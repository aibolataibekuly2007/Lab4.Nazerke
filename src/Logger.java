import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
    private static volatile Logger instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private LoggerConfig config;
    private PrintWriter fileWriter;
    private long currentFileSize;
    private int currentBackupIndex;

    private Logger() {
        this.config = new LoggerConfig();
        initializeFileWriter();
    }

    private Logger(String configFile) {
        this.config = LoggerConfig.loadFromFile(configFile);
        initializeFileWriter();
    }

    public static Logger getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Logger();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static Logger getInstance(String configFile) {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Logger(configFile);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void initializeFileWriter() {
        try {
            File file = new File(config.getLogFilePath());

            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            fileWriter = new PrintWriter(new FileWriter(file, true));
            currentFileSize = file.exists() ? file.length() : 0;
        } catch (IOException e) {
            System.err.println("Failed to initialize file writer: " + e.getMessage());
            try {
                fileWriter = new PrintWriter(System.out);
            } catch (Exception ex) {
                System.err.println("Critical error: " + ex.getMessage());
            }
        }
    }

    private void checkFileRotation() {
        if (currentFileSize >= config.getMaxFileSize()) {
            lock.lock();
            try {
                rotateLogFile();
            } finally {
                lock.unlock();
            }
        }
    }

    private void rotateLogFile() {
        try {
            fileWriter.close();

            File currentFile = new File(config.getLogFilePath());

            for (int i = config.getMaxBackupFiles() - 1; i >= 0; i--) {
                File rotatedFile = new File(config.getLogFilePath() + "." + i);
                File nextFile = new File(config.getLogFilePath() + "." + (i + 1));

                if (rotatedFile.exists()) {
                    Files.move(rotatedFile.toPath(), nextFile.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                }
            }

            File backupFile = new File(config.getLogFilePath() + ".0");
            if (currentFile.exists()) {
                Files.move(currentFile.toPath(), backupFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }

            initializeFileWriter();
            currentFileSize = 0;

            log(LogLevel.INFO, "Log file rotated, backup created: " + backupFile.getName());

        } catch (IOException e) {
            System.err.println("Error rotating log file: " + e.getMessage());
        }
    }

    public void log(LogLevel level, String message) {
        if (level.getLevel() < config.getLogLevel().getLevel()) {
            return;
        }

        lock.lock();
        try {
            LogEntry entry = new LogEntry(level, message);
            String logLine = entry.toString();

            if (fileWriter != null) {
                fileWriter.println(logLine);
                fileWriter.flush();

                currentFileSize += logLine.length() + System.lineSeparator().length();
            }

            if (config.isConsoleOutput()) {
                String color = getColorForLevel(level);
                System.out.println(color + logLine + "\u001B[0m");
            }

            checkFileRotation();

        } finally {
            lock.unlock();
        }
    }

    private String getColorForLevel(LogLevel level) {
        switch (level) {
            case ERROR: return "\u001B[31m"; // Red
            case WARNING: return "\u001B[33m"; // Yellow
            case INFO: return "\u001B[32m"; // Green
            default: return "\u001B[0m";
        }
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void setLogLevel(LogLevel level) {
        lock.lock();
        try {
            this.config.setLogLevel(level);
            log(LogLevel.INFO, "Log level changed to: " + level);
        } finally {
            lock.unlock();
        }
    }

    public void setConfig(LoggerConfig config) {
        lock.lock();
        try {
            this.config = config;
            if (fileWriter != null) {
                fileWriter.close();
            }
            initializeFileWriter();
            log(LogLevel.INFO, "Logger configuration updated");
        } finally {
            lock.unlock();
        }
    }

    public void reloadConfig(String configFile) {
        lock.lock();
        try {
            LoggerConfig newConfig = LoggerConfig.loadFromFile(configFile);
            setConfig(newConfig);
        } finally {
            lock.unlock();
        }
    }

    public void close() {
        lock.lock();
        try {
            if (fileWriter != null) {
                fileWriter.close();
            }
        } finally {
            lock.unlock();
        }
    }
}
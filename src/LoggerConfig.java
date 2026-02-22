import java.io.*;
import java.util.Properties;

public class LoggerConfig {
    private LogLevel logLevel;
    private String logFilePath;
    private boolean consoleOutput;
    private int maxFileSize; // in bytes
    private int maxBackupFiles;

    public LoggerConfig() {
        this.logLevel = LogLevel.INFO;
        this.logFilePath = "logs/app.log";
        this.consoleOutput = true;
        this.maxFileSize = 10 * 1024 * 1024; // 10 MB
        this.maxBackupFiles = 3;
    }

    public static LoggerConfig loadFromFile(String filename) {
        LoggerConfig config = new LoggerConfig();
        Properties props = new Properties();

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Config file not found: " + filename + ", using defaults");
            config.saveToFile(filename);
            return config;
        }

        try (InputStream input = new FileInputStream(filename)) {
            props.load(input);

            if (props.containsKey("log.level")) {
                config.setLogLevel(LogLevel.fromString(props.getProperty("log.level")));
            }
            if (props.containsKey("log.file")) {
                config.setLogFilePath(props.getProperty("log.file"));
            }
            if (props.containsKey("log.console")) {
                config.setConsoleOutput(Boolean.parseBoolean(props.getProperty("log.console")));
            }
            if (props.containsKey("log.maxSize")) {
                config.setMaxFileSize(Integer.parseInt(props.getProperty("log.maxSize")));
            }
            if (props.containsKey("log.backupFiles")) {
                config.setMaxBackupFiles(Integer.parseInt(props.getProperty("log.backupFiles")));
            }

            System.out.println("Logger configuration loaded from: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading config file: " + e.getMessage());
        }

        return config;
    }

    public void saveToFile(String filename) {
        Properties props = new Properties();
        props.setProperty("log.level", logLevel.name());
        props.setProperty("log.file", logFilePath);
        props.setProperty("log.console", String.valueOf(consoleOutput));
        props.setProperty("log.maxSize", String.valueOf(maxFileSize));
        props.setProperty("log.backupFiles", String.valueOf(maxBackupFiles));

        File file = new File(filename);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (OutputStream output = new FileOutputStream(filename)) {
            props.store(output, "Logger Configuration");
            System.out.println("Logger configuration saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public boolean isConsoleOutput() {
        return consoleOutput;
    }

    public void setConsoleOutput(boolean consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public int getMaxBackupFiles() {
        return maxBackupFiles;
    }

    public void setMaxBackupFiles(int maxBackupFiles) {
        this.maxBackupFiles = maxBackupFiles;
    }
}
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogReader {
    private String logFilePath;

    public LogReader(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public List<String> readAllLogs() {
        return readLogs(null, null);
    }

    public List<String> readLogsByLevel(LogLevel level) {
        return readLogs(level, null);
    }

    public List<String> readLogsByDate(LocalDateTime date) {
        return readLogs(null, date);
    }

    private List<String> readLogs(LogLevel filterLevel, LocalDateTime filterDate) {
        List<String> filteredLogs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        File file = new File(logFilePath);
        if (!file.exists()) {
            System.out.println("Log file not found: " + logFilePath);
            return filteredLogs;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean include = true;

                if (filterLevel != null) {
                    include = line.contains("[" + filterLevel + "]");
                }

                if (filterDate != null && include && line.length() > 24) {
                    try {
                        String dateStr = line.substring(1, 24);
                        LocalDateTime logDate = LocalDateTime.parse(dateStr, formatter);
                        include = logDate.toLocalDate().equals(filterDate.toLocalDate());
                    } catch (Exception e) {
                        include = false;
                    }
                }

                if (include) {
                    filteredLogs.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }

        return filteredLogs;
    }

    public void displayLogs(List<String> logs) {
        if (logs.isEmpty()) {
            System.out.println("No logs found.");
            return;
        }

        for (String log : logs) {
            if (log.contains("[ERROR]")) {
                System.out.println("\u001B[31m" + log + "\u001B[0m");
            } else if (log.contains("[WARNING]")) {
                System.out.println("\u001B[33m" + log + "\u001B[0m");
            } else {
                System.out.println("\u001B[32m" + log + "\u001B[0m");
            }
        }
    }

    public void displayStatistics() {
        List<String> allLogs = readAllLogs();
        int infoCount = 0;
        int warningCount = 0;
        int errorCount = 0;

        for (String log : allLogs) {
            if (log.contains("[INFO]")) infoCount++;
            else if (log.contains("[WARNING]")) warningCount++;
            else if (log.contains("[ERROR]")) errorCount++;
        }

        System.out.println("\n=== Log Statistics ===");
        System.out.println("Total logs: " + allLogs.size());
        System.out.println("INFO: " + infoCount);
        System.out.println("WARNING: " + warningCount);
        System.out.println("ERROR: " + errorCount);
    }
}
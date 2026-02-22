import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {
    private LocalDateTime timestamp;
    private LogLevel level;
    private String message;
    private String threadName;

    public LogEntry(LogLevel level, String message) {
        this.timestamp = LocalDateTime.now();
        this.level = level;
        this.message = message;
        this.threadName = Thread.currentThread().getName();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return String.format("[%s] [%s] [%s] %s",
                timestamp.format(formatter),
                threadName,
                level,
                message);
    }

    public LogLevel getLevel() {
        return level;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
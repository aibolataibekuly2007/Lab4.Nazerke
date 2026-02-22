import java.time.LocalDateTime;

public class LoggerMain {
    public static void main(String[] args) {
        System.out.println("=== Logger Singleton Test ===\n");

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println("logger1 hash: " + System.identityHashCode(logger1));
        System.out.println("logger2 hash: " + System.identityHashCode(logger2));
        System.out.println("Same instance: " + (logger1 == logger2));

        System.out.println("\n=== Testing different log levels ===");
        logger1.info("Application started");
        logger1.warning("Low disk space");
        logger1.error("Database connection failed");

        System.out.println("\n=== Changing log level to WARNING ===");
        logger1.setLogLevel(LogLevel.WARNING);

        logger1.info("This INFO message should NOT appear");
        logger1.warning("This WARNING message should appear");
        logger1.error("This ERROR message should appear");

        System.out.println("\n=== Changing log level to ERROR ===");
        logger1.setLogLevel(LogLevel.ERROR);

        logger1.info("This INFO message should NOT appear");
        logger1.warning("This WARNING message should NOT appear");
        logger1.error("This ERROR message should appear");

        System.out.println("\n=== Testing LogReader ===");
        LogReader reader = new LogReader("logs/app.log");

        System.out.println("\nAll logs:");
        reader.displayLogs(reader.readAllLogs());

        System.out.println("\nERROR logs only:");
        reader.displayLogs(reader.readLogsByLevel(LogLevel.ERROR));

        reader.displayStatistics();

        MultiThreadLoggerTest.main(args);

        logger1.close();
    }
}
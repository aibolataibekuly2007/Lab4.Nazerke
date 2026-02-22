public enum LogLevel {
    INFO(0),
    WARNING(1),
    ERROR(2);

    private final int level;

    LogLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static LogLevel fromString(String level) {
        try {
            return LogLevel.valueOf(level.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INFO;
        }
    }
}
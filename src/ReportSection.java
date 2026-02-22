public class ReportSection {
    private String title;
    private String content;
    private String type; // "text", "table", "chart"

    public ReportSection(String title, String content) {
        this.title = title;
        this.content = content;
        this.type = "text";
    }

    public ReportSection(String title, String content, String type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}
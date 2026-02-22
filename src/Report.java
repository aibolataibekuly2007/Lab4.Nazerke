import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private String title;
    private List<ReportSection> sections;
    private String footer;
    private ReportStyle style;
    private String format;
    private LocalDateTime createdAt;

    public Report(String format) {
        this.format = format;
        this.sections = new ArrayList<>();
        this.style = new ReportStyle();
        this.createdAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addSection(ReportSection section) {
        sections.add(section);
    }

    public void addSection(String title, String content) {
        sections.add(new ReportSection(title, content));
    }

    public void addSection(String title, String content, String type) {
        sections.add(new ReportSection(title, content, type));
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void setStyle(ReportStyle style) {
        this.style = style;
    }

    public String export() {
        switch (format.toLowerCase()) {
            case "html":
                return exportToHtml();
            case "text":
                return exportToText();
            case "pdf":
                return exportToPdf();
            default:
                return exportToText();
        }
    }

    private String exportToText() {
        StringBuilder sb = new StringBuilder();
        String line = "=".repeat(80);

        sb.append(line).append("\n");
        sb.append(centerText(title, 80)).append("\n");
        sb.append(line).append("\n\n");

        for (ReportSection section : sections) {
            sb.append("■ ").append(section.getTitle()).append("\n");
            sb.append("-".repeat(40)).append("\n");
            sb.append(section.getContent()).append("\n\n");
        }

        sb.append(line).append("\n");
        sb.append(centerText(footer, 80)).append("\n");
        sb.append(line).append("\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sb.append("Generated: ").append(createdAt.format(formatter)).append("\n");

        return sb.toString();
    }

    private String exportToHtml() {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n");
        sb.append("<html>\n<head>\n");
        sb.append("<style>\n");
        sb.append("body { background-color: ").append(style.getBackgroundColor()).append("; }\n");
        sb.append(".report { font-family: ").append(style.getFontFamily()).append("; }\n");
        sb.append(".title { color: ").append(style.getFontColor()).append("; font-size: ").append(style.getFontSize() + 8).append("px; ");
        if (style.isBold()) sb.append("font-weight: bold; ");
        sb.append("}\n");
        sb.append(".section-title { color: ").append(style.getFontColor()).append("; font-size: ").append(style.getFontSize() + 4).append("px; ");
        if (style.isBold()) sb.append("font-weight: bold; ");
        sb.append("}\n");
        sb.append(".content { color: ").append(style.getFontColor()).append("; font-size: ").append(style.getFontSize()).append("px; }\n");
        sb.append(".footer { color: ").append(style.getFontColor()).append("; font-size: ").append(style.getFontSize() - 2).append("px; ");
        if (style.isItalic()) sb.append("font-style: italic; ");
        sb.append("}\n");
        sb.append("</style>\n</head>\n<body>\n");

        sb.append("<div class='report'>\n");
        sb.append("<h1 class='title'>").append(title).append("</h1>\n");
        sb.append("<hr>\n");

        for (ReportSection section : sections) {
            sb.append("<h2 class='section-title'>").append(section.getTitle()).append("</h2>\n");

            if ("table".equals(section.getType())) {
                sb.append("<table border='1' style='width:100%'>\n");
                String[] rows = section.getContent().split("\n");
                for (String row : rows) {
                    sb.append("<tr>");
                    String[] cells = row.split("\\|");
                    for (String cell : cells) {
                        sb.append("<td>").append(cell).append("</td>");
                    }
                    sb.append("</tr>\n");
                }
                sb.append("</table>\n");
            } else {
                sb.append("<p class='content'>").append(section.getContent().replace("\n", "<br>")).append("</p>\n");
            }
        }

        sb.append("<hr>\n");
        sb.append("<p class='footer'>").append(footer).append("</p>\n");
        sb.append("<p class='footer'>Generated: ").append(createdAt).append("</p>\n");
        sb.append("</div>\n</body>\n</html>");

        return sb.toString();
    }

    private String exportToPdf() {
        StringBuilder sb = new StringBuilder();
        sb.append("%PDF-1.4\n");
        sb.append("1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n");
        sb.append("2 0 obj\n<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n");

        String content = exportToText();
        sb.append("3 0 obj\n<< /Type /Page /Parent 2 0 R /Resources << /Font << /F1 4 0 R >> >> /MediaBox [0 0 612 792] /Contents 5 0 R >>\nendobj\n");
        sb.append("4 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\nendobj\n");
        sb.append("5 0 obj\n<< /Length ").append(content.length()).append(" >>\nstream\n");
        sb.append("BT /F1 12 Tf 72 720 Td (").append(title).append(") Tj ET\n");
        sb.append("endstream\nendobj\n");
        sb.append("xref\n0 6\n0000000000 65535 f\n0000000010 00000 n\n0000000079 00000 n\n0000000178 00000 n\n0000000457 00000 n\n0000000540 00000 n\ntrailer\n<< /Size 6 /Root 1 0 R >>\nstartxref\n650\n%%EOF");

        return sb.toString() + "\n\n[PDF Content]\n" + content;
    }

    private String centerText(String text, int width) {
        if (text == null || text.length() >= width) {
            return text;
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text;
    }

    public void display() {
        System.out.println(export());
    }
}
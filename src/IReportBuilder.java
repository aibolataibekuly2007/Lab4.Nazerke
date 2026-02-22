public interface IReportBuilder {
    IReportBuilder setTitle(String title);
    IReportBuilder addSection(String title, String content);
    IReportBuilder addSection(String title, String content, String type);
    IReportBuilder setFooter(String footer);
    IReportBuilder setStyle(ReportStyle style);
    Report build();
}
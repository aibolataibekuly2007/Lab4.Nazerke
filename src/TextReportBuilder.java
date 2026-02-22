public class TextReportBuilder implements IReportBuilder {
    private Report report;

    public TextReportBuilder() {
        this.report = new Report("text");
    }

    @Override
    public IReportBuilder setTitle(String title) {
        report.setTitle(title);
        return this;
    }

    @Override
    public IReportBuilder addSection(String title, String content) {
        report.addSection(title, content);
        return this;
    }

    @Override
    public IReportBuilder addSection(String title, String content, String type) {
        report.addSection(title, content, type);
        return this;
    }

    @Override
    public IReportBuilder setFooter(String footer) {
        report.setFooter(footer);
        return this;
    }

    @Override
    public IReportBuilder setStyle(ReportStyle style) {
        report.setStyle(style);
        return this;
    }

    @Override
    public Report build() {
        return report;
    }
}
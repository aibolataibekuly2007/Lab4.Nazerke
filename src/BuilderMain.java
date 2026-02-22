public class BuilderMain {
    public static void main(String[] args) {
        System.out.println("=== Advanced Builder Pattern Test ===\n");

        ReportDirector director = new ReportDirector();

        System.out.println("--- Text Report ---");
        IReportBuilder textBuilder = new TextReportBuilder();
        Report textReport = director.createSalesReport(textBuilder);
        textReport.display();

        System.out.println("\n" + "=".repeat(80) + "\n");

        System.out.println("--- HTML Report with Styling ---");
        IReportBuilder htmlBuilder = new HtmlReportBuilder();
        ReportStyle style = new ReportStyle("#E6F3FF", "#004080", 12);
        style.setBold(true);

        Report htmlReport = htmlBuilder.setTitle("Styled HTML Report")
                .setStyle(style)
                .addSection("Section 1", "This is a styled section with custom colors", "text")
                .addSection("Data Table", "Product|Sales|Growth\nLaptop|45000|12%\nPhone|32000|8%", "table")
                .setFooter("Generated with custom styling")
                .build();
        htmlReport.display();

        System.out.println("\n" + "=".repeat(80) + "\n");

        System.out.println("--- Financial Report ---");
        IReportBuilder pdfBuilder = new PdfReportBuilder();
        Report financialReport = director.createFinancialReport(pdfBuilder);
        financialReport.display();

        System.out.println("\n" + "=".repeat(80) + "\n");

        System.out.println("--- Custom Report ---");
        IReportBuilder customBuilder = new TextReportBuilder();
        String[][] sections = {
                {"Executive Summary", "Project completed on time\nBudget: $150,000\nTimeline: 6 months"},
                {"Team Members", "Lead: Sarah Connor\nDev: 5 members\nQA: 2 members"},
                {"Risk Assessment", "Low|Medium|High\nSchedule|Low|-\nBudget|Medium|Under review", "table"}
        };

        Report customReport = director.createCustomReport(
                customBuilder,
                "Project Completion Report",
                sections,
                "Project Manager: John Smith"
        );
        customReport.display();
    }
}
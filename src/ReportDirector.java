public class ReportDirector {

    public Report createSalesReport(IReportBuilder builder) {
        return builder.setTitle("Monthly Sales Report")
                .addSection("Summary", "Total Revenue: $125,000\nTotal Orders: 1,250\nAverage Order Value: $100")
                .addSection("Top Products", "1. Laptop: $45,000\n2. Smartphone: $32,000\n3. Headphones: $18,000", "table")
                .addSection("Regional Performance", "North: $45,000\nSouth: $32,000\nEast: $28,000\nWest: $20,000", "table")
                .setFooter("Confidential - For internal use only")
                .build();
    }

    public Report createEmployeeReport(IReportBuilder builder) {
        return builder.setTitle("Employee Performance Review")
                .addSection("Overview", "Total Employees: 45\nDepartments: 6\nReview Period: Q1 2025")
                .addSection("Top Performers", "1. John Doe (Sales): 150% quota\n2. Jane Smith (Dev): 98% completion\n3. Bob Johnson (Support): 4.9 rating")
                .addSection("Attendance", "Average attendance: 98%\nLate arrivals: 12\nDays off: 87")
                .setFooter("HR Department - Annual Review")
                .build();
    }

    public Report createFinancialReport(IReportBuilder builder) {
        ReportStyle style = new ReportStyle("#F0F0F0", "#003366", 11);
        style.setBold(true);

        return builder.setTitle("Financial Summary 2025")
                .addSection("Income Statement", "Revenue: $1,250,000\nExpenses: $875,000\nNet Income: $375,000")
                .addSection("Balance Sheet", "Assets: $2,100,000\nLiabilities: $850,000\nEquity: $1,250,000", "table")
                .addSection("Cash Flow", "Operating: +$420,000\nInvesting: -$150,000\nFinancing: -$70,000")
                .setStyle(style)
                .setFooter("Audited financial statements")
                .build();
    }

    public Report createCustomReport(IReportBuilder builder, String title,
                                     String[][] sections, String footer) {
        IReportBuilder reportBuilder = builder.setTitle(title);

        for (String[] section : sections) {
            if (section.length == 2) {
                reportBuilder.addSection(section[0], section[1]);
            } else if (section.length == 3) {
                reportBuilder.addSection(section[0], section[1], section[2]);
            }
        }

        return reportBuilder.setFooter(footer).build();
    }
}
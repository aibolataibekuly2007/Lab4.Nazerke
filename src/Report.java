public class Report implements Document {
    private String title;
    private String author;
    private int pageCount;

    public Report(String title, String author, int pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }

    @Override
    public void open() {
        System.out.println("Opening Report: " + title);
        System.out.println("Author: " + author);
        System.out.println("Pages: " + pageCount);
        System.out.println("Report opened in view mode");
    }

    @Override
    public String getDocumentType() {
        return "Report";
    }

    @Override
    public String getInfo() {
        return "Report [Title: " + title + ", Author: " + author + ", Pages: " + pageCount + "]";
    }
}
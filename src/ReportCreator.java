public class ReportCreator extends DocumentCreator {
    private String title;
    private String author;
    private int pageCount;

    public ReportCreator(String title, String author, int pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }

    @Override
    public Document createDocument() {
        return new Report(title, author, pageCount);
    }
}
public class Letter implements Document {
    private String recipient;
    private String sender;
    private String subject;

    public Letter(String recipient, String sender, String subject) {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
    }

    @Override
    public void open() {
        System.out.println("Opening Letter to: " + recipient);
        System.out.println("From: " + sender);
        System.out.println("Subject: " + subject);
        System.out.println("Letter opened in compose mode");
    }

    @Override
    public String getDocumentType() {
        return "Letter";
    }

    @Override
    public String getInfo() {
        return "Letter [To: " + recipient + ", From: " + sender + ", Subject: " + subject + "]";
    }
}
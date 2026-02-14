public class LetterCreator extends DocumentCreator {
    private String recipient;
    private String sender;
    private String subject;

    public LetterCreator(String recipient, String sender, String subject) {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
    }

    @Override
    public Document createDocument() {
        return new Letter(recipient, sender, subject);
    }
}
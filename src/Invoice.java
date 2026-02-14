public class Invoice implements Document {
    private String invoiceNumber;
    private String clientName;
    private double amount;

    public Invoice(String invoiceNumber, String clientName, double amount) {
        this.invoiceNumber = invoiceNumber;
        this.clientName = clientName;
        this.amount = amount;
    }

    @Override
    public void open() {
        System.out.println("Opening Invoice #" + invoiceNumber);
        System.out.println("Client: " + clientName);
        System.out.println("Amount: $" + amount);
        System.out.println("Invoice opened in accounting mode");
    }

    @Override
    public String getDocumentType() {
        return "Invoice";
    }

    @Override
    public String getInfo() {
        return "Invoice [Number: " + invoiceNumber + ", Client: " + clientName + ", Amount: $" + amount + "]";
    }
}
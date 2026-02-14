public class InvoiceCreator extends DocumentCreator {
    private String invoiceNumber;
    private String clientName;
    private double amount;

    public InvoiceCreator(String invoiceNumber, String clientName, double amount) {
        this.invoiceNumber = invoiceNumber;
        this.clientName = clientName;
        this.amount = amount;
    }

    @Override
    public Document createDocument() {
        return new Invoice(invoiceNumber, clientName, amount);
    }
}
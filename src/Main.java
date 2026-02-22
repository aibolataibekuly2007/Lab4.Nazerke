import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Document> documents = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getUserChoice();

            if (choice == 0) {
                System.out.println("Exiting program...");
                scanner.close();
                break;
            }

            processChoice(choice);
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Document Factory System ===");
        System.out.println("1. Create Report");
        System.out.println("2. Create Resume");
        System.out.println("3. Create Letter");
        System.out.println("4. Create Invoice (New)");
        System.out.println("5. Open all documents");
        System.out.println("6. Show all documents");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void processChoice(int choice) {
        switch (choice) {
            case 1:
                createReport();
                break;
            case 2:
                createResume();
                break;
            case 3:
                createLetter();
                break;
            case 4:
                createInvoice();
                break;
            case 5:
                openAllDocuments();
                break;
            case 6:
                showAllDocuments();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void createReport() {
        System.out.println("\n=== Create Report ===");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter page count: ");
        int pageCount = Integer.parseInt(scanner.nextLine());

        ReportCreator creator = new ReportCreator(title, author, pageCount);
        Document report = creator.createDocument();
        documents.add(report);

        System.out.println("Report created successfully!");
        report.open();
    }

    private static void createResume() {
        System.out.println("\n=== Create Resume ===");
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter position: ");
        String position = scanner.nextLine();
        System.out.print("Enter experience (years): ");
        int experience = Integer.parseInt(scanner.nextLine());

        ResumeCreator creator = new ResumeCreator(fullName, position, experience);
        Document resume = creator.createDocument();
        documents.add(resume);

        System.out.println("Resume created successfully!");
        resume.open();
    }

    private static void createLetter() {
        System.out.println("\n=== Create Letter ===");
        System.out.print("Enter recipient: ");
        String recipient = scanner.nextLine();
        System.out.print("Enter sender: ");
        String sender = scanner.nextLine();
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();

        LetterCreator creator = new LetterCreator(recipient, sender, subject);
        Document letter = creator.createDocument();
        documents.add(letter);

        System.out.println("Letter created successfully!");
        letter.open();
    }

    private static void createInvoice() {
        System.out.println("\n=== Create Invoice ===");
        System.out.print("Enter invoice number: ");
        String invoiceNumber = scanner.nextLine();
        System.out.print("Enter client name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter amount: $");
        double amount = Double.parseDouble(scanner.nextLine());

        InvoiceCreator creator = new InvoiceCreator(invoiceNumber, clientName, amount);
        Document invoice = creator.createDocument();
        documents.add(invoice);

        System.out.println("Invoice created successfully!");
        invoice.open();
    }

    private static void openAllDocuments() {
        System.out.println("\n=== Opening All Documents ===");
        if (documents.isEmpty()) {
            System.out.println("No documents to open.");
            return;
        }

        for (int i = 0; i < documents.size(); i++) {
            System.out.println("\n--- Document " + (i + 1) + " ---");
            documents.get(i).open();
        }
    }

    private static void showAllDocuments() {
        System.out.println("\n=== All Documents ===");
        if (documents.isEmpty()) {
            System.out.println("No documents created yet.");
            return;
        }

        for (int i = 0; i < documents.size(); i++) {
            System.out.println((i + 1) + ". " + documents.get(i).getInfo());
        }
    }
}
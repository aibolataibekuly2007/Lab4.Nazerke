public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("MODULE 05 - PRACTICAL WORK");
        System.out.println("Singleton | Builder | Prototype");
        System.out.println("==========================================\n");

        LoggerMain.main(args);

        System.out.println("\n" + "=".repeat(80) + "\n");
        BuilderMain.main(args);

        System.out.println("\n" + "=".repeat(80) + "\n");
        PrototypeGameMain.main(args);
    }
}
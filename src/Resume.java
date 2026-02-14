public class Resume implements Document {
    private String fullName;
    private String position;
    private int experience;

    public Resume(String fullName, String position, int experience) {
        this.fullName = fullName;
        this.position = position;
        this.experience = experience;
    }

    @Override
    public void open() {
        System.out.println("Opening Resume: " + fullName);
        System.out.println("Position: " + position);
        System.out.println("Experience: " + experience + " years");
        System.out.println("Resume opened in preview mode");
    }

    @Override
    public String getDocumentType() {
        return "Resume";
    }

    @Override
    public String getInfo() {
        return "Resume [Name: " + fullName + ", Position: " + position + ", Experience: " + experience + " years]";
    }
}
public class ResumeCreator extends DocumentCreator {
    private String fullName;
    private String position;
    private int experience;

    public ResumeCreator(String fullName, String position, int experience) {
        this.fullName = fullName;
        this.position = position;
        this.experience = experience;
    }

    @Override
    public Document createDocument() {
        return new Resume(fullName, position, experience);
    }
}
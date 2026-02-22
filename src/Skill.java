public class Skill implements Cloneable {
    private String name;
    private String type; // Physical, Magic, Healing, Buff
    private int power;
    private int manaCost;
    private int cooldown;
    private String description;

    public Skill(String name, String type, int power, int manaCost, int cooldown, String description) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.description = description;
    }

    public Skill(Skill source) {
        this.name = source.name;
        this.type = source.type;
        this.power = source.power;
        this.manaCost = source.manaCost;
        this.cooldown = source.cooldown;
        this.description = source.description;
    }

    @Override
    public Skill clone() {
        return new Skill(this);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - Power: %d, Mana: %d, CD: %d",
                name, type, power, manaCost, cooldown);
    }
}
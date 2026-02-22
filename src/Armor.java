public class Armor implements Cloneable {
    private String name;
    private String type; // Helmet, Chestplate, Leggings, Boots, Shield
    private int defense;
    private int durability;
    private String material;

    public Armor(String name, String type, int defense, int durability, String material) {
        this.name = name;
        this.type = type;
        this.defense = defense;
        this.durability = durability;
        this.material = material;
    }

    public Armor(Armor source) {
        this.name = source.name;
        this.type = source.type;
        this.defense = source.defense;
        this.durability = source.durability;
        this.material = source.material;
    }

    @Override
    public Armor clone() {
        return new Armor(this);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }

    public void reduceDurability(int amount) {
        this.durability = Math.max(0, durability - amount);
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - Def: %d, Dur: %d (%s)",
                name, type, defense, durability, material);
    }
}
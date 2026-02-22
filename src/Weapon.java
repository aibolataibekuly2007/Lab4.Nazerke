public class Weapon implements Cloneable {
    private String name;
    private String type;
    private int damage;
    private double attackSpeed;
    private int range;
    private String rarity; // Common, Rare, Epic, Legendary

    public Weapon(String name, String type, int damage, double attackSpeed, int range, String rarity) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.rarity = rarity;
    }

    public Weapon(Weapon source) {
        this.name = source.name;
        this.type = source.type;
        this.damage = source.damage;
        this.attackSpeed = source.attackSpeed;
        this.range = source.range;
        this.rarity = source.rarity;
    }

    @Override
    public Weapon clone() {
        return new Weapon(this);
    }

    public double calculateDPS() {
        return damage * attackSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public String getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - Dmg: %d, Speed: %.1f, DPS: %.1f (%s)",
                name, type, damage, attackSpeed, calculateDPS(), rarity);
    }
}
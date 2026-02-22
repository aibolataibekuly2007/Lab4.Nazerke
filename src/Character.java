import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character implements Cloneable {
    private static int nextId = 1000;

    private int id;
    private String name;
    private String characterClass;
    private int level;

    // Attributes
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int strength;
    private int agility;
    private int intelligence;

    // Equipment
    private Map<String, Weapon> weapons;
    private Map<String, Armor> armor;
    private List<Skill> skills;

    // Stats
    private int experience;
    private int gold;

    public Character(String name, String characterClass) {
        this.id = nextId++;
        this.name = name;
        this.characterClass = characterClass;
        this.level = 1;

        this.weapons = new HashMap<>();
        this.armor = new HashMap<>();
        this.skills = new ArrayList<>();

        initializeBaseStats();
    }

    public Character(Character source) {
        this.id = nextId++;
        this.name = source.name;
        this.characterClass = source.characterClass;
        this.level = source.level;

        this.health = source.health;
        this.maxHealth = source.maxHealth;
        this.mana = source.mana;
        this.maxMana = source.maxMana;
        this.strength = source.strength;
        this.agility = source.agility;
        this.intelligence = source.intelligence;

        this.weapons = new HashMap<>();
        for (Map.Entry<String, Weapon> entry : source.weapons.entrySet()) {
            this.weapons.put(entry.getKey(), entry.getValue().clone());
        }

        this.armor = new HashMap<>();
        for (Map.Entry<String, Armor> entry : source.armor.entrySet()) {
            this.armor.put(entry.getKey(), entry.getValue().clone());
        }

        this.skills = new ArrayList<>();
        for (Skill skill : source.skills) {
            this.skills.add(skill.clone());
        }

        this.experience = source.experience;
        this.gold = source.gold;
    }

    @Override
    public Character clone() {
        return new Character(this);
    }

    private void initializeBaseStats() {
        switch (characterClass.toLowerCase()) {
            case "warrior":
                this.maxHealth = 120;
                this.maxMana = 50;
                this.strength = 15;
                this.agility = 8;
                this.intelligence = 5;
                break;
            case "mage":
                this.maxHealth = 70;
                this.maxMana = 150;
                this.strength = 4;
                this.agility = 6;
                this.intelligence = 18;
                break;
            case "rogue":
                this.maxHealth = 90;
                this.maxMana = 80;
                this.strength = 8;
                this.agility = 16;
                this.intelligence = 7;
                break;
            default:
                this.maxHealth = 100;
                this.maxMana = 100;
                this.strength = 10;
                this.agility = 10;
                this.intelligence = 10;
        }

        this.health = maxHealth;
        this.mana = maxMana;
    }

    public void equipWeapon(String slot, Weapon weapon) {
        weapons.put(slot, weapon);
    }

    public void equipArmor(String slot, Armor piece) {
        armor.put(slot, piece);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public void levelUp() {
        level++;
        maxHealth += 10;
        maxMana += 5;
        strength += 2;
        agility += 2;
        intelligence += 2;
        health = maxHealth;
        mana = maxMana;
    }

    public int getTotalDefense() {
        int total = 0;
        for (Armor piece : armor.values()) {
            total += piece.getDefense();
        }
        return total;
    }

    public int getTotalDamage() {
        int total = 0;
        for (Weapon weapon : weapons.values()) {
            total += weapon.getDamage();
        }
        return total;
    }

    public void displayInfo() {
        System.out.println("\n=== Character #" + id + " ===");
        System.out.println("Name: " + name);
        System.out.println("Class: " + characterClass);
        System.out.println("Level: " + level);
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("Mana: " + mana + "/" + maxMana);
        System.out.println("Stats: STR=" + strength + " AGI=" + agility + " INT=" + intelligence);

        System.out.println("\nWeapons:");
        if (weapons.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Map.Entry<String, Weapon> entry : weapons.entrySet()) {
                System.out.println("  [" + entry.getKey() + "] " + entry.getValue());
            }
        }

        System.out.println("\nArmor:");
        if (armor.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Map.Entry<String, Armor> entry : armor.entrySet()) {
                System.out.println("  [" + entry.getKey() + "] " + entry.getValue());
            }
        }

        System.out.println("\nSkills:");
        if (skills.isEmpty()) {
            System.out.println("  None");
        } else {
            for (Skill skill : skills) {
                System.out.println("  " + skill);
            }
        }

        System.out.println("\nTotal Defense: " + getTotalDefense());
        System.out.println("Total Damage: " + getTotalDamage());
        System.out.println("Gold: " + gold);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    public void setMana(int mana) {
        this.mana = Math.min(mana, maxMana);
    }
}
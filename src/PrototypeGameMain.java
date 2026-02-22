public class PrototypeGameMain {
    public static void main(String[] args) {
        System.out.println("=== Game Character Prototype System ===\n");

        Character warriorTemplate = new Character("Warrior", "Warrior");

        Weapon sword = new Weapon("Dragon Sword", "Sword", 45, 1.2, 1, "Epic");
        Weapon axe = new Weapon("Battle Axe", "Axe", 60, 0.8, 1, "Rare");

        Armor helmet = new Armor("Dragon Helm", "Helmet", 25, 100, "Steel");
        Armor chestplate = new Armor("Dragon Plate", "Chestplate", 45, 150, "Steel");
        Armor shield = new Armor("Dragon Shield", "Shield", 35, 120, "Steel");

        Skill smash = new Skill("Smash", "Physical", 50, 20, 3, "Powerful strike");
        Skill taunt = new Skill("Taunt", "Buff", 0, 15, 5, "Draw enemy attention");

        warriorTemplate.equipWeapon("Main Hand", sword);
        warriorTemplate.equipWeapon("Off Hand", axe);
        warriorTemplate.equipArmor("Head", helmet);
        warriorTemplate.equipArmor("Chest", chestplate);
        warriorTemplate.equipArmor("Off Hand", shield);
        warriorTemplate.addSkill(smash);
        warriorTemplate.addSkill(taunt);

        warriorTemplate.levelUp();
        warriorTemplate.addGold(1000);

        System.out.println("--- Template Warrior ---");
        warriorTemplate.displayInfo();

        System.out.println("\n" + "=".repeat(80) + "\n");

        Character player1 = warriorTemplate.clone();
        player1.setName("Aragorn");
        player1.addGold(500);

        System.out.println("--- Player 1 (Cloned from template) ---");
        player1.displayInfo();

        System.out.println("\n" + "=".repeat(80) + "\n");

        Character player2 = player1.clone();
        player2.setName("Boromir");

        Weapon betterSword = new Weapon("Legendary Sword", "Sword", 80, 1.4, 1, "Legendary");
        player2.equipWeapon("Main Hand", betterSword);
        player2.levelUp();
        player2.addGold(200);

        System.out.println("--- Player 2 (Cloned from Player 1 with modifications) ---");
        player2.displayInfo();

        System.out.println("\n" + "=".repeat(80) + "\n");

        System.out.println("--- Verify Deep Clone (Original unchanged) ---");
        System.out.println("Original warrior's main weapon: " +
                warriorTemplate.getTotalDamage());
        System.out.println("Player 2's main weapon: " +
                player2.getTotalDamage());
    }
}
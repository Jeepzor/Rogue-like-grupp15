package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorDecisionTableTest {
    //The different player classes.
    Player wizardPlayer = new Player(new Wizard(),new Position(25, 10));
    Player warriorPlayer = new Player(new Warrior(), new Position(3, 9));

    //Items to fill inventory for test 1.
    Armor armor1 = new Armor("Cloth");
    Armor armor2 = new Armor("Cloth");
    Armor armor3 = new Armor("Cloth");

    //Item for plate tests
    Armor plateArmor = new Armor("plate");

    @Test
    public void canNotEquipItemThatIsNotInInventory(){
        Inventory inventory = new Inventory(warriorPlayer);
        assertThrows(IllegalStateException.class, () -> {
            inventory.equipItem(armor1);
        });

    }
    @Test
    public void canNotEquipItemWithFullInventory(){
        Inventory inventory = new Inventory(wizardPlayer);
        Armor armor4 = new Armor("Cloth");
        inventory.addItemToNEItems(armor1);
        inventory.addItemToNEItems(armor2);
        inventory.addItemToNEItems(armor3);
        inventory.addItemToNEItems(armor4);
        inventory.equipItem(armor1);
        inventory.equipItem(armor2);
        inventory.equipItem(armor3);
        assertThrows(IllegalStateException.class, () -> {
            inventory.equipItem(armor4);
        });

    }
    @Test
    public void canNotEquipItemWithTooHighLevelReq(){
        Inventory inventory = new Inventory(wizardPlayer);
        Armor highLevelArmor = new Armor("Cloth", 10);
        inventory.addItemToNEItems(highLevelArmor);
        assertThrows(IllegalStateException.class, () -> {
            inventory.equipItem(highLevelArmor);
        });
    }

    @Test
    public void wizardCanEquipClothAndNotPlate(){
        Inventory inventory = new Inventory(wizardPlayer);
        inventory.addItemToNEItems(armor1);
        inventory.addItemToNEItems(plateArmor);
        assertDoesNotThrow( () -> {
            inventory.equipItem(armor1);
        });
        assertThrows(IllegalStateException.class, () -> {
            inventory.equipItem(plateArmor);
        });
    }

    @Test
    public void warriorCanEquipClothAndPlate(){
        Inventory inventory = new Inventory(warriorPlayer);
        inventory.addItemToNEItems(armor2);
        inventory.addItemToNEItems(plateArmor);
        assertDoesNotThrow(() -> {
            inventory.equipItem(armor2);
            inventory.equipItem(plateArmor);
        });
    }
}
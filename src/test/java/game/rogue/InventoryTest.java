package game.rogue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class InventoryTest {
    Player player = new Player(new Warrior(), new Position(5, 5));
    Inventory bag = new Inventory(player);
    GenericItem rock = new GenericItem();
    GenericItem pebble = new GenericItem();
    Armor plateArmor = new Armor("Plate");
    Armor crown = new Armor("Cloth");
    Armor boots = new Armor("Cloth");
    Armor bling = new Armor("plate", 5);

    @BeforeEach
    public void init(){
        player.setInventory(bag);
    }
    
    @Test
    public void hasRequireLevelToEquipItem(){
        //assertFalse(bag.hasRequiredLevel(bling));
        //assertTrue(bag.hasRequiredLevel(boots));
    }

    @Test
    public void wizardCanWearClothItem(){
        Player wizard = new Player(new Wizard(), new Position(12, 3));
        Inventory wizardInventory = new Inventory(wizard);
        wizard.setInventory(wizardInventory);
        wizardInventory.addItemToNEItems(boots);
        assertTrue(wizardInventory.canWear(boots));
    }

    @Test
    public void wizardCanNotWearPlateItem(){
        Player wizard = new Player(new Wizard(), new Position(12, 3));
        Inventory wizardInventory = new Inventory(wizard);
        wizard.setInventory(wizardInventory);
        wizardInventory.addItemToNEItems(plateArmor);
        assertFalse(wizardInventory.canWear(plateArmor));
    }
    @Test
    public void playerCanNotWearItemWithTooHighLevel(){
        bag.addItemToNEItems(bling);
        assertFalse(bag.canWear(bling));
    }

    @Test
    public void itemUnEquipsWhenChangingClass(){
        bag.addItemToNEItems(bling);
        bag.addItemToNEItems(boots);
        player.gainExperience(2000);
        bag.equipItem(bling);
        bag.equipItem(boots);
        player.changeClass(new Wizard());
        assertEquals("[Armor]", bag.getEquippedItems());
    }

    @Test
    public void equipItemThatIsNotWearableThrowsISE(){
        bag.addItemToNEItems(bling);
        assertThrows(IllegalStateException.class, () -> {
            bag.equipItem(bling);
        });
    }


    @Test
    public void newInventoryIsEmpty() {
        assertEquals("[]", bag.getItemsInBag());
    }

    @Test
    public void inventoryWeightCalculatesCorrectly(){
        bag.addItemToNEItems(rock);
        bag.addItemToNEItems(pebble);
        assertEquals(400, bag.getTotalWeight());
    }

    @Test
    public void inventoryContentsReturnsItems(){
        bag.addItemToNEItems(rock);
        bag.addItemToNEItems(pebble);
        assertEquals("[Rock, Rock]", bag.getItemsInBag());
    }

    @Test
    public void removedItemAffectsWeight(){
        GenericItem stick = new GenericItem(20);
        bag.addItemToNEItems(stick);
        GenericItem cobble = new GenericItem(2000);
        bag.addItemToNEItems(cobble);
        bag.removeItemFromNEItems(stick);
        assertEquals(2000, bag.getTotalWeight());
    }

    @Test
    public void equipItemFromBag(){
        bag.addItemToNEItems(plateArmor);
        bag.equipItem(plateArmor);
        assertEquals("[Armor]", bag.getEquippedItems());
    }

    @Test
    public void unequipEquippedItem(){
        bag.addItemToNEItems(plateArmor);
        bag.equipItem(plateArmor);
        bag.unEquipItem(plateArmor);
        assertEquals("[]", bag.getEquippedItems());
    }

    @Test
    public void equipItemNotInBagThrowsISE(){
        assertThrows(IllegalStateException.class, () -> {
            bag.equipItem(plateArmor);
        });
    }

    @Test
    public void unequipItemNotEquippedThrowsISE(){
        assertThrows(IllegalStateException.class, () -> {
            bag.unEquipItem(plateArmor);
        });
    }
    @Test
    public void equipTooManyItemsThrowsISE(){
        bag.addItemToNEItems(plateArmor);
        bag.addItemToNEItems(crown);
        bag.addItemToNEItems(boots);
        bag.addItemToNEItems(bling);
        bag.equipItem(plateArmor);
        bag.equipItem(crown);
        bag.equipItem(boots);
        assertThrows(IllegalStateException.class, () ->{
            bag.equipItem(bling);
        });
    }
}
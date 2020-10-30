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
        assertFalse(bag.hasRequireLevel(bling));
        assertTrue(bag.hasRequireLevel(boots));
    }

    @Test
    public void hasRequirementsForEquipment(){
        assertTrue(bag.hasRequireClass(boots));
        assertTrue(bag.hasRequireClass(plateArmor));
    }

    @Test
    public void itemUnEquipsWhenChangingClass(){
        bag.addItemToInventory(bling);
        bag.addItemToInventory(boots);
        player.setLevel(6);
        bag.equipItem(bling);
        bag.equipItem(boots);
        player.changeClass(new Wizard());
        assertEquals("[Armor]", bag.getEquippedItems());
    }


    @Test
    public void newInventoryIsEmpty() {
        assertEquals("[]", bag.getItemsInBag());
    }

    @Test
    public void inventoryWeightCalculatesCorrectly(){
        bag.addItemToInventory(rock);
        bag.addItemToInventory(pebble);
        assertEquals(400, bag.getTotalWeight());
    }

    @Test
    public void inventoryContentsReturnsItems(){
        bag.addItemToInventory(rock);
        bag.addItemToInventory(pebble);
        assertEquals("[Rock, Rock]", bag.getItemsInBag());
    }

    @Test
    public void removedItemAffectsWeight(){
        GenericItem stick = new GenericItem(20);
        bag.addItemToInventory(stick);
        GenericItem cobble = new GenericItem(2000);
        bag.addItemToInventory(cobble);
        bag.removeItemFromInventory(stick);
        assertEquals(2000, bag.getTotalWeight());
    }

    @Test
    public void equipItemFromBag(){
        bag.addItemToInventory(plateArmor);
        bag.equipItem(plateArmor);
        assertEquals("[Armor]", bag.getEquippedItems());
    }

    @Test
    public void unequipEquippedItem(){
        bag.addItemToInventory(plateArmor);
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
        bag.addItemToInventory(plateArmor);
        bag.addItemToInventory(crown);
        bag.addItemToInventory(boots);
        bag.addItemToInventory(bling);
        bag.equipItem(plateArmor);
        bag.equipItem(crown);
        bag.equipItem(boots);
        assertThrows(IllegalStateException.class, () ->{
            bag.equipItem(bling);
        });
    }
}
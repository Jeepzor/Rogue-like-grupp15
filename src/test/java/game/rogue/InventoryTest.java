package game.rogue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Inventory bag = new Inventory();
    GenericItem rock = new GenericItem();
    GenericItem pebble = new GenericItem();
    Armor plateArmor = new Armor("Plate");

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
}
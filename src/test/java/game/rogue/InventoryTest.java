package game.rogue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    public void newInventoryIsEmpty() {
        Inventory bag = new Inventory();
        assertEquals("[]", bag.getItemsInBag());
    }

    @Test
    public void inventoryWeightCalculatesCorrectly(){
        Inventory bag = new Inventory();
        GenericItem rock = new GenericItem();
        GenericItem pebble = new GenericItem();
        bag.addItemToInventory(rock);
        bag.addItemToInventory(pebble);
        assertEquals(400, bag.getTotalWeight());
    }

    @Test
    public void inventoryContentsReturnsItems(){
        Inventory bag = new Inventory();
        GenericItem rock = new GenericItem();
        GenericItem pebble = new GenericItem();
        bag.addItemToInventory(rock);
        bag.addItemToInventory(pebble);
        assertEquals("[Rock, Rock]", bag.getItemsInBag());
    }

    @Test
    public void removedItemAffectsWeight(){
        Inventory bag = new Inventory();
        GenericItem stick = new GenericItem(20);
        bag.addItemToInventory(stick);
        GenericItem cobble = new GenericItem(2000);
        bag.addItemToInventory(cobble);
        bag.removeItemFromInventory(stick);
        assertEquals(2000, bag.getTotalWeight());
    }

    @Test
    public void equipItemFromBag(){
        Inventory inventory = new Inventory();
        Item plateArmor = new Armor("Plate");
        inventory.addItemToInventory(plateArmor);
        inventory.equipItem(plateArmor);
        assertEquals("[Armor]", inventory.getEquippedItems());
    }

    //här ska jag också göra test som kastar undantag för både equip och unequip.
}
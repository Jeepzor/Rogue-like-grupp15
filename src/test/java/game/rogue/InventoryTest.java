package game.rogue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Inventory bag = new Inventory();
    GenericItem rock = new GenericItem();
    GenericItem pebble = new GenericItem();

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
        Armor plateArmor = new Armor("Plate");
        bag.addItemToInventory(plateArmor);
        bag.equipItem(plateArmor);
        assertEquals("[Armor]", bag.getEquippedItems());
    }

    //här ska jag också göra test som kastar undantag för både equip och unequip.
}
package game.rogue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Player player = new Player(new Wizard(), new Position(5, 5));
    Inventory bag = new Inventory(player);
    GenericItem rock = new GenericItem();
    GenericItem pebble = new GenericItem();
    Armor plateArmor = new Armor("Plate");
    Weapon sword = new Weapon(10, false);
    Armor boots = new Armor("Cloth");
    Armor bling = new Armor("plate");

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
        bag.addItemToInventory(sword);
        bag.addItemToInventory(boots);
        bag.addItemToInventory(bling);
        bag.equipItem(plateArmor);
        bag.equipItem(sword);
        bag.equipItem(boots);
        assertThrows(IllegalStateException.class, () ->{
            bag.equipItem(bling);
        });
    }
}
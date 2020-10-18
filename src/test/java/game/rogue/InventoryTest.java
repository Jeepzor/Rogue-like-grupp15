package game.rogue;

import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {

    @Test
    public void newInventoryIsEmpty() {
        Inventory bag = new Inventory();
        assertEquals("[]", bag.toString());
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
    public void inventoryToStringReturnsItems(){
        Inventory bag = new Inventory();
        GenericItem rock = new GenericItem();
        GenericItem pebble = new GenericItem();
        bag.addItemToInventory(rock);
        bag.addItemToInventory(pebble);
        assertEquals("[Rock, Rock]", bag.toString());
    }
}
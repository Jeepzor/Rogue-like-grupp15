package game.rogue;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }

    public int getTotalWeight() {
        int result = 0;
        for (Item item : inventory) {
            result += item.getWeight();
        }
        return result;
    }

    public ArrayList<Item> getInventory() {
        ArrayList<Item> inventoryCopy = new ArrayList<>();
        inventoryCopy.addAll(inventory);
        return inventoryCopy;
    }

    @Override
    public String toString() {
        return inventory.toString();
    }
}


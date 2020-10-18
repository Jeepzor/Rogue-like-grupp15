package game.rogue;

import java.util.ArrayList;

public class Inventory {
    private  ArrayList<Item> inventory;
    private int weight;
    public void addItemToInventory(Item item){
        inventory.add(item);
        weight += item.getWeight();
    }
    public void removeItemFromInventory(Item item){
        inventory.remove(item);
        weight -= item.getWeight();
    }

    public int getTotalWeight() {
        int result = 0;
        for(Item item : inventory){
            result += item.getWeight();
        }
        return result;
    }
}

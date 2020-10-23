package game.rogue;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> bag;
    private ArrayList<Equipment> equippedItems;
    private Player player;

    public Inventory(Player player) {
        bag = new ArrayList<>();
        equippedItems = new ArrayList<>();
        this.player = player;
    }

    public void addItemToInventory(Item item) {
        bag.add(item);
    }

    public void removeItemFromInventory(Item item) {
        bag.remove(item);
    }
    public void equipItem(Equipment item){
        if (bag.contains(item)){
            bag.remove(item);
            equippedItems.add(item);
        }
        else{
            throw new IllegalStateException("This item is not in player's inventory.");
        }
    }
    public void unEquipItem(Equipment item){
        if(equippedItems.contains(item)){
            equippedItems.remove(item);
            bag.add(item);
        }else{
            throw new IllegalStateException("That item is not equipped.");
        }
    }


    public int getTotalWeight() {
        int result = 0;
        for (Item item : bag) {
            result += item.getWeight();
        }
        return result;
    }
    //getBag() kanske inte ens behövs.
    public ArrayList<Item> getBag() {
        ArrayList<Item> inventoryCopy = new ArrayList<>();
        inventoryCopy.addAll(bag);
        return inventoryCopy;
    }

    public String getItemsInBag() {
        return bag.toString();
    }

    public String getEquippedItems(){
        return equippedItems.toString();
    }
}


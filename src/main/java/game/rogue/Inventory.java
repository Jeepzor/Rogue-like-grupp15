package game.rogue;

import java.util.ArrayList;
import java.util.Iterator;

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
        if(!bag.contains(item)) {
            throw new IllegalStateException("This item is not in player's inventory");
        }
        else if((equippedItems.size() >=3)){
            throw new IllegalStateException("There are too many equipped items");
        }
        else if(!canWear(item)) {
            throw new IllegalStateException("Player can not equip that item");
        }
        else{
            bag.remove(item);
            equippedItems.add(item);
        }
    }


    public void validateEquippedItems(){
       Iterator<Equipment> iterator = equippedItems.iterator();
       while (iterator.hasNext()){
           Equipment item = iterator.next();
            if (!canWear(item)){
                bag.add(item);
                equippedItems.remove(item);
            }
        }
    }

    public boolean canWear(Equipment item){
        return hasRequireLevel(item) && hasRequireClass(item);
    }

    public boolean hasRequireLevel(Equipment item){
        return this.player.getLevel() >= item.getLevelRequirement();
    }

    public boolean hasRequireClass(Equipment item){
        if (item instanceof Armor){
            if (((Armor) item).isPlate()){
                return this.player.canEquipPlate();
            }else if (((Armor) item).isCloth()){
                return this.player.canEquipCloth();
            }
        }else if (item instanceof Weapon) {
            return false; // Temporary
        }
        return false;
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


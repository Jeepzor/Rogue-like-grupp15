package game.rogue;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
    //non-equipped items
    private final ArrayList<Item> nEItems;
    private final ArrayList<Equipment> equippedItems;
    private final Player player;
    private static final int MAX_EQUIPPED_ITEMS = 3;

    public Inventory(Player player) {
        this.nEItems = new ArrayList<>();
        this.equippedItems = new ArrayList<>();
        this.player = player;
    }

    public void addItemToNEItems(Item item) {
        nEItems.add(item);
    }

    public void removeItemFromNEItems(Item item) {
        nEItems.remove(item);
    }

    //Controls that contains item, then checks that equippedItems is not full, then checks that validly can equip item
    //Then equips item and removes it from non-equipped items.
    public void equipItem(Equipment item){
        if(!nEItems.contains(item)) {
            throw new IllegalStateException("This item is not in player's inventory");
        }
        else if((equippedItems.size() >=MAX_EQUIPPED_ITEMS)){
            throw new IllegalStateException("There are too many equipped items");
        }
        else if(!canWear(item)) {
            throw new IllegalStateException("Player can not equip that item");
        }
        else{
            removeItemFromNEItems(item);
            equippedItems.add(item);
        }
    }


    public void unEquipItem(Equipment item){
        if(equippedItems.contains(item)){
            equippedItems.remove(item);
            addItemToNEItems(item);
        }else{
            throw new IllegalStateException("That item is not equipped.");
        }
    }

    //Loops through equipped items and checks if the equipped items still can be equipped. If not they are unequipped.
    public void validateEquippedItems(){
       Iterator<Equipment> iterator = equippedItems.iterator();
       while (iterator.hasNext()){
           Equipment item = iterator.next();
            if (!canWear(item)){
                unEquipItem(item);
            }
        }
    }

    public boolean canWear(Equipment item){
        return hasRequiredLevel(item) && hasRequiredClass(item);
    }

    private boolean hasRequiredLevel(Equipment item){
        return this.player.getLevel() >= item.getLevelRequirement();
    }

    private boolean hasRequiredClass(Equipment item){
        if (item instanceof Armor){
            if (((Armor) item).isPlate()){
                return this.player.canEquipPlate();
            }else if (((Armor) item).isCloth()){
                return this.player.canEquipCloth();
            }
        }
        return false;
    }

    public int getTotalWeight() {
        int result = 0;
        for (Item item : nEItems) {
            result += item.getWeight();
        }
        return result;
    }

    public String itemsNEToString() {
        return nEItems.toString();
    }

    public String equippedItemsToString(){
        return equippedItems.toString();
    }
}


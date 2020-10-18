package game.rogue;

public abstract class Consumable extends Item{
    private int size;

    public abstract void consume(Consumable item);
    public int getSize(){
        return this.size;
    }
}

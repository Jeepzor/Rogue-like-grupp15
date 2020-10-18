package game.rogue;

public abstract class Consumable extends Item{
    public Consumable(int weight, int size){
        super(weight);
        this.size = size;
    }
    private int size;
    public abstract void consume(Consumable item);
    public int getSize(){
        return this.size;
    }
}

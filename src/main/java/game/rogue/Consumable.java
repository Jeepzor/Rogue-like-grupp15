package game.rogue;

public abstract class Consumable extends Item{
    private int size;

    public Consumable(int weight, int size){
        super(weight);
        this.size = size;
    }
    public abstract void consume(Player player);

    public int getSize(){
        return this.size;
    }
}

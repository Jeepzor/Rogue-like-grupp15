package game.rogue;

public abstract class Consumable extends Item{

    public Consumable(int weight){
        super(weight);
    }
    public abstract void consume(Player player);

}

package game.rogue;

public abstract class Item {
    private int weight;

    public Item(int weight){
        this.weight = weight;
    }

    public int getWeight(){
        return this.weight;
    }
}

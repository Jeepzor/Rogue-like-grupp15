package game.rogue;

public class HealthPotion extends Consumable{
    private static final int DEFAULT_WEIGHT = 20;
    private static final int DEFAULT_SIZE = 10;
    private int size;

    public HealthPotion(int size){
        super(DEFAULT_WEIGHT);
        this.size = size;
    }

    public HealthPotion(){
        super(DEFAULT_WEIGHT);
        this.size = DEFAULT_SIZE;
    }

    public void consume(Player player){
        player.gainHitPoints(this.getSize());
    }

    public int getSize(){
        return this.size;
    }
}

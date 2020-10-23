package game.rogue;

public class HealthPotion extends Consumable{
    private static final int DEFAULT_WEIGHT = 20;

    public HealthPotion(int size){
        super(DEFAULT_WEIGHT, size);
    }

    public void consume(Player player){
        player.gainHitPoints(this.getSize());
    }
}

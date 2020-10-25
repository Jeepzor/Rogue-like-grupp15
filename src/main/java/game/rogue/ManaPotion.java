package game.rogue;

public class ManaPotion extends Consumable{
    private static final int DEFAULT_WEIGHT = 20;

    public ManaPotion(int size){
        super(DEFAULT_WEIGHT, size);
    }

    public void consume(Player player){
        player.gainMana(this.getSize());
    }

}

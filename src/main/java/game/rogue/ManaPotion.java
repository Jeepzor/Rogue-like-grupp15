package game.rogue;

import java.awt.font.TextHitInfo;

public class ManaPotion extends Consumable{
    private static final int DEFAULT_WEIGHT = 20;
    private static final int DEFAULT_SIZE = 5;
    private int size;

    public ManaPotion(int size){
        super(DEFAULT_WEIGHT);
        this.size = size;
    }

    public ManaPotion(){
        super(DEFAULT_WEIGHT);
        this.size = DEFAULT_SIZE;
    }

    public void consume(Player player){
        player.gainMana(this.getSize());
    }

    public int getSize(){
        return this.size;
    }

}

package game.rogue;

public class PotionOfKnowledge extends Consumable{
    public static final int DEFAULT_SIZE = 3;
    public static final int SIZE_DIVIDER = 10;
    public static final int DEFAULT_WEIGHT = 20;

    @Override
    public void consume(Player player) {
        player.gainExperience((player.getNextLevelThreshold() * DEFAULT_SIZE/SIZE_DIVIDER));
    }

    public PotionOfKnowledge(){
        super(DEFAULT_WEIGHT, DEFAULT_SIZE);
    }
}

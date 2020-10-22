package game.rogue;

public abstract class Equipment extends Item{
    private final static int DEFAULT_LEVEL_REQUIREMENT = 0;
    private int levelReq;

    public Equipment(int weight, int levelRequirement){
        super(weight);
        this.levelReq = levelRequirement;
    }
    public Equipment(int weight){
        super(weight);
        this.levelReq = DEFAULT_LEVEL_REQUIREMENT;
    }

    public int getLevelRequirement(){
        return this.levelReq;
    }

}

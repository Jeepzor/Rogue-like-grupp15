package game.rogue;

public abstract class Ability {
    private static final int MAX_LEVEL = 5;
    private int level;

    Ability(int level){
        this.level = level;
    }

    public abstract double getManaCost();

    public void incrementLevel(){
        if (this.level < MAX_LEVEL){
            this.level += 1;
        } else{
            throw new IllegalStateException("Ability is already at max level");
        }
    }

    public int getLevel(){
        return this.level;
    }
}

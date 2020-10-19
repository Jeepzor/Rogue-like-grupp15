package game.rogue;

public class Player extends Character{
    private static final int MAX_LEVEL = 100;
    private static final int BASE_LEVEL_UP_THRESHOLD = 100;
    private int level;
    private int experience;
    private int currentHitPoints;
    private PlayerClass playerClass;

    public Player(PlayerClass playerClass, int xPos, int yPos){
        super(xPos, yPos);
        this.playerClass = playerClass;
        this.level = 1;
        this.currentHitPoints = playerClass.getBaseHealth(this.level);
        this.experience = 0;
    }

    public int getMaxHitPoints() {
        return this.playerClass.getBaseHealth(this.level);
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public int getLevel(){
        return this.level;
    }

    public int getExperience() {
        return this.experience;
    }

    /*If the amount is less than the needed XP for next level, simply increment XP by the amount.
      Else set XP to be equal to the leftover */
    public void gainExperience(int amount){
        if (this.experience + amount < getNextLevelThreshold()){
            this.experience += amount;
        }else{
            this.experience = amount - (getNextLevelThreshold() - this.experience);
            levelUp();
        }
    }

    public int getNextLevelThreshold(){
        return BASE_LEVEL_UP_THRESHOLD * this.level;
    }

    public void levelUp(){
        incrementLevel();
        healToMaxHitPoints();
    }

    public void healToMaxHitPoints(){
        this.currentHitPoints = playerClass.getBaseHealth(this.level);
    }
    public void incrementLevel(){
        if (this.level < MAX_LEVEL){
            this.level += 1;
        }
    }
}

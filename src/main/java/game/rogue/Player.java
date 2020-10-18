package game.rogue;

public class Player {
    private static final int MAX_LEVEL = 100;
    private static final int BASE_LEVEL_UP_THRESHOLD = 100;
    private int level;
    private int experience;
    private int currentHitPoints;
    private PlayerClass playerClass;

    public Player(PlayerClass playerClass){
        this.playerClass = playerClass;
        this.currentHitPoints = playerClass.getBaseHealth();
        this.level = 1;
        this.experience = 0;
    }

    public int getMaxHitPoints() {
        return this.playerClass.getBaseHealth();
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
            incrementLevel();
        }
    }

    public int getNextLevelThreshold(){
        return BASE_LEVEL_UP_THRESHOLD * this.level;
    }

    public void incrementLevel(){
        if (this.level < MAX_LEVEL){
            this.level += 1;
        }
    }
}

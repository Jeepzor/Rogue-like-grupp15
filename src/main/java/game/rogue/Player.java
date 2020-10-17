package game.rogue;

public class Player {
    private static final int MAX_LEVEL = 100;
    private static final int BASE_LEVEL_UP_AMOUNT = 100;
    private int level;
    private int experiencePoints;

    public Player(){
        this.level = 1;
        this.experiencePoints = 0;
    }

    public int getLevel(){
        return this.level;
    }

    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    /*If the amount is less than the needed XP for next level, simply increment XP by the amount.
      Else set XP to be equal to the leftover */
    public void gainExperience(int amount){
        if (this.experiencePoints + amount < getExperienceToNextLevel()){
            this.experiencePoints += amount;
        }else{
            this.experiencePoints = amount - (getExperienceToNextLevel() - this.experiencePoints);
            incrementLevel();
        }
    }

    public int getExperienceToNextLevel(){
        return BASE_LEVEL_UP_AMOUNT * this.level;
    }

    public void incrementLevel(){
        this.level += 1;
    }
}

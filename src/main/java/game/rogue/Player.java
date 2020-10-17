package game.rogue;

public class Player {
    private static final int MAX_LEVEL = 100;
    private static final int BASE_LEVEL_UP_AMOUNT = 100;
    private int level;
    private int experience;

    public Player(){
        this.level = 1;
        this.experience = 0;
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
        if (this.experience + amount < getNextLevelTreshold()){
            this.experience += amount;
        }else{
            this.experience = amount - (getNextLevelTreshold() - this.experience);
            incrementLevel();
        }
    }

    public int getNextLevelTreshold(){
        return BASE_LEVEL_UP_AMOUNT * this.level;
    }

    public void incrementLevel(){
        this.level += 1;
    }
}

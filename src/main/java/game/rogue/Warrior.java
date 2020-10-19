package game.rogue;

public class Warrior extends PlayerClass{
    private final int baseHealth = 20;
    private final int baseMana = 5;

    @Override
    public int getMaxHealth(int level) {
        return this.baseHealth * level;
    }

    @Override
    public int getMaxMana(int level) {
        return this.baseHealth * level;
    }

    @Override
    public boolean canEquipCloth(){
        return true;
    }

    @Override
    public boolean canEquipPlate(){
        return true;
    }

    @Override
    public String toString(){
        return "Class: Warrior";
    }
}

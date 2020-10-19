package game.rogue;

public class Warrior extends PlayerClass{
    private final int baseHealth;
    private final int baseMana;

    Warrior(){
        this.baseHealth = 20;
        this.baseMana = 5;
    }

    @Override
    public int getBaseHealth(int level) {
        return this.baseHealth * level;
    }

    @Override
    public int getBaseMana(int level) {
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

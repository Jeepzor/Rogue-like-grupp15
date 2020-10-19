package game.rogue;

public class Wizard extends PlayerClass{
    private final int baseHealth;
    private final int baseMana;

    Wizard(){
        this.baseHealth = 5;
        this.baseMana = 10;
    }
    @Override
    public int getBaseHealth(int level) {
        return this.baseHealth * level;
    }

    @Override
    public int getBaseMana(int level) {
        return this.baseMana * level;
    }

    @Override
    public boolean canEquipCloth(){
        return true;
    }

    @Override
    public boolean canEquipPlate(){
        return false;
    }

    @Override
    public String toString(){
        return "Class: Wizard";
    }
}

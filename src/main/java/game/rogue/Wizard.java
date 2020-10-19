package game.rogue;

public class Wizard extends PlayerClass{
    private int baseHealth = 10;
    private int baseMana = 5;

    public int getMaxHealth(int level) {
        return this.baseHealth * level;
    }

    @Override
    public int getMaxMana(int level) {
        return this.baseMana * level;
    }

    @Override
    public boolean canEquipCloth(){
        return true;
    }

    @Override
    public String toString(){
        return "Class: Wizard";
    }
}

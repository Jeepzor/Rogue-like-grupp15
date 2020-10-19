package game.rogue;

public class Wizard extends PlayerClass{
    private static final int BASE_HEALTH = 10;
    private static final int BASE_MANA = 20;

    @Override
    public int getBaseHealth() {
        return BASE_HEALTH;
    }

    @Override
    public int getBaseMana() {
        return BASE_MANA;
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

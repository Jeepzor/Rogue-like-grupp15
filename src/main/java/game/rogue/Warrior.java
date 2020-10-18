package game.rogue;

public class Warrior extends PlayerClass{
    private static final int BASE_HEALTH = 20;
    private static final int BASE_MANA = 5;

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
        return true;
    }

    @Override
    public String toString(){
        return "Class: Warrior";
    }
}

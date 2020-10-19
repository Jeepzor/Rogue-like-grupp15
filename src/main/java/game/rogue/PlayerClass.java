package game.rogue;

public abstract class PlayerClass {
    private int baseHealth;
    private int baseMana;

    public abstract int getBaseHealth(int level);

    public abstract int getBaseMana(int level);

    public boolean canEquipCloth(){
        return false;
    }

    public boolean canEquipPlate(){
        return false;
    }
}

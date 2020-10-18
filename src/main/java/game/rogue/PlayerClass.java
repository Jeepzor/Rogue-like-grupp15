package game.rogue;

public abstract class PlayerClass {
    private int baseHealth;
    private int baseMana;

    public abstract int getBaseHealth();

    public abstract int getBaseMana();

    public boolean canEquipCloth(){
        return false;
    }

    public boolean canEquipPlate(){
        return false;
    }
}

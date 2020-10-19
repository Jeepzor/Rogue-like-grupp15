package game.rogue;

public abstract class PlayerClass {
    private int baseHealth;
    private int baseMana;

    public abstract int getMaxHealth(int level);

    public abstract int getMaxMana(int level);

    public boolean canEquipCloth(){
        return false;
    }

    public boolean canEquipPlate(){
        return false;
    }
}

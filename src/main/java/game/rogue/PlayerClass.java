package game.rogue;

public abstract class PlayerClass {
    private double baseHealth;
    private double baseMana;
    private double baseDamage;
    private double damagePerLevel;

    public abstract double getMaxHitPoints(int level);

    public abstract double getMaxMana(int level);

    public abstract double getDamage(int level);

    public boolean canEquipCloth(){
        return false;
    }

    public boolean canEquipPlate(){
        return false;
    }


}

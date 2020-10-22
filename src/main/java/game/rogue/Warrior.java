package game.rogue;

public class Warrior extends PlayerClass{
    private final double baseHitPoints = 20;
    private final double baseMana = 5;

    @Override
    public double getMaxHitPoints(int level) {
        return this.baseHitPoints * level;
    }

    @Override
    public double getMaxMana(int level) {
        return this.baseMana;
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

    @Override
    public boolean equals(Object other) {
        return other instanceof Warrior;
    }

}

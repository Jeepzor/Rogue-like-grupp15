package game.rogue;

public class Wizard extends PlayerClass{
    private double baseHitPoints = 10;
    private double baseMana = 20;

    public double getMaxHitPoints(int level) {
        return this.baseHitPoints * level;
    }

    @Override
    public double getMaxMana(int level) {
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

    @Override
    public boolean equals(Object other) {
        return other instanceof Wizard;
    }
}

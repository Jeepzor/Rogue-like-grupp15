package game.rogue;

public class Wizard extends PlayerClass{
    private int baseHitPoints = 10;
    private int baseMana = 20;

    public int getMaxHitPoints(int level) {
        return this.baseHitPoints * level;
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

    @Override
    public boolean equals(Object other) {
        return other instanceof Wizard;
    }
}

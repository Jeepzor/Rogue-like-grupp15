package game.rogue;

import java.util.Objects;

public class Wizard extends PlayerClass{
    private double baseHitPoints = 10;
    private double baseMana = 20;
    private double baseDamage = 2;
    private double damagePerLevel = 1;

    public double getMaxHitPoints(int level) {
        if (level <= 0){
            throw new IllegalArgumentException("Level can't be negative or zero!");
        }
        return this.baseHitPoints * level;
    }

    @Override
    public double getMaxMana(int level) {
        if (level <= 0){
            throw new IllegalArgumentException("Level can't be negative or zero!");
        }
        return this.baseMana * level;
    }

    @Override
    public boolean canEquipCloth(){
        return true;
    }

    @Override
    public double getDamage(int level){
        if (level <= 0){
            throw new IllegalArgumentException("Level can't be negative or zero!");
        }
        return baseDamage + damagePerLevel * (level - 1);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Wizard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseHitPoints, baseMana, baseDamage, damagePerLevel);
    }

    @Override
    public String toString(){
        return "Class: Wizard";
    }
}

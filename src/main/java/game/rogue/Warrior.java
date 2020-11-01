package game.rogue;

import java.util.Objects;

public class Warrior extends PlayerClass{
    private final double baseHitPoints = 20;
    private final double baseMana = 5;
    private final double baseDamage = 3;
    private final double damagePerLevel = 1.5;

    @Override
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
        return this.baseMana;
    }

    @Override
    public boolean canEquipPlate(){
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Warrior;
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseHitPoints, baseMana, baseDamage, damagePerLevel);
    }

    @Override
    public double getDamage(int level){
        if (level <= 0){
            throw new IllegalArgumentException("Level can't be negative or zero!");
        }
        return baseDamage + damagePerLevel * (level - 1);
    }

    @Override
    public String toString(){
        return "Class: Warrior";
    }

}

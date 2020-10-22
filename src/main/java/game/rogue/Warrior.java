package game.rogue;

public class Warrior extends PlayerClass{
    private final int baseHitPoints = 20;
    private final int baseMana = 5;

    @Override
    public int getMaxHitPoints(int level) {
        return this.baseHitPoints * level;
    }

    @Override
    public int getMaxMana(int level) {
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
        if (other instanceof Warrior){
            return true;
        }else{
            return false;
        }
    }

}

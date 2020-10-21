package game.rogue;

public class Warrior extends PlayerClass{
    private final int baseHealth = 20;
    private final int baseMana = 5;

    public Warrior(){

    }

    @Override
    public int getMaxHealth(int level) {
        return this.baseHealth * level;
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

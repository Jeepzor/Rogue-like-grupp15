package game.rogue;

public class Weapon extends Item{
    private static final int DEFAULT_WEIGHT = 800;
    private int damage;
    private boolean twoHanded;

    public Weapon (int damage, boolean twoHanded){
        super(DEFAULT_WEIGHT);
        this.damage = damage;
        this.twoHanded = twoHanded;
    }

    public Weapon(int damage, int customWeight, boolean twoHanded){
        super(customWeight);
        this.damage = damage;
        this.twoHanded = twoHanded;
    }

    public int getDamage(){
        return this.damage;
    }
    public boolean isTwoHanded(){
        return this.twoHanded;
    }
}

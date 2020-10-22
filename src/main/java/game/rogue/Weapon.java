package game.rogue;

public class Weapon extends Item{
    private static final int DEFAULT_WEIGHT = 800;
    private int damage;

    public Weapon (int damage){
        super(DEFAULT_WEIGHT);
        this.damage = damage;
    }

    public int getDamage(){
        return this.damage;
    }
}

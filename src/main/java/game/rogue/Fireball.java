package game.rogue;

public class Fireball extends Ability{
    private static final double  BASE_DAMAGE = 3;
    private static final double DAMAGE_PER_LEVEL = 2.5;

    private int level;
    public Fireball(int level) {
        super(level);
    }

    public double getDamageValue(){
        return BASE_DAMAGE + DAMAGE_PER_LEVEL * (getLevel() - 1);
    }
}

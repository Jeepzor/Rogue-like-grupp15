package game.rogue;

import java.util.Objects;

public class Fireball extends Ability{
    private static final double BASE_DAMAGE = 3;
    private static final double DAMAGE_PER_LEVEL = 2.5;
    private static final double MANA_COST = 8;
    private static final double MANA_COST_PER_LEVEL = -1;
    private static final PlayerClass REQUIRED_CLASS = new Wizard();

    private int level;
    public Fireball(int level) {
        super(level);
    }

    public double getDamageValue(){
        return BASE_DAMAGE + DAMAGE_PER_LEVEL * (getLevel() - 1);
    }

    public double getManaCost(){
        return MANA_COST + MANA_COST_PER_LEVEL * (getLevel() - 1);
    }

    public boolean hasRequiredClass(PlayerClass playerClass){
        return playerClass.equals(REQUIRED_CLASS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Fireball;
    }

    @Override
    public int hashCode() {
        return Objects.hash(BASE_DAMAGE, DAMAGE_PER_LEVEL, MANA_COST, MANA_COST_PER_LEVEL);
    }
}

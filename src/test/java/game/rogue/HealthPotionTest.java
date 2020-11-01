package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthPotionTest {

    HealthPotion pot = new HealthPotion(8);
    @Test
    public void healthPotionConstructorTest(){
        assertEquals(8, pot.getSize());
    }

    @Test
    public void healthPotionConstructorDefault(){
        HealthPotion hpPot = new HealthPotion();
        assertEquals(10, hpPot.getSize());
    }

    @Test
    public void restoresPlayerHealthWhenConsumed(){
        Warrior warrior = new Warrior();
        Player player = new Player(warrior, new Position(5, 5));
        player.takeDamage(8);
        assertEquals(player.getMaxHitPoints()-8, player.getCurrentHitPoints());
        pot.consume(player);
        assertEquals(player.getMaxHitPoints(), player.getCurrentHitPoints());
    }

}
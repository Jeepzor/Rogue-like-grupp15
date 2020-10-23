package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthPotionTest {

    HealthPotion pot = new HealthPotion(10);
    @Test
    public void healthPotionConstructorTest(){
        assertEquals(10, pot.getSize());
    }

    @Test
    public void restoresPlayerHealthWhenConsumed(){
        Warrior warrior = new Warrior();
        Player player = new Player(warrior, new Position(5, 5));
        player.takeDamage(10);
        assertEquals(player.getMaxHitPoints()-10, player.getCurrentHitPoints());
        pot.consume(player);
        assertEquals(player.getMaxHitPoints(), player.getCurrentHitPoints());
    }

}
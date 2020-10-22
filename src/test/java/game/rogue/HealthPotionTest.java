package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthPotionTest {

    @Test
    public void healthPotionConstructorTest(){
        HealthPotion pot = new HealthPotion(10);
        assertEquals(10, pot.getSize());
    }

}
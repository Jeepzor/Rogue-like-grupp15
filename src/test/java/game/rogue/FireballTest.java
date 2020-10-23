package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireballTest {
    Fireball fireball = new Fireball(1);
    @Test
    void levelUpTest() {
        for(int i = 1; i < 5; i++){
            fireball.incrementLevel();
            assertEquals(i + 1, fireball.getLevel());
        }
        assertThrows(IllegalStateException.class, () -> fireball.incrementLevel());
    }

    @Test
    void getDamageValue() {
        assertEquals(3, fireball.getDamageValue());
        fireball.incrementLevel();

        assertEquals(5.5, fireball.getDamageValue());
    }
}
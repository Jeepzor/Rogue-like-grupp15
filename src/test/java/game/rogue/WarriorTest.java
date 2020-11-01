package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    PlayerClass warrior = new Warrior();
    PlayerClass warrior2 = new Warrior();
    PlayerClass wizard = new Wizard();


    @Test
    void getMaxHitPoints() {
        assertTrue(warrior.getMaxMana(1) == 5);
        assertTrue(warrior.getMaxMana(10) == 5);
    }

    @Test
    void getMaxHitPointsNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getMaxMana(-1));
    }

    @Test
    void getMaxHitPointsLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getMaxMana(0));
    }

    @Test
    void getMana() {
        assertTrue(warrior.getMaxHitPoints(1) == 20);
        assertTrue(warrior.getMaxHitPoints(10) == 200);
    }

    @Test
    void getManaNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getMaxHitPoints(-1));
    }

    @Test
    void getManaLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getMaxHitPoints(0));
    }

    @Test
    void canEquipCloth() {
        assertTrue(warrior.canEquipCloth());
    }

    @Test
    void cantEquipPlate() {
        assertTrue(warrior.canEquipPlate());
    }

    @Test
    void getDamage() {
        assertTrue(warrior.getDamage(1) == 3);
        assertTrue(warrior.getDamage(10) == 16.5);
    }

    @Test
    void getDamageNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getDamage(-1));
    }

    @Test
    void getDamageLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getDamage(0));
    }

    @Test
    void testEquals() {
        assertTrue(warrior.equals(warrior2));
    }

    @Test
    void testNotEquals() {
        assertFalse(warrior.equals(wizard));
    }

    @Test
    void testHashCode() {
        assertTrue(warrior.hashCode() == warrior2.hashCode());
        assertFalse(warrior.hashCode() == wizard.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(warrior.toString(), "Class: Warrior");
    }
}
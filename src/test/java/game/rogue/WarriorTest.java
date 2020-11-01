package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {

    PlayerClass warrior = new Warrior();
    PlayerClass warrior2 = new Warrior();
    PlayerClass wizard = new Wizard();


    @Test
    public void getMaxHitPoints() {
        assertTrue(warrior.getMaxMana(1) == 5);
        assertTrue(warrior.getMaxMana(10) == 5);
    }

    @Test
    public void getMaxHitPointsNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getMaxMana(-1));
    }

    @Test
    public void getMaxHitPointsLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getMaxMana(0));
    }

    @Test
    public void getMana() {
        assertTrue(warrior.getMaxHitPoints(1) == 20);
        assertTrue(warrior.getMaxHitPoints(10) == 200);
    }

    @Test
    public void getManaNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getMaxHitPoints(-1));
    }

    @Test
    public void getManaLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getMaxHitPoints(0));
    }

    @Test
    public void canEquipCloth() {
        assertTrue(warrior.canEquipCloth());
    }

    @Test
    public void cantEquipPlate() {
        assertTrue(warrior.canEquipPlate());
    }

    @Test
    public void getDamage() {
        assertTrue(warrior.getDamage(1) == 3);
        assertTrue(warrior.getDamage(10) == 16.5);
    }

    @Test
    public void getDamageNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getDamage(-1));
    }

    @Test
    public void getDamageLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> warrior.getDamage(0));
    }

    @Test
    public void testEquals() {
        assertTrue(warrior.equals(warrior2));
    }

    @Test
    public void testNotEquals() {
        assertFalse(warrior.equals(wizard));
    }

    @Test
    public void testHashCode() {
        assertTrue(warrior.hashCode() == warrior2.hashCode());
        assertFalse(warrior.hashCode() == wizard.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(warrior.toString(), "Class: Warrior");
    }
}
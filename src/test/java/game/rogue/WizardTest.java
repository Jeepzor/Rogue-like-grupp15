package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {

    PlayerClass wizard = new Wizard();
    PlayerClass wizard2 = new Wizard();
    PlayerClass warrior = new Warrior();

    @Test
    void getMaxHitPoints() {
        assertTrue(wizard.getMaxMana(1) == 20);
        assertTrue(wizard.getMaxMana(10) == 200);
    }

    @Test
    void getMaxHitPointsNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getMaxMana(-1));
    }

    @Test
    void getMaxHitPointsLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getMaxMana(0));
    }

    @Test
    void getMana() {
        assertTrue(wizard.getMaxHitPoints(1) == 10);
        assertTrue(wizard.getMaxHitPoints(10) == 100);
    }

    @Test
    void getManaNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getMaxHitPoints(-1));
    }

    @Test
    void getManaLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getMaxHitPoints(0));
    }

    @Test
    void canEquipCloth() {
        assertTrue(wizard.canEquipCloth());
    }

    @Test
    void cantEquipPlate() {
        assertFalse(wizard.canEquipPlate());
    }

    @Test
    void getDamage() {
        assertTrue(wizard.getDamage(1) == 2);
        assertTrue(wizard.getDamage(10) == 11);
    }

    @Test
    void getDamageNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getDamage(-1));
    }

    @Test
    void getDamageLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getDamage(0));
    }

    @Test
    void testEquals() {
        assertTrue(wizard.equals(wizard2));
    }

    @Test
    void testNotEquals() {
        assertFalse(wizard.equals(warrior));
    }

    @Test
    void testHashCode() {
        assertTrue(wizard.hashCode() == wizard2.hashCode());
        assertFalse(wizard.hashCode() == warrior.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(wizard.toString(), "Class: Wizard");
    }
}
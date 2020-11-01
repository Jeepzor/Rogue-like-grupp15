package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {

    PlayerClass wizard = new Wizard();
    PlayerClass wizard2 = new Wizard();
    PlayerClass warrior = new Warrior();

    @Test
    public void getMaxHitPoints() {
        assertTrue(wizard.getMaxMana(1) == 20);
        assertTrue(wizard.getMaxMana(10) == 200);
    }

    @Test
    public void getMaxHitPointsNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getMaxMana(-1));
    }

    @Test
    public void getMaxHitPointsLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getMaxMana(0));
    }

    @Test
    public void getMana() {
        assertTrue(wizard.getMaxHitPoints(1) == 10);
        assertTrue(wizard.getMaxHitPoints(10) == 100);
    }

    @Test
    public void getManaNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getMaxHitPoints(-1));
    }

    @Test
    public void getManaLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getMaxHitPoints(0));
    }

    @Test
    public void canEquipCloth() {
        assertTrue(wizard.canEquipCloth());
    }

    @Test
    public void cantEquipPlate() {
        assertFalse(wizard.canEquipPlate());
    }

    @Test
    public void getDamage() {
        assertTrue(wizard.getDamage(1) == 2);
        assertTrue(wizard.getDamage(10) == 11);
    }

    @Test
    public void getDamageNegativeLevel() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getDamage(-1));
    }

    @Test
    public void getDamageLevelZero() {
        assertThrows(IllegalArgumentException.class, () -> wizard.getDamage(0));
    }

    @Test
    public void testEquals() {
        assertTrue(wizard.equals(wizard2));
    }

    @Test
    public void testNotEquals() {
        assertFalse(wizard.equals(warrior));
    }

    @Test
    public void testHashCode() {
        assertTrue(wizard.hashCode() == wizard2.hashCode());
        assertFalse(wizard.hashCode() == warrior.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(wizard.toString(), "Class: Wizard");
    }
}
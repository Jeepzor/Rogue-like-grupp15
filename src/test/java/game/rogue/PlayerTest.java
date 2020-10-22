package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    PlayerClass warrior = new Warrior();
    Player player = new Player(warrior, new Position(10, 10));

    //Class
    @Test
    public void correctClassStartHitPoints() {
        assertEquals(20, player.getMaxHitPoints());
        PlayerClass wizard = new Wizard();
        Player wizardPlayer = new Player(wizard, new Position(10, 10));
        assertEquals(10, wizardPlayer.getMaxHitPoints());
    }

    @Test
    public void correctClassStartMana() {
        assertEquals(5, player.getMaxMana());
        PlayerClass wizard = new Wizard();
        Player wizardPlayer = new Player(wizard, new Position(10, 10));
        assertEquals(20, wizardPlayer.getMaxMana());
    }

    @Test
    public void testWizardArmor(){
        PlayerClass wizard = new Wizard();
        Player wizardPlayer = new Player(wizard, new Position(10, 10));
        assertTrue(wizardPlayer.canEquipCloth());
        assertFalse(wizardPlayer.canEquipPlate());
    }

    @Test
    public void testWarriorArmor(){
        assertTrue(player.canEquipCloth());
        assertTrue(player.canEquipPlate());
    }

    @Test
    public void changeClass() {
        PlayerClass wizard = new Wizard();
        PlayerClass wizard2 = new Wizard();
        assertThrows(IllegalStateException.class, () -> player.changeClass(wizard));
        for(int i = 1; i < 15; i++){
            player.incrementLevel();
        }
        assertEquals(15, player.getLevel());
        player.changeClass(wizard);
        assertEquals(10, player.getLevel());
        assertThrows(IllegalArgumentException.class, () -> player.changeClass(wizard2));
    }

    // Health and damage
    @Test
    public void takeDamage() {
        assertEquals(20, player.getCurrentHitPoints());
        player.takeDamage(5);
        assertEquals(15, player.getCurrentHitPoints());
        assertTrue(player.isAlive());
    }

    @Test
    public void takeNegativeDamage() {
        assertThrows(IllegalArgumentException.class, () -> player.takeDamage(-1));
    }

    @Test
    public void takeFatalDamage() {
        assertEquals(20, player.getCurrentHitPoints());
        assertTrue(player.isAlive());
        player.takeDamage(25);
        assertEquals(0, player.getCurrentHitPoints());
        assertFalse(player.isAlive());
    }

    @Test
    public void gainHitPoints(){
        player.takeDamage(5);
        assertEquals(15, player.getCurrentHitPoints());
        player.gainHitPoints(5);
        assertEquals(20, player.getCurrentHitPoints());
    }

    @Test
    public void gainHitPointsAboveMaxLife(){
        player.takeDamage(5);
        assertEquals(15, player.getCurrentHitPoints());
        player.gainHitPoints(10);
        assertEquals(20, player.getCurrentHitPoints());
    }

    @Test
    public void gainHitPointsAtMaxLife(){
        assertThrows(IllegalStateException.class, () -> player.gainHitPoints(10));
    }

    @Test
    public void negativeHeal(){
        assertThrows(IllegalArgumentException.class, () -> player.gainHitPoints(-1));
    }

    @Test
    public void zeroHeal(){
        assertThrows(IllegalArgumentException.class, () -> player.gainHitPoints(0));
    }


    //Mana
    @Test
    public void gainMana(){
        player.spendMana(4);
        assertEquals(1, player.getCurrentMana());
        player.gainMana(1);
        assertEquals(2, player.getCurrentMana());
    }

    @Test
    public void spendNegativeMana(){
        assertThrows(IllegalArgumentException.class, () -> player.spendMana(-1));
    }

    @Test
    public void cantAffordMana(){
        assertThrows(IllegalStateException.class, () -> player.spendMana(player.getMaxMana() + 1));
    }

    @Test
    public void canSpendAllMana(){
        player.spendMana(player.getMaxMana());
        assertEquals(0, player.getCurrentMana());
    }

    @Test
    public void gainManaAboveMax(){
        player.spendMana(2);
        assertEquals(3, player.getCurrentMana());
        player.gainMana(10);
        assertEquals(5, player.getCurrentMana());
    }

    @Test
    public void gainManaAtMaxLife(){
        assertThrows(IllegalStateException.class, () -> player.gainMana(10));
    }

    @Test
    public void negativeManaGain(){
        assertThrows(IllegalArgumentException.class, () -> player.gainMana(-1));
    }

    @Test
    public void zeroManaGain(){
        assertThrows(IllegalArgumentException.class, () -> player.gainMana(0));
    }

    //Experience & Level
    @Test
    public void gainExperience() {
        player.gainExperience(80);
        assertEquals(80, player.getExperience());
    }

    @Test
    public void gainMoreExperienceThanNextLevel(){
        player.gainExperience(160);
        assertEquals(60, player.getExperience());
    }

    @Test
    public void gainZeroExperience() {
        assertThrows(IllegalArgumentException.class, () -> player.gainExperience(0));
    }

    @Test
    public void gainNegativeExperience() {
        assertThrows(IllegalArgumentException.class, () -> player.gainExperience(-1));
    }

    @Test
    public void getExperienceToNextLevel() {
        assertEquals(100, player.getNextLevelThreshold());
        player.gainExperience(150);
        assertEquals(200, player.getNextLevelThreshold());
    }

    @Test
    public void levelUp() {
        player.takeDamage(5);
        player.spendMana(3);
        player.levelUp();
        assertEquals(2, player.getLevel());
        assertEquals(player.getMaxHitPoints(), player.getCurrentHitPoints());
        assertEquals(player.getMaxMana(), player.getCurrentMana());
    }

    @Test
    public void incrementLevel() {
        for(int i = 1; i < 99; i++){
            player.incrementLevel();
        }
        assertEquals(99, player.getLevel());
        player.incrementLevel();
        assertEquals(100, player.getLevel());
        player.incrementLevel();
        assertEquals(100, player.getLevel());
    }
}
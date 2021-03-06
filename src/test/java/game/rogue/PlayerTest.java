package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    PlayerClass warrior = new Warrior();
    PlayerClass warrior2 = new Warrior();
    Player playerWarrior = new Player(warrior, new Position(10, 10));
    Inventory playerInventory = new Inventory(playerWarrior);

    PlayerClass wizard = new Wizard();
    PlayerClass wizard2 = new Wizard();
    Player playerWizard = new Player(wizard, new Position(10, 10));


    //Class
    @Test
    public void playerHasNoInventory(){
        assertThrows(IllegalStateException.class, () -> playerWarrior.getInventory());
    }

    @Test
    public void playerHasNoClass(){
        assertThrows(IllegalArgumentException.class, () ->  new Player(null, new Position(10, 10)));
    }

    @Test
    public void addInventory(){
        playerWarrior.setInventory(playerInventory);
        assertEquals(playerWarrior.getInventory(),playerInventory);
    }

    @Test
    public void correctClassStartHitPoints() {
        assertEquals(20, playerWarrior.getMaxHitPoints());
        assertEquals(10, playerWizard.getMaxHitPoints());
    }

    @Test
    public void correctClassStartMana() {
        assertEquals(5, playerWarrior.getMaxMana());
        assertEquals(20, playerWizard.getMaxMana());
    }

    @Test
    public void correctClassStartDamage() {
        assertEquals(3, playerWarrior.getDamageValue());
        assertEquals(2, playerWizard.getDamageValue());
    }

    @Test
    public void correctPlayerToString(){
        assertEquals("Player - Class: Warrior - Level: 1", playerWarrior.toString());
        assertEquals("Player - Class: Wizard - Level: 1", playerWizard.toString());
    }

    @Test
    public void testWizardArmor(){
        assertTrue(playerWizard.canEquipCloth());
        assertFalse(playerWizard.canEquipPlate());
    }

    @Test
    public void testWarriorArmor(){
        assertTrue(playerWarrior.canEquipCloth());
        assertTrue(playerWarrior.canEquipPlate());
    }

    @Test
    public void changeClassSucessfully() {
        for(int i = 1; i < 15; i++){
            playerWarrior.incrementLevel();
        }
        assertEquals(15, playerWarrior.getLevel());
        playerWarrior.changeClass(wizard);
        assertEquals(10, playerWarrior.getLevel());
    }

    @Test
    public void changeToSameClass() {
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.changeClass(warrior2));
        assertThrows(IllegalArgumentException.class, () -> playerWizard.changeClass(wizard2));
    }

    @Test
    public void changeClassTooLowLevel() {
        for(int i = 1; i < 5; i++){
            playerWarrior.incrementLevel();
        }
        assertEquals(5, playerWarrior.getLevel());
        assertThrows(IllegalStateException.class, () -> playerWarrior.changeClass(wizard));
    }

    // Health and damage
    @Test
    public void takeDamage() {
        assertEquals(20, playerWarrior.getCurrentHitPoints());
        playerWarrior.takeDamage(5);
        assertEquals(15, playerWarrior.getCurrentHitPoints());
        assertTrue(playerWarrior.isAlive());
    }

    @Test
    public void takeNegativeDamage() {
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.takeDamage(-1));
    }

    @Test
    public void takeFatalDamage() {
        assertEquals(20, playerWarrior.getCurrentHitPoints());
        assertTrue(playerWarrior.isAlive());
        playerWarrior.takeDamage(25);
        assertEquals(0, playerWarrior.getCurrentHitPoints());
        assertFalse(playerWarrior.isAlive());
    }

    @Test
    public void gainHitPoints(){
        playerWarrior.takeDamage(5);
        assertEquals(15, playerWarrior.getCurrentHitPoints());
        playerWarrior.gainHitPoints(5);
        assertEquals(20, playerWarrior.getCurrentHitPoints());
    }

    @Test
    public void gainHitPointsAboveMaxLife(){
        playerWarrior.takeDamage(5);
        assertEquals(15, playerWarrior.getCurrentHitPoints());
        playerWarrior.gainHitPoints(10);
        assertEquals(20, playerWarrior.getCurrentHitPoints());
    }

    @Test
    public void gainHitPointsAtMaxLife(){
        assertThrows(IllegalStateException.class, () -> playerWarrior.gainHitPoints(10));
    }

    @Test
    public void gainNegativeHitPoints(){
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.gainHitPoints(-1));
    }

    @Test
    public void gainZeroHitPoints(){
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.gainHitPoints(0));
    }

    //Mana
    @Test
    public void gainMana(){
        playerWarrior.spendMana(4);
        assertEquals(1, playerWarrior.getCurrentMana());
        playerWarrior.gainMana(1);
        assertEquals(2, playerWarrior.getCurrentMana());
    }

    @Test
    public void spendNegativeMana(){
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.spendMana(-1));
    }

    @Test
    public void cantAffordMana(){
        assertThrows(IllegalStateException.class, () -> playerWarrior.spendMana(playerWarrior.getMaxMana() + 1));
    }

    @Test
    public void canSpendAllMana(){
        playerWarrior.spendMana(playerWarrior.getMaxMana());
        assertEquals(0, playerWarrior.getCurrentMana());
    }

    @Test
    public void gainManaAboveMax(){
        playerWarrior.spendMana(2);
        assertEquals(3, playerWarrior.getCurrentMana());
        playerWarrior.gainMana(10);
        assertEquals(5, playerWarrior.getCurrentMana());
    }

    @Test
    public void gainManaAtMaxLife(){
        assertThrows(IllegalStateException.class, () -> playerWarrior.gainMana(10));
    }

    @Test
    public void negativeManaGain(){
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.gainMana(-1));
    }

    @Test
    public void zeroManaGain(){
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.gainMana(0));
    }

    //Experience & Level
    @Test
    public void gainExperience() {
        playerWarrior.gainExperience(80);
        assertEquals(80, playerWarrior.getExperience());
    }

    @Test
    public void gainMoreExperienceThanNextLevel(){
        playerWarrior.gainExperience(160);
        assertEquals(60, playerWarrior.getExperience());
    }

    @Test
    public void gainZeroExperience() {
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.gainExperience(0));
    }

    @Test
    public void gainNegativeExperience() {
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.gainExperience(-1));
    }

    @Test
    public void gainExperienceWorthMultipleLevels(){
        assertEquals(1, playerWarrior.getLevel());
        playerWarrior.gainExperience(650);
        assertEquals(4, playerWarrior.getLevel());
    }
    // 100 , 200, 300
    @Test
    public void getExperienceToNextLevel() {
        assertEquals(100, playerWarrior.getNextLevelThreshold());
        playerWarrior.gainExperience(150);
        assertEquals(200, playerWarrior.getNextLevelThreshold());
    }

    @Test
    public void levelUp() {
        playerWarrior.takeDamage(5);
        playerWarrior.spendMana(3);
        playerWarrior.levelUp();
        assertEquals(2, playerWarrior.getLevel());
        assertEquals(playerWarrior.getMaxHitPoints(), playerWarrior.getCurrentHitPoints());
        assertEquals(playerWarrior.getMaxMana(), playerWarrior.getCurrentMana());
    }

    @Test
    public void incrementLevel() {
        for(int i = 1; i < 99; i++){
            playerWarrior.incrementLevel();
        }
        assertEquals(99, playerWarrior.getLevel());
        playerWarrior.incrementLevel();
        assertEquals(100, playerWarrior.getLevel());
        playerWarrior.incrementLevel();
        assertEquals(100, playerWarrior.getLevel());
    }
}
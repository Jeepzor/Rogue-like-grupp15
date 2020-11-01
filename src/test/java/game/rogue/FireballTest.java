package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireballTest {
    Fireball fireball = new Fireball(1);
    Fireball fireball2 = new Fireball(1);

    PlayerClass wizard = new Wizard();
    PlayerClass warrior = new Warrior();
    Player playerWizard = new Player(wizard, new Position(10, 10));
    Player playerWarrior = new Player(warrior, new Position(10, 10));

    @Test
    public void createHigherThanMaxLevel(){
        Fireball fireball3 = new Fireball(6);
        assertEquals(5, fireball3.getLevel());
    }

    @Test
    public void levelUpTest() {
        for(int i = 1; i < 5; i++){
            fireball.incrementLevel();
            assertEquals(i + 1, fireball.getLevel());
        }
        assertThrows(IllegalStateException.class, () -> fireball.incrementLevel());
    }

  

    @Test
    public void getDamageValue() {
        assertEquals(3, fireball.getDamageValue());
        fireball.incrementLevel();

        assertEquals(5.5, fireball.getDamageValue());
    }

    @Test
    public void getManaCost() {
        assertEquals(8, fireball.getManaCost());
        fireball.incrementLevel();
        assertEquals(7, fireball.getManaCost());
    }

    @Test
    public void testHashCode(){
        assertEquals(fireball.hashCode(), fireball2.hashCode());
    }

    @Test
    public void gainNewAbility(){
        Fireball fireball = new Fireball(1);
        playerWizard.gainAbility(fireball);
        assertEquals(1, playerWizard.getAmountOfAbilities());
        assertThrows(IllegalArgumentException.class, () -> playerWarrior.gainAbility(fireball));
    }

    @Test
    public void hasRequiredClass() {
        Wizard playerWizard = new Wizard();
        Warrior playerWarrior = new Warrior();
        assertTrue(fireball.hasRequiredClass(playerWizard));
        assertFalse(fireball.hasRequiredClass(playerWarrior));
    }

    @Test
    public void doNotGainAbilityIfItAlreadyExists(){
        playerWizard.gainAbility(fireball);
        assertEquals(1, playerWizard.getAmountOfAbilities());
        playerWizard.gainAbility(fireball2);
        assertEquals(1, playerWizard.getAmountOfAbilities());
        assertEquals(2, fireball.getLevel());
    }

    @Test
    public void loseAbilityOnClassChange(){
        playerWizard.gainExperience(5000);
        Fireball fireball = new Fireball(1);
        playerWizard.gainAbility(fireball);
        assertEquals(1, playerWizard.getAmountOfAbilities());
        playerWizard.changeClass(new Warrior());
        assertEquals(0, playerWizard.getAmountOfAbilities());
    }
}
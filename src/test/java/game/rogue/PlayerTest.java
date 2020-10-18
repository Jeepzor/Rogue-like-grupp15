package game.rogue;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    PlayerClass warrior = new Warrior();
    Player player = new Player(warrior);

    @Test
    public void getHealth() {
        assertEquals(20, player.getHealth());

        PlayerClass wizard = new Wizard();
        Player wizardPlayer = new Player(wizard);
        assertEquals(10, wizardPlayer.getHealth());
    }

    @Test
    public void gainExperience() {
        player.gainExperience(80);
        assertEquals(80, player.getExperience());
        player.gainExperience(80);
        assertEquals(60, player.getExperience());
    }

    @Test
    public void getExperienceToNextLevel() {
        assertEquals(100, player.getNextLevelTreshold());
        player.gainExperience(150);
        assertEquals(200, player.getNextLevelTreshold());
    }

    @Test
    public void levelUp() {
        player.gainExperience(150);
        assertEquals(2, player.getLevel());
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
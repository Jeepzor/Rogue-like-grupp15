package game.rogue;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player = new Player();

    @Test
    public void gainExperience() {
        player.gainExperience(80);
        assertEquals(80, player.getExperiencePoints());
        player.gainExperience(80);
        assertEquals(60, player.getExperiencePoints());
    }

    @Test
    public void getExperienceToNextLevel() {
        assertEquals(100, player.getExperienceToNextLevel());
        player.gainExperience(150);
        assertEquals(200, player.getExperienceToNextLevel());
    }

    @Test
    public void incrementLevel() {
        player.gainExperience(150);
        assertEquals(2, player.getLevel());
    }
}
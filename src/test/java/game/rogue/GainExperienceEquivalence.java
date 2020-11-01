package game.rogue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GainExperienceEquivalence {
    PlayerClass warrior = new Warrior();
    Player playerWarrior = new Player(warrior, new Position(10, 10));

    /*
        1. Gain experience, no lvl up
        2. Gain experience, lvl up, remaining xp > 0
        3. Gain experience, lvl up, remaining xp = 0
        4. Gain 0 experience
        5. Gain < 0 experience
        6. Gain experience worth multiple levels
        7. Gain experience that results in becoming max level with a remainder
        8. Gain experience when max level
        9. Gain experience that results in becoming max level with no remainder

        Valid:
        1
        2 & 6
        3 & 9
        7 & 8

        Invalid:
        4 & 5
     */

    @Test
    public void gainExperienceNoLevelUp(){
        playerWarrior.gainExperience(55);
        assertEquals(55, playerWarrior.getExperience());
        assertEquals(1, playerWarrior.getLevel());
    }

    @Test
    public void gainExperienceMultipleLevelsWithRemainder(){
        playerWarrior.gainExperience(350);
        assertEquals(50, playerWarrior.getExperience());
        assertEquals(3, playerWarrior.getLevel());
    }

    @Test
    public void gainExperienceToMaxLevelNoRemainder(){
        while(playerWarrior.getLevel() < 100){
            playerWarrior.incrementLevel();
        }

        playerWarrior.gainExperience(9800);
        assertEquals(0, playerWarrior.getExperience());
        assertEquals(100, playerWarrior.getLevel());
    }

    @Test
    public void gainExperienceToMaxLevelWithRemainder(){
        while(playerWarrior.getLevel() < 100){
            playerWarrior.incrementLevel();
        }

        playerWarrior.gainExperience(10000);
        assertEquals(0, playerWarrior.getExperience());
        assertEquals(100, playerWarrior.getLevel());
    }

    @Test
    public void gainZeroExperienceCastsError(){
        assertThrows(IllegalArgumentException.class, () -> {
            playerWarrior.gainExperience(0);
        });
    }

    @Test
    public void gainNegativeExperienceCastsError(){
        assertThrows(IllegalArgumentException.class, () -> {
            playerWarrior.gainExperience(-1);
        });
    }
}

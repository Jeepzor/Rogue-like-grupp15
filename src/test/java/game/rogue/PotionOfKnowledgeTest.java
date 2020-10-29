package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PotionOfKnowledgeTest {
    PotionOfKnowledge pot = new PotionOfKnowledge();
    Player player = new Player(new Wizard(), new Position(20,51));

    @Test
    public void expGainIsCorrect(){
        pot.consume(player);
        assertEquals(30, player.getExperience());
    }

    @Test
    public void expGainIsCorrectForHigherLevels(){
        player.levelUp();
        player.levelUp();
        pot.consume(player);
        assertEquals(player.getNextLevelThreshold() * 0.3, player.getExperience());
    }
}
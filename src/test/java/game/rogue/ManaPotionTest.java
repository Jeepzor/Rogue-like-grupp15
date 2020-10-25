package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManaPotionTest {

    @Test
    public void manaPotionRestoresCorrectAmountOfMana(){
        Player player = new Player(new Wizard(), new Position(5, 5));
        ManaPotion manaPot = new ManaPotion(5);
        player.spendMana(6);
        assertEquals(player.getCurrentMana(), player.getMaxMana()-6);
        manaPot.consume(player);
        assertEquals(player.getCurrentMana(), player.getMaxMana()-1);
    }

}
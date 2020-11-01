package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManaPotionTest {

    @Test
    public void manaPotionRestoresCorrectAmountOfMana(){
        Player player = new Player(new Wizard(), new Position(5, 5));
        ManaPotion manaPot = new ManaPotion(7);
        player.spendMana(8);
        assertEquals(player.getCurrentMana(), player.getMaxMana()-8);
        manaPot.consume(player);
        assertEquals(player.getCurrentMana(), player.getMaxMana()-1);
    }
    @Test
    public void manaPotionWithDefaultValues(){
        ManaPotion mpPot = new ManaPotion();
        assertEquals(5, mpPot.getSize());
    }

}
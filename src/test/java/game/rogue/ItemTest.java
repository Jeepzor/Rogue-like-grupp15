package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void noParamSetsDefaultWeight(){
        GenericItem rock = new GenericItem();
        assertEquals(200, rock.getWeight());
    }
    @Test
    public void specificParamSetsSpecificWeight(){
        GenericItem boulder = new GenericItem(50000);
        assertEquals(50000, boulder.getWeight());
    }

}
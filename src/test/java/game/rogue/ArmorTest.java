package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    @Test
    public void constructorRecognizesInput(){
        Armor armor = new Armor("PlAte");
        assertEquals(10, armor.getDefense());
        assertEquals(200, armor.getWeight());
    }

    @Test
    public void clothSetsClothValues(){
        Armor robe = new Armor("cLoTH");
        assertEquals(3, robe.getDefense());
    }


    @Test
    public void neitherPlateNorClothThrowsIAE(){
        assertThrows(IllegalArgumentException.class, () -> {
            Armor leatherArmor = new Armor("leather");
        });
    }

    @Test
    public void armorWithSpecificWeight(){
        Armor heavyArmor = new Armor("plate", 950);
        assertEquals(950, heavyArmor.getWeight());
    }

    @Test
    public void plateArmorisPlate(){
        Armor heavyArmor = new Armor("Plate");
        assertTrue(heavyArmor.isPlate());
    }

    @Test
    public void clothArmorisCloth(){
        Armor shirt = new Armor("Cloth");
        assertTrue(shirt.isCloth());
    }



}
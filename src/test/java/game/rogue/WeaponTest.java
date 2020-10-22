package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    public void weaponDamageTest(){
        Weapon weapon = new Weapon(5);
        assertEquals(5, weapon.getDamage());
        assertEquals(800, weapon.getWeight());
    }

}
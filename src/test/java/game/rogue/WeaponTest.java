package game.rogue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    public void weaponDefaultWeightDamageTest(){
        Weapon weapon = new Weapon(5, false);
        assertEquals(5, weapon.getDamage());
        assertEquals(800, weapon.getWeight());
        assertEquals(false, weapon.isTwoHanded());
    }

    @Test
    public void weaponCustomWeightTest(){
        Weapon heavyWeapon = new Weapon(10, 2400, true);
        assertEquals(2400, heavyWeapon.getWeight());
    }

}
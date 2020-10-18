package game.rogue;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyTest {
	@Test
	public void createEnemyWithValidValues() {
		Enemy e = new Enemy(100, false, 20, 80);
		assertEquals(100, e.getMaxHitPoints());
		assertEquals(100, e.getCurrentHitPoints());
		assertFalse(e.isAggressive());
		assertEquals(20, e.getXPos());
		assertEquals(80, e.getYPos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void creatingEnemyWithLessThan100HitPointsThrowsIAE() {
		new Enemy(99, true, 40, 60);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creatingEnemyWithMoreThan1000HitPointsThrowsIAE() {
		new Enemy(1001, false, 25, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creatingEnemyWithXPosBelow0ThrowsIAE() {
		new Enemy(101, true, -1, 95);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creatingEnemyWithYPosBelow0ThrowsIAE() {
		new Enemy(1000, false, 75, -1);
	}
}

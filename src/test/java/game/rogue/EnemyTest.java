package game.rogue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnemyTest {

	private Enemy DEFAULT_ENEMY;

	@Before
	public void createDefaultEnemy() {
		DEFAULT_ENEMY = new Enemy(250, true, 50, 75);
	}

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

	@Test
	public void damageTakenReducesHitPoints() {
		DEFAULT_ENEMY.damage(15);
		assertEquals(250, DEFAULT_ENEMY.getMaxHitPoints());
		assertEquals(235, DEFAULT_ENEMY.getCurrentHitPoints());
	}

	@Test
	public void damageTakenIsBelow0() {
		final int currentHitPoints = DEFAULT_ENEMY.getCurrentHitPoints();
		DEFAULT_ENEMY.damage(-1);
		assertEquals(currentHitPoints, DEFAULT_ENEMY.getCurrentHitPoints());
	}

	@Test
	public void damageTakenIsMoreThanCurrentHitPointsKillsEnemy() {
		DEFAULT_ENEMY.damage(DEFAULT_ENEMY.getCurrentHitPoints() + 1);
		assertTrue(DEFAULT_ENEMY.isDead());
	}

	@Test
	public void enemyMovesBy5xAnd10Y() {
		int currentXPos = DEFAULT_ENEMY.getXPos();
		int currentYPos = DEFAULT_ENEMY.getYPos();
		DEFAULT_ENEMY.move(5, 10);
		assertEquals(currentXPos + 5, DEFAULT_ENEMY.getXPos());
		assertEquals(currentYPos + 10, DEFAULT_ENEMY.getYPos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void enemyMovesOutsideTheMapOnXThrowsIAE() {
		DEFAULT_ENEMY.move(-(DEFAULT_ENEMY.getXPos() + 1), 15);
	}

	@Test(expected = IllegalArgumentException.class)
	public void enemyMovesOutsideTheMapOnYThrowsIAE() {
		DEFAULT_ENEMY.move(20, -(DEFAULT_ENEMY.getYPos() + 1));
	}

	@Test
	public void enemyDetectsPlayerWhenInArea() {
		Player p = new Player(new Warrior(), 65, 90);
		assertTrue(DEFAULT_ENEMY.hasPlayerInArea(p));
	}

	@Test
	public void enemyDoesNotDetectPlayerWhenYNotInRange() {
		Player p = new Player(new Warrior(), 45, 91);
		assertFalse(DEFAULT_ENEMY.hasPlayerInArea(p));
	}

	@Test
	public void enemyDoesNotFindPlayerWhenXNotInRange() {
	Player p = new Player(new Warrior(), 34, 80);
		assertFalse(DEFAULT_ENEMY.hasPlayerInArea(p));
	}

	@Test
	public void enemyMovesRandomly() {
		for (int i = 0; i < 1000; i++) {
			Enemy e = new Enemy(500, true, 50, 75);
			int currentX = e.getXPos();
			int currentY = e.getYPos();

			e.moveRandomly();
			
			boolean newXIsWithinRange = currentX > e.getXPos() ? currentX - e.getXPos() <= 30
					: e.getXPos() - currentX <= 30;
			boolean newYIsWithinRange = currentY > e.getYPos() ? currentY - e.getYPos() <= 30
					: e.getYPos() - currentY <= 30;
			
			assertTrue(newXIsWithinRange);
			assertTrue(newYIsWithinRange);
		}
	}

}

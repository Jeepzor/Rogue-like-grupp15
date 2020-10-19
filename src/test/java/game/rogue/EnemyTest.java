package game.rogue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnemyTest {

	private Enemy DEFAULT_ENEMY;
	private Player DEFAULT_PLAYER;
	private World DEFAULT_WORLD;

	@Before
	public void createDefaultEnemy() {
		DEFAULT_ENEMY = new Enemy(250, true, new Position(50, 75));
		DEFAULT_PLAYER = new Player(new Warrior(), new Position(65, 90));
	}
	
	@Before
	public void createAndPopulateWorld() {
		DEFAULT_WORLD = new World(5000, 7500, DEFAULT_PLAYER);
	}
	

	@Test
	public void createEnemyWithValidValues() {
		Enemy e = new Enemy(100, false, new Position(20, 80));
		assertEquals(100, e.getMaxHitPoints());
		assertEquals(100, e.getCurrentHitPoints());
		assertFalse(e.isAggressive());
		assertEquals(20, e.getPosition().getX());
		assertEquals(80, e.getPosition().getY());
	}

	@Test(expected = IllegalArgumentException.class)
	public void creatingEnemyWithLessThan100HitPointsThrowsIAE() {
		new Enemy(99, true, new Position(40, 60));
	}

	@Test(expected = IllegalArgumentException.class)
	public void creatingEnemyWithMoreThan1000HitPointsThrowsIAE() {
		new Enemy(1001, false, new Position(25, 10));
	}

	@Test(expected = IllegalArgumentException.class)
	public void creatingEnemyWithXPosBelow0ThrowsIAE() {
		new Enemy(101, true, new Position(-1, 95));
	}

	@Test(expected = IllegalArgumentException.class)
	public void creatingEnemyWithYPosBelow0ThrowsIAE() {
		new Enemy(1000, false, new Position(75, -1));
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
		int currentXPos = DEFAULT_ENEMY.getPosition().getX();
		int currentYPos = DEFAULT_ENEMY.getPosition().getY();
		DEFAULT_ENEMY.move(DEFAULT_WORLD, 5, 10);
		assertEquals(currentXPos + 5, DEFAULT_ENEMY.getPosition().getX());
		assertEquals(currentYPos + 10, DEFAULT_ENEMY.getPosition().getY());
	}

	@Test(expected = IllegalArgumentException.class)
	public void enemyMovesOutsideTheMapOnXThrowsIAE() {
		DEFAULT_ENEMY.move(DEFAULT_WORLD, -(DEFAULT_ENEMY.getPosition().getX() + 1), 15);
	}

	@Test(expected = IllegalArgumentException.class)
	public void enemyMovesOutsideTheMapOnYThrowsIAE() {
		DEFAULT_ENEMY.move(DEFAULT_WORLD, 20, -(DEFAULT_ENEMY.getPosition().getY() + 1));
	}

	@Test
	public void enemyDetectsPlayerWhenInArea() {
		World w = new World(5000, 7500, DEFAULT_PLAYER);
		assertTrue(DEFAULT_ENEMY.hasPlayerInArea(w));
	}

	@Test
	public void enemyDoesNotDetectPlayerWhenYNotInRange() {
		World w = new World(5000, 7500, new Player(new Warrior(), new Position(45, 150)));
		assertFalse(DEFAULT_ENEMY.hasPlayerInArea(w));
	}

	@Test
	public void enemyDoesNotFindPlayerWhenXNotInRange() {
		World w = new World(5000, 7500, new Player(new Warrior(), new Position(10, 70)));
		assertFalse(DEFAULT_ENEMY.hasPlayerInArea(w));
	}

	@Test
	public void aggressiveEnemyAttacksPlayerWhenMovesIntoPlayer() {
		DEFAULT_ENEMY.move(DEFAULT_WORLD, 10, 5);
		assertTrue(DEFAULT_ENEMY.isInCombat());
	}
	
	@Test
	public void aggressIveEnemyDoesNotAttackPlayerWhenDoesNotMoveIntoPlayer() {
		DEFAULT_ENEMY.move(DEFAULT_WORLD, -40, -30);
		assertFalse(DEFAULT_ENEMY.isInCombat());
	}
	
	@Test
	public void enemyMovesRandomly() {
		for (int i = 0; i < 1000; i++) {
			Enemy e = new Enemy(500, true, new Position(50, 75));
			int currentX = e.getPosition().getX();
			int currentY = e.getPosition().getY();

			e.moveRandomly(DEFAULT_WORLD);
			
			boolean newXIsWithinRange = currentX > e.getPosition().getX() ? currentX - e.getPosition().getX() <= 30
					: e.getPosition().getX() - currentX <= 30;
			boolean newYIsWithinRange = currentY > e.getPosition().getY() ? currentY - e.getPosition().getY() <= 30
					: e.getPosition().getY()- currentY <= 30;
			
			assertTrue(newXIsWithinRange);
			assertTrue(newYIsWithinRange);
		}
	}

}

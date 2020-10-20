package game.rogue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

	private Enemy DEFAULT_ENEMY;
	private Player DEFAULT_PLAYER;
	private World DEFAULT_WORLD;

	@Before
	public void setUp() {
		DEFAULT_ENEMY = new Enemy(250, true, new Position(50, 75));
		DEFAULT_PLAYER = new Player(new Warrior(), new Position(65, 90));
		DEFAULT_WORLD = new World(5000, 7500, DEFAULT_PLAYER);
	}

	@Test
	public void createEnemyWithValidValues() {
		assertEquals(250, DEFAULT_ENEMY.getMaxHitPoints());
		assertEquals(250, DEFAULT_ENEMY.getCurrentHitPoints());
		assertTrue(DEFAULT_ENEMY.isAggressive());
		assertEquals(50, DEFAULT_ENEMY.getPosition().getX());
		assertEquals(75, DEFAULT_ENEMY.getPosition().getY());
	}

	@Test
	public void creatingEnemyWithLessThan100HitPointsThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(99, true, new Position(40, 60));
		});
	}

	@Test
	public void creatingEnemyWithMoreThan1000HitPointsThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(1001, false, new Position(25, 10));
		});

	}

	@Test
	public void creatingEnemyWithNullPositionThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(500, true, null);
		});
	}

	@Test
	public void creatingEnemyWithXPosBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(101, true, new Position(-1, 95));
		});

	}

	@Test
	public void creatingEnemyWithYPosBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(1000, false, new Position(75, -1));
		});
	}

	@Test
	public void damageTakenReducesHitPoints() {
		DEFAULT_ENEMY.damage(15);
		assertEquals(250, DEFAULT_ENEMY.getMaxHitPoints());
		assertEquals(235, DEFAULT_ENEMY.getCurrentHitPoints());

		DEFAULT_ENEMY.damage(35);
		assertEquals(200, DEFAULT_ENEMY.getCurrentHitPoints());
		assertEquals(250, DEFAULT_ENEMY.getMaxHitPoints());
	}

	@Test
	public void damageTakenIsBelow0DoesNothing() {
		final int currentHitPoints = DEFAULT_ENEMY.getCurrentHitPoints();
		final int maxHitPoints = DEFAULT_ENEMY.getMaxHitPoints();

		DEFAULT_ENEMY.damage(-1);
		assertEquals(currentHitPoints, DEFAULT_ENEMY.getCurrentHitPoints());
		assertEquals(maxHitPoints, DEFAULT_ENEMY.getMaxHitPoints());
	}

	@Test
	public void damageTakenIsMoreThanCurrentHitPointsKillsEnemy() {
		DEFAULT_ENEMY.damage(DEFAULT_ENEMY.getCurrentHitPoints() + 1);
		assertFalse(DEFAULT_ENEMY.isAlive());
	}

	@Test
	public void enemyMovesBy5XAnd10Y() {
		int currentX = DEFAULT_ENEMY.getPosition().getX();
		int currentY = DEFAULT_ENEMY.getPosition().getY();

		DEFAULT_ENEMY.move(DEFAULT_WORLD, 5, 10);
		assertEquals(currentX + 5, DEFAULT_ENEMY.getPosition().getX());
		assertEquals(currentY + 10, DEFAULT_ENEMY.getPosition().getY());
	}

	@Test
	public void enemyTriesToMoveOutsideTheMapOnXStopsAtEdge() {
		final int currentX = DEFAULT_ENEMY.getPosition().getX();
		final int currentY = DEFAULT_ENEMY.getPosition().getY();

		DEFAULT_ENEMY.move(DEFAULT_WORLD, -(currentX + 1), 15);
		assertEquals(0, DEFAULT_ENEMY.getPosition().getX());
		assertEquals(currentY + 15, DEFAULT_ENEMY.getPosition().getY());
	}

	@Test
	public void enemyTriesToMoveOutsideTheMapOnYStopsAtEdge() {
		final int currentX = DEFAULT_ENEMY.getPosition().getX();
		final int currentY = DEFAULT_ENEMY.getPosition().getY();

		DEFAULT_ENEMY.move(DEFAULT_WORLD, 10, -(currentY + 1));
		assertEquals(0, DEFAULT_ENEMY.getPosition().getY());
		assertEquals(currentX + 10, DEFAULT_ENEMY.getPosition().getX());
	}

	@Test
	public void enemyTriesToMoveOutsideTheMapOnXAndY() {
		final int currentX = DEFAULT_ENEMY.getPosition().getX();
		final int currentY = DEFAULT_ENEMY.getPosition().getY();

		DEFAULT_ENEMY.move(DEFAULT_WORLD, -(currentX + 1), -(currentY + 1));
		assertEquals(0, DEFAULT_ENEMY.getPosition().getX());
		assertEquals(0, DEFAULT_ENEMY.getPosition().getY());
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
					: e.getPosition().getY() - currentY <= 30;

			assertTrue(newXIsWithinRange);
			assertTrue(newYIsWithinRange);
		}
	}

	@Test
	public void enemyDetectsPlayerWhenInArea() {
		assertTrue(DEFAULT_ENEMY.hasPlayerInArea(DEFAULT_WORLD));
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
	public void enemyDoesNotFindPlayerWhenXAndYNotInRange() {
		World w = new World(5000, 6500, new Player(new Warrior(), new Position(250, 350)));
		assertFalse(DEFAULT_ENEMY.hasPlayerInArea(w));
	}

	@Test
	public void enemyDetectsPlayerAfterMovingCloseToIt() {
		World w = new World(5000, 7500, new Player(new Warrior(), new Position(100, 100)));
		assertFalse(DEFAULT_ENEMY.hasPlayerInArea(w));
		DEFAULT_ENEMY.move(w, 35, 10);
		assertTrue(DEFAULT_ENEMY.hasPlayerInArea(w));
	}

	@Test
	public void aggressiveEnemyAttacksPlayerWhenMovesInRange() {
		World w = new World(5000, 7500, new Player(new Warrior(), new Position(100, 100)));
		assertFalse(DEFAULT_ENEMY.hasPlayerInArea(w));
		assertFalse(DEFAULT_ENEMY.isInCombat());

		DEFAULT_ENEMY.move(w, 50, 30);
		assertTrue(DEFAULT_ENEMY.hasPlayerInArea(w));
		assertTrue(DEFAULT_ENEMY.isInCombat());
	}
	
	@Test
	public void nonAggressiveEnemyDoesNotAttackPlayerWhenMovesIntoRange() {
		World w = new World(5000, 7500, new Player(new Warrior(), new Position(100, 100)));
		Enemy e = new Enemy(300, false, new Position(50, 50));
		assertFalse(e.hasPlayerInArea(w));
		assertFalse(e.isInCombat());
		
		e.move(w, 45, 40);
		assertTrue(e.hasPlayerInArea(w));
		assertFalse(e.isInCombat());
	}

}

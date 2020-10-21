package game.rogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

	private Enemy DEFAULT_ENEMY;
	private Player DEFAULT_PLAYER;
	private World DEFAULT_WORLD;

	@BeforeEach
	public void setUp() {
		DEFAULT_ENEMY = new Enemy(250, true, new Position(50, 75), 5, 2);
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
		assertEquals(5, DEFAULT_ENEMY.getStrength());
		assertEquals(2, DEFAULT_ENEMY.getLevel());
	}

	@Test
	public void creatingEnemyWithLessThan10HPThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(9, true, new Position(40, 60), 6, 3);
		});
	}

	@Test
	public void creatingEnemyWithMoreThan1000HPThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(1001, false, new Position(25, 10), 1, 6);
		});

	}

	@Test
	public void creatingEnemyWithNullPositionThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(500, true, null, 5, 10);
		});
	}

	@Test
	public void creatingEnemyWithXPosBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(101, true, new Position(-1, 95), 10, 5);
		});

	}

	@Test
	public void creatingEnemyWithYPosBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(1000, false, new Position(75, -1), 15, 25);
		});
	}

	@Test
	public void creatingEnemyWithLessTHan1StrengthThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(250, true, new Position(80, 20), 0, 1);
		});
	}

	@Test
	public void creatingEnemyWithMoreTHan200StrengthThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(250, true, new Position(80, 20), 201, 3);
		});
	}

	@Test
	public void creatingEnemyWithLessTHanLevel1ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(250, true, new Position(80, 20), 100, 0);
		});
	}

	@Test
	public void creatingEnemyWithMoreTHanLevel100ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(250, true, new Position(80, 20), 50, 101);
		});
	}

	@Test
	public void damageTakenReducesHP() {
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
	public void damageTakenIsMoreThanCurrentHPKillsEnemy() {
		DEFAULT_ENEMY.damage(DEFAULT_ENEMY.getCurrentHitPoints() + 1);
		assertFalse(DEFAULT_ENEMY.isAlive());
	}

	@Test
	public void enemyHealsFrom80To100PercentHP() {
		DEFAULT_ENEMY.damage((int) (0.15 * DEFAULT_ENEMY.getMaxHitPoints()));
		DEFAULT_ENEMY.heal((int) (0.15 * DEFAULT_ENEMY.getMaxHitPoints()));
		assertTrue(DEFAULT_ENEMY.getCurrentHitPoints() == DEFAULT_ENEMY.getMaxHitPoints());
	}

	@Test
	public void enemyHealsFrom80To90PercentHP() {
		DEFAULT_ENEMY.damage((int) (0.2 * DEFAULT_ENEMY.getMaxHitPoints()));
		DEFAULT_ENEMY.heal((int) (0.1 * DEFAULT_ENEMY.getMaxHitPoints()));
		assertTrue(DEFAULT_ENEMY.getCurrentHitPoints() == (DEFAULT_ENEMY.getMaxHitPoints() * 0.9));
	}

	@Test
	public void enemyHealsAboveMaxHitPointsStopAtMaxHitPoints() {
		DEFAULT_ENEMY.damage((int) (0.1 * DEFAULT_ENEMY.getMaxHitPoints()));
		DEFAULT_ENEMY.heal((int) (0.2 * DEFAULT_ENEMY.getMaxHitPoints()));
		assertTrue(DEFAULT_ENEMY.getCurrentHitPoints() == DEFAULT_ENEMY.getMaxHitPoints());
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
			Enemy e = new Enemy(500, true, new Position(50, 75), 25, 30);
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
		Enemy e = new Enemy(300, false, new Position(50, 50), 1, 3);
		assertFalse(e.hasPlayerInArea(w));
		assertFalse(e.isInCombat());

		e.move(w, 45, 40);
		assertTrue(e.hasPlayerInArea(w));
		assertFalse(e.isInCombat());
	}

	@Test
	public void attackPlayerReducesItsHP() {
		final int playersCurrentHP = DEFAULT_PLAYER.getCurrentHitPoints();

		DEFAULT_ENEMY.attack(DEFAULT_PLAYER);
		assertEquals(playersCurrentHP - DEFAULT_ENEMY.getStrength(), DEFAULT_PLAYER.getCurrentHitPoints());
	}
}

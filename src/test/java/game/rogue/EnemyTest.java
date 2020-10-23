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
		DEFAULT_ENEMY = new Enemy(2, true, new Position(50, 75));
		DEFAULT_PLAYER = new Player(new Warrior(), new Position(65, 90));
		DEFAULT_WORLD = new World(5000, 7500, DEFAULT_PLAYER);
	}

	@Test
	public void constructorSetsCorrectHitPoints() {
		Enemy e = new Enemy(1, true, new Position(1, 1));
		final double hitPoints = 2 * Math.sqrt(1);
		assertEquals(hitPoints, e.getMaxHitPoints());
		assertEquals(hitPoints, e.getCurrentHitPoints());
	}

	@Test
	public void contstructorSetsAggressive() {
		Enemy e = new Enemy(100, true, new Position(25, 15));
		assertTrue(e.isAggressive());
	}
	
	@Test
	public void constructorSetsNonAggressive() {
		Enemy e = new Enemy(99, false, new Position(100, 10));
		assertFalse(e.isAggressive());
	}

	@Test
	public void constructorSetsCorrectPosition() {
		Enemy e = new Enemy(50, true, new Position(1200, 2670));
		assertEquals(1200, e.getPosition().getX());
		assertEquals(2670, e.getPosition().getY());
	}

	@Test
	public void constructorSetsCorrectLevel() {
		Enemy e = new Enemy(100, false, new Position(0, 0));
		assertEquals(100, e.getLevel());
	}
	
	@Test
	public void constructorSetsCorrectStrength() {
		Enemy e = new Enemy(2, false, new Position(25, 100));
		assertEquals(4, e.getStrength());
	}

	@Test
	public void creatingEnemyWithNullPositionThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(10, true, null);
		});
	}

	@Test
	public void creatingEnemyWithXPosBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(5, true, new Position(-1, 95));
		});
	}

	@Test
	public void creatingEnemyWithYPosBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(25, false, new Position(75, -1));
		});
	}

	@Test
	public void creatingEnemyWithLessTHanLevel1ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(0, true, new Position(80, 20));
		});
	}

	@Test
	public void creatingEnemyWithMoreTHanLevel100ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(101, true, new Position(80, 20));
		});
	}
	
	@Test
	public void damageTakenReducesHP() {
		final double maxHP = DEFAULT_ENEMY.getMaxHitPoints();
		double currentHP = maxHP;
		DEFAULT_ENEMY.damage(maxHP * 0.5);
		currentHP -= maxHP * 0.5;
		assertEquals(maxHP, DEFAULT_ENEMY.getMaxHitPoints());
		assertEquals(currentHP, DEFAULT_ENEMY.getCurrentHitPoints());

		DEFAULT_ENEMY.damage(maxHP * 0.1);
		currentHP -= maxHP * 0.1;
		assertEquals(currentHP, DEFAULT_ENEMY.getCurrentHitPoints());
		assertEquals(maxHP, DEFAULT_ENEMY.getMaxHitPoints());
	}
	
	@Test
	public void damageTakenIsBelow0DoesNothing() {
		final double currentHitPoints = DEFAULT_ENEMY.getCurrentHitPoints();
		final double maxHitPoints = DEFAULT_ENEMY.getMaxHitPoints();

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
	public void enemyHealsToFullHP() {
		DEFAULT_ENEMY.damage(10);
		DEFAULT_ENEMY.heal(10);
		assertTrue(DEFAULT_ENEMY.getCurrentHitPoints() == DEFAULT_ENEMY.getMaxHitPoints());
	}

	@Test
	public void enemyHealsButNotToFullHP() {
		DEFAULT_ENEMY.damage(6);
		DEFAULT_ENEMY.heal(5);
		assertTrue(DEFAULT_ENEMY.getCurrentHitPoints() == (DEFAULT_ENEMY.getMaxHitPoints() - 1));
	}

	@Test
	public void enemyHealsAboveMaxHitPointsStopAtMaxHitPoints() {
		DEFAULT_ENEMY.damage(100);
		DEFAULT_ENEMY.heal(101);
		assertTrue(DEFAULT_ENEMY.getCurrentHitPoints() == DEFAULT_ENEMY.getMaxHitPoints());
	}

	@Test
	public void enemyHealsWithLessThan0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			DEFAULT_ENEMY.heal(-1);
		});
	}

	@Test
	public void enemyHealsWhenAlreadyAtFullHPThrowsIAE() {
		assertEquals(DEFAULT_ENEMY.getCurrentHitPoints(), DEFAULT_ENEMY.getMaxHitPoints());
		assertThrows(IllegalArgumentException.class, () -> {
			DEFAULT_ENEMY.heal(1);
		});
	}
	
	@Test
	public void enemyIsAliveAbove0HitPoints() {
		assertTrue(DEFAULT_ENEMY.isAlive());
	}
	
	@Test
	public void enemyIsNotAliveAt0HitPoints() {
		Enemy e = new Enemy(3, true, new Position(20, 25));
		e.damage(e.getMaxHitPoints());
		assertFalse(e.isAlive());
	}
	
	@Test
	public void enemyIsNotAliveAtBelow0HitPoints() {
		Enemy e = new Enemy(3, true, new Position(20, 25));
		e.damage(e.getMaxHitPoints() + 1);
		assertFalse(e.isAlive());		
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
			Enemy e = new Enemy(30, true, new Position(50, 75));
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
		Enemy e = new Enemy(3, false, new Position(50, 50));
		assertFalse(e.hasPlayerInArea(w));
		assertFalse(e.isInCombat());

		e.move(w, 45, 40);
		assertTrue(e.hasPlayerInArea(w));
		assertFalse(e.isInCombat());
	}

	@Test
	public void attackPlayerReducesItsHP() {
		final double playersCurrentHP = DEFAULT_PLAYER.getCurrentHitPoints();

		DEFAULT_ENEMY.attack(DEFAULT_PLAYER);
		assertEquals(playersCurrentHP - DEFAULT_ENEMY.getStrength(), DEFAULT_PLAYER.getCurrentHitPoints());
	}
}

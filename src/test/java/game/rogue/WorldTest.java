package game.rogue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {

	private Player DEFAULT_PLAYER;
	private World DEFAULT_WORLD;
	private Enemy DEFAULT_ENEMY;

	@BeforeEach
	public void setUp() {
		DEFAULT_PLAYER = new Player(new Warrior(), new Position(50, 100));
		DEFAULT_WORLD = new World(5000, 7500, DEFAULT_PLAYER);
		DEFAULT_ENEMY = new Enemy(2, true, new Position(30, 80));
	}

	@Test
	public void constructorCreatesMap() {
		assertEquals(5000, DEFAULT_WORLD.getWidth());
		assertEquals(7500, DEFAULT_WORLD.getHeight());
		assertEquals(DEFAULT_PLAYER, DEFAULT_WORLD.getPlayer());
	}

	@Test
	public void widthLessThan0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new World(-1, 5000, DEFAULT_PLAYER);
		});
	}

	@Test
	public void widthMoreThan50000ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new World(50001, 2500, DEFAULT_PLAYER);
		});
	}

	@Test
	public void heightLessThan0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new World(5000, -1, DEFAULT_PLAYER);
		});
	}

	@Test
	public void heightMoreThan50000ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new World(1000, 50001, DEFAULT_PLAYER);
		});
	}

	@Test
	public void nullPlayerhrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new World(1000, 2000, null);
		});
	}

	@Test
	public void addNewEnemy() {
		assertEquals(0, DEFAULT_WORLD.getEnemies().size());
		DEFAULT_WORLD.addEnemy(DEFAULT_ENEMY);
		assertEquals(1, DEFAULT_WORLD.getEnemies().size());
	}

	@Test
	public void removeEnemy() {
		Enemy e = new Enemy(5, true, new Position(10, 10));

		DEFAULT_WORLD.addEnemy(DEFAULT_ENEMY);
		DEFAULT_WORLD.addEnemy(e);
		assertEquals(2, DEFAULT_WORLD.getEnemies().size());

		DEFAULT_WORLD.removeEnemy(e);
		assertEquals(1, DEFAULT_WORLD.getEnemies().size());
		assertTrue(DEFAULT_WORLD.getEnemies().containsValue(DEFAULT_ENEMY));
	}
}

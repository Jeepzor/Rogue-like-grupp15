package game.rogue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {

	private Player DEFAULT_PLAYER;
	private HashMap<Position, Enemy> DEFAULT_ENEMY_MAP;

	@Before
	public void createDefaultObjects() {
		DEFAULT_PLAYER = new Player(new Warrior(), new Position(50, 100));
		DEFAULT_ENEMY_MAP = new HashMap<Position, Enemy>();
		DEFAULT_ENEMY_MAP.put(new Position(50, 75), new Enemy(600, true, new Position(50, 75)));
		DEFAULT_ENEMY_MAP.put(new Position(5, 150), new Enemy(500, true, new Position(5, 150)));
		DEFAULT_ENEMY_MAP.put(new Position(10, 40), new Enemy(400, true, new Position(10, 40)));
		DEFAULT_ENEMY_MAP.put(new Position(2, 250), new Enemy(300, false, new Position(2, 250)));
		DEFAULT_ENEMY_MAP.put(new Position(300, 55), new Enemy(200, true, new Position(300, 55)));
		DEFAULT_ENEMY_MAP.put(new Position(120, 40), new Enemy(100, false, new Position(120, 40)));
	}

	@Test
	public void constructorCreatesMap() {
		World w = new World(5000, 7500, DEFAULT_PLAYER);
		assertEquals(5000, w.getHeight());
		assertEquals(7500, w.getWidth());
		assertEquals(DEFAULT_PLAYER, w.getPlayer());
	}
}

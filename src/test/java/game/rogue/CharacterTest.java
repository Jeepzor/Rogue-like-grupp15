package game.rogue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterTest {
	
	private Player DEFAULT_PLAYER;
	private Enemy DEFAULT_ENEMY;
	private World DEFAULT_WORLD;
	
	@BeforeEach
	public void setUp() {
		DEFAULT_PLAYER = new Player(new Warrior(), new Position(10, 20));
		DEFAULT_ENEMY = new Enemy(1, true, new Position(50, 100));
		DEFAULT_WORLD = new World(100, 200, DEFAULT_PLAYER);
	}

	@Test
	void createCharacterWithValidValues() {
		Enemy e = new Enemy(5, false, new Position(0, 1));
		assertEquals(0, e.getPosition().getX());
		assertEquals(1, e.getPosition().getY());
	}
	
	@Test
	void characterWithNullPositionThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(5, false, null);
		});
	}
	
	@Test
	void characterWithXPositionBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(5, false, new Position(-1, 0));
		});
	}
	
	@Test
	void characterWithYPositionBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Enemy(5, false, new Position(1, -1));
		});
	}

	@Test
	public void characterMovesBy1X() {
		DEFAULT_PLAYER.move(DEFAULT_WORLD, 1, 0);
		assertEquals(11, DEFAULT_PLAYER.getPosition().getX());
		assertEquals(20, DEFAULT_PLAYER.getPosition().getY());
	}
	
	@Test
	public void characterMovesBy1Y() {
		DEFAULT_ENEMY.move(DEFAULT_WORLD, 0, 1);
		assertEquals(50, DEFAULT_ENEMY.getPosition().getX());
		assertEquals(101, DEFAULT_ENEMY.getPosition().getY());
	}

	@Test
	public void characterMovesOutsideMapOnMinXStopAtEdge() {
		DEFAULT_PLAYER.move(DEFAULT_WORLD, -11, -19);
		assertEquals(0, DEFAULT_PLAYER.getPosition().getX());
		assertEquals(1, DEFAULT_PLAYER.getPosition().getY());
	}
	
	@Test
	public void characterMovesOutsideMapOnMinYStopAtEdge() {
		DEFAULT_PLAYER.move(DEFAULT_WORLD, -9, -21);
		assertEquals(1, DEFAULT_PLAYER.getPosition().getX());
		assertEquals(0, DEFAULT_PLAYER.getPosition().getY());
	}
	
	@Test
	public void characterMovesOutsideMapOnMaxXStopAtEdge() {
		DEFAULT_PLAYER.move(DEFAULT_WORLD, 91, 180);
		assertEquals(100, DEFAULT_PLAYER.getPosition().getX());
		assertEquals(200, DEFAULT_PLAYER.getPosition().getY());
	}
	
	@Test
	public void characterMovesOutsideMapOnMaxYStopAtEdge() {
		DEFAULT_PLAYER.move(DEFAULT_WORLD, 90, 181);
		assertEquals(100, DEFAULT_PLAYER.getPosition().getX());
		assertEquals(200, DEFAULT_PLAYER.getPosition().getY());
	}
}

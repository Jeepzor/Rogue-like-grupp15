package game.rogue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionTest {

	private Position DEFAULT_POSITION;
	private World DEFAULT_WORLD;

	@BeforeEach
	public void setUp() {
		DEFAULT_POSITION = new Position(50, 75);
		DEFAULT_WORLD = new World(5000, 7500, new Player(new Warrior(), new Position(50, 75)));
	}

	@Test
	public void createPosition() {
		assertEquals(50, DEFAULT_POSITION.getX());
		assertEquals(75, DEFAULT_POSITION.getY());
	}

	@Test
	public void creatingPositionWithXBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Position(-1, 25);
		});
	}

	@Test
	public void creatingPositionWithYBelow0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Position(10, -1);
		});
	}

	@Test
	public void setNewXPosition() {
		DEFAULT_POSITION.setX(25, DEFAULT_WORLD);
		assertEquals(25, DEFAULT_POSITION.getX());
	}

	@Test
	public void setNewYPosition() {
		DEFAULT_POSITION.setY(15, DEFAULT_WORLD);
		assertEquals(15, DEFAULT_POSITION.getY());
	}

	@Test
	public void settingXToLessThan0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			DEFAULT_POSITION.setX(-1, DEFAULT_WORLD);
		});
	}

	@Test
	public void settingYToLessThan0ThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			DEFAULT_POSITION.setY(-1, DEFAULT_WORLD);
		});
	}
	
	@Test
	public void settingYToMoreThanWorldHeightThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			DEFAULT_POSITION.setY(DEFAULT_WORLD.getHeight() + 1, DEFAULT_WORLD);
		});
	}
	
	@Test
	public void settingXToMoreThanWorldWidthThrowsIAE() {
		assertThrows(IllegalArgumentException.class, () -> {
			DEFAULT_POSITION.setX(DEFAULT_WORLD.getWidth() + 1, DEFAULT_WORLD);
		});
	}
}

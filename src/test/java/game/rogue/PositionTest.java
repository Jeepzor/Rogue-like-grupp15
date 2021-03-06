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
	public void enemyTriesToMoveOutsideTheMapOnMinXStopsAtEdge() {
		DEFAULT_POSITION.setX(-1, DEFAULT_WORLD);
		DEFAULT_POSITION.setY(1, DEFAULT_WORLD);

		assertEquals(0, DEFAULT_POSITION.getX());
		assertEquals(1, DEFAULT_POSITION.getY());
	}

	@Test
	public void enemyTriesToMoveOutsideTheMapOnMinYStopsAtEdge() {
		DEFAULT_POSITION.setX(1, DEFAULT_WORLD);
		DEFAULT_POSITION.setY(-1, DEFAULT_WORLD);

		assertEquals(1, DEFAULT_POSITION.getX());
		assertEquals(0, DEFAULT_POSITION.getY());
	}
	
	@Test
	public void enemyTriesToMoveOutsideTheMapOnMaxXStopsAtEdge() {
		DEFAULT_POSITION.setX(5001, DEFAULT_WORLD);
		DEFAULT_POSITION.setY(7500, DEFAULT_WORLD);

		assertEquals(5000, DEFAULT_POSITION.getX());
		assertEquals(7500, DEFAULT_POSITION.getY());
	}
	
	@Test
	public void enemyTriesToMoveOutsideTheMapOnMaxYStopsAtEdge() {
		DEFAULT_POSITION.setX(5000, DEFAULT_WORLD);
		DEFAULT_POSITION.setY(7501, DEFAULT_WORLD);

		assertEquals(5000, DEFAULT_POSITION.getX());
		assertEquals(7500, DEFAULT_POSITION.getY());
	}

	@Test
	public void enemyTriesToMoveOutsideTheMapOnXAndY() {
		DEFAULT_POSITION.setX(5000, DEFAULT_WORLD);
		DEFAULT_POSITION.setY(7501, DEFAULT_WORLD);

		assertEquals(5000, DEFAULT_POSITION.getX());
		assertEquals(7500, DEFAULT_POSITION.getY());
	}
	
	@Test
	public void equalsReturnsTrueWhenEqual() {
		Position p1 = new Position(50, 75);
		Position p2 = new Position(50, 75);
		assertTrue(p1.equals(p2));
		assertTrue(p2.equals(p1));
	}
	
	@Test
	public void equalsReturnsFalseWhenNoneIsEqual() {
		Position p1 = new Position(50, 75);
		Position p2 = new Position(55, 80);
		assertFalse(p1.equals(p2));
		assertFalse(p2.equals(p1));
	}
	
	@Test
	public void equalsReturnsFalseWhenXNotEqual() {
		Position p1 = new Position(50, 75);
		Position p2 = new Position(55, 75);
		assertFalse(p1.equals(p2));
		assertFalse(p2.equals(p1));
	}
	
	@Test
	public void equalsReturnsFalseWhenYNotEqual() {
		Position p1 = new Position(50, 75);
		Position p2 = new Position(50, 80);
		assertFalse(p1.equals(p2));
		assertFalse(p2.equals(p1));
	}
	
	@Test
	public void equalsReturnsFalseIfParamIsNull() {
		assertFalse(DEFAULT_POSITION.equals(null));
	}
	
	@Test
	public void equalsReturnsFalseIfParamNotPosition() {
		Object o = new Object();
		assertFalse(DEFAULT_POSITION.equals(o));
	}
	
	@Test
	public void hashCodeReturnsCorrectValue() {
		final int result = DEFAULT_POSITION.getX() * 31 + DEFAULT_POSITION.getY();
		assertEquals(result, DEFAULT_POSITION.hashCode());
	}
	
}

package game.rogue;

public abstract class Character {
	private final Position position;

	public Character(Position position) {
		if (position == null) {
			throw new IllegalArgumentException();
		}
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void move(World world, int x, int y) {
		int newX = position.getX() + x;
		int newY = position.getY() + y;
		position.setX(newX, world);
		position.setY(newY, world);
	}
}

package game.rogue;

public abstract class Character {
	private Position position;

	public Character(Position position) {
		if (position == null || position.getX() < 0 || position.getY() < 0) {
			throw new IllegalArgumentException();
		}
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void move(World world, int x, int y) {
		int currentX = position.getX();
		int currentY = position.getY();
		if ((currentX + x) < 0) {
			position.setX(0, world);
		} else {
			position.setX(currentX + x, world);
		}
		if ((currentY + y) < 0) {
			position.setY(0, world);
		} else {
			position.setY(currentY + y, world);
		}
		
	}
}

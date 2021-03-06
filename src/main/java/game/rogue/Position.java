package game.rogue;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x, World world) {
		if (x < 0) {
			this.x = 0;
		} else if (x > world.getWidth()) {
			this.x = world.getWidth();
		} else {
			this.x = x;
		}
	}

	public void setY(int y, World world) {
		if (y < 0) {
			this.y = 0;
		} else if (y > world.getHeight()) {
			this.y = world.getHeight();
		} else {
			this.y = y;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Position) {
			Position p = (Position) o;
			return x == p.x && y == p.y;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return x * 31 + y;
	}
}

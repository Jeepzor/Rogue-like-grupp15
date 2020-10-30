package game.rogue;

import java.util.*;

public class World {
	private final int height;
	private final int width;
	private final Player player;
	private final HashMap<Position, Enemy> enemies;

	public World(int width, int height, Player player) {
		if (width < 0 || width > 50000 || height < 0 || height > 50000 || player == null)  {
			throw new IllegalArgumentException();
		}
		this.width = width;
		this.height = height;
		this.player = player;
		this.enemies = new HashMap<Position, Enemy>();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public HashMap<Position, Enemy> getEnemies() {
		return enemies;
	}
	
	public void addEnemy(Enemy e) {
		enemies.put(e.getPosition(), e);
	}
	
	public void removeEnemy(Enemy e) {
		enemies.remove(e.getPosition());
	}
}

package game.rogue;

import java.util.*;

public class World {
	private int height;
	private int width;
	private Player player;
	private HashMap<Position, Enemy> enemies;

	public World(int height, int width, Player player) {
		this.height = height;
		this.width = width;
		this.player = player;
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
package game.rogue;

import java.util.Random;
import java.util.concurrent.*;

public class Enemy extends Character {
	private final int maxHitPoints;
	private int currentHitPoints;
	private boolean isAggressive;
	private boolean isInCombat;

	public Enemy(int maxHitPoints, boolean isAggressive, Position position) {
		super(position);
		if (maxHitPoints < 100 || maxHitPoints > 1000) {
			throw new IllegalArgumentException();
		}
		this.maxHitPoints = maxHitPoints;
		this.currentHitPoints = maxHitPoints;
		this.isAggressive = isAggressive;

	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	public int getCurrentHitPoints() {
		return currentHitPoints;
	}

	public boolean isAggressive() {
		return isAggressive;
	}

	public boolean isInCombat() {
		return isInCombat;
	}

	public void damage(int amount) {
		if (amount >= 0) {
			currentHitPoints -= amount;
		}
	}

	public boolean isAlive() {
		return currentHitPoints >= 0;
	}

	@Override
	public void move(World world, int x, int y) {
		super.move(world, x, y);
		if (hasPlayerInArea(world) && isAggressive) {
			startCombat(world.getPlayer());
		}
	}
	
	public void moveRandomly(World world) {
		int randomX = getRandomIntWithinRange(-30, 30);
		int randomY = getRandomIntWithinRange(-30, 30);
		move(world, randomX, randomY);
	}

	private int getRandomIntWithinRange(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public boolean hasPlayerInArea(World world) {
		Position playerPosition = world.getPlayer().getPosition();
		return isInRange(getPosition().getX(), playerPosition.getX())
				&& isInRange(getPosition().getY(), playerPosition.getY());
	}

	private boolean isInRange(int enemyPos, int playerPos) {
		return enemyPos > playerPos ? enemyPos - playerPos <= 15 : playerPos - enemyPos <= 15;
	}

	public void startCombat(Player player) {
		isInCombat = true;
		
	}
	
	public void attack(Player player) {

	}

}

package game.rogue;

import java.util.concurrent.*;

public class Enemy extends Character {
	private static final int MAX_LEVEL = 100;
	private double maxHitPoints;
	private double currentHitPoints;
	private boolean isAggressive;
	private boolean isInCombat;
	private int level;

	public Enemy(int level, boolean isAggressive, Position position) {
		super(position);
		if (level < 1 || level > MAX_LEVEL) {
			throw new IllegalArgumentException("Level has to be between 1 and 100");
		}
		this.level = level;
		this.maxHitPoints = (level * 2) * Math.sqrt(level);
		this.currentHitPoints = maxHitPoints;
		this.isAggressive = isAggressive;
	}

	public double getMaxHitPoints() {
		return maxHitPoints;
	}

	public double getCurrentHitPoints() {
		return currentHitPoints;
	}

	public boolean isAggressive() {
		return isAggressive;
	}

	public boolean isInCombat() {
		return isInCombat;
	}

	public int getStrength() {
		return level * 2;
	}

	public int getLevel() {
		return level;
	}

	private void healToMaxHitPoints() {
		currentHitPoints = maxHitPoints;
	}

	public void damage(double amount) {
		if (amount >= 0) {
			currentHitPoints -= amount;
		}
	}

	public void heal(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Healing amount has to be above 0");
		}
		if (currentHitPoints == maxHitPoints) {
			throw new IllegalArgumentException("Player is already at full health");
		}
		if ((currentHitPoints + amount) > maxHitPoints) {
			healToMaxHitPoints();
		} else {
			currentHitPoints += amount;
		}
	}

	public boolean isAlive() {
		return currentHitPoints > 0;
	}

	@Override
	public void move(World world, int x, int y) {
		super.move(world, x, y);
		if (hasPlayerInArea(world) && isAggressive) {
			startCombat(world.getPlayer());
		}
	}

	public void moveRandomly(World world) {
		int randomX = getRandomIntWithinRange();
		int randomY = getRandomIntWithinRange();
		move(world, randomX, randomY);
	}

	private int getRandomIntWithinRange() {
		final int min = -30;
		final int max = 30;
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public boolean hasPlayerInArea(World world) {
		Position playerPos = world.getPlayer().getPosition();
		Position enemyPos = getPosition();
		return isInRange(enemyPos.getX(), playerPos.getX()) && isInRange(enemyPos.getY(), playerPos.getY());
	}

	private boolean isInRange(int enemyPos, int playerPos) {
		return enemyPos > playerPos ? enemyPos - playerPos <= 15 : playerPos - enemyPos <= 15;
	}

	private void startCombat(Player player) {
		isInCombat = true;
	}

	public void attack(Player player) {
		player.takeDamage(getStrength());
	}

}

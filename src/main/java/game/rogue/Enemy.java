package game.rogue;

import java.util.concurrent.*;

public class Enemy extends Character {
	private static final int MAX_LEVEL = 100;
	private final int maxHitPoints;
	private int currentHitPoints;
	private boolean isAggressive;
	private boolean isInCombat;
	private int strength;
	private int level;

	public Enemy(int maxHitPoints, boolean isAggressive, Position position, int strength, int level) {
		super(position);
		if (maxHitPoints < 10 || maxHitPoints > 1000) {
			throw new IllegalArgumentException("Hit points has to be between 10 and 1000");
		}
		if (strength < 1 || strength > 200) {
			throw new IllegalArgumentException("Strength has to be between 1 and 200");
		}
		if (level < 1 || level > MAX_LEVEL) {
			throw new IllegalArgumentException("Level has to be between 1 and 100");
		}
		this.maxHitPoints = maxHitPoints;
		this.currentHitPoints = maxHitPoints;
		this.isAggressive = isAggressive;
		this.strength = strength;
		this.level = level;
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

	public int getStrength() {
		return strength;
	}

	public int getLevel() {
		return level;
	}
	
	public void levelUp() {
		if (level < MAX_LEVEL) {
			level++;
			currentHitPoints = maxHitPoints;
		}
	}

	public void damage(int amount) {
		if (amount >= 0) {
			currentHitPoints -= amount;
		}
	}

	public void heal(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Healing amount has to be above 0");
		}
		if (currentHitPoints == maxHitPoints) {
			throw new IllegalArgumentException("Player is already at full health");
		}
		if ((currentHitPoints + amount) > maxHitPoints) {
			currentHitPoints = maxHitPoints;
		} else {
			currentHitPoints += amount;
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
		player.takeDamage(strength);
	}

}

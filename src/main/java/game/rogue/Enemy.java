package game.rogue;

import java.util.concurrent.*;

public class Enemy extends Character {
	private static final int MAX_LEVEL = 100;
	private static final int MAX_RANGE_TO_MOVE_RANDOMLY = 30;
	private static final int DETECTION_RANGE = 15;
	private final int level;
	private final boolean isAggressive;
	private double currentHitPoints;
	private boolean isInCombat;

	public Enemy(int level, boolean isAggressive, Position position) {
		super(position);
		if (level < 1 || level > MAX_LEVEL) {
			throw new IllegalArgumentException("Level has to be between 1 and 100");
		}
		this.level = level;
		this.currentHitPoints = getMaxHitPoints();
		this.isAggressive = isAggressive;
	}

	public double getMaxHitPoints() {
		return (level * 2) * Math.sqrt(level);
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
		currentHitPoints = getMaxHitPoints();
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
		if (currentHitPoints == getMaxHitPoints()) {
			throw new IllegalArgumentException("Player is already at full health");
		}
		if ((currentHitPoints + amount) > getMaxHitPoints()) {
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
		if (hasPlayerInDetectionRange(world) && isAggressive) {
			startCombat();
		}
	}

	/*
	 * Calls moveRandomWithinRange() to get a random int within a specific range for
	 * both x and y and then moves by that amount
	 */
	public void moveRandomly(World world) {
		int randomX = getRandomIntWithinRange();
		int randomY = getRandomIntWithinRange();
		move(world, randomX, randomY);
	}

	/*
	 * Returns an int within a specific range. Max is the constant
	 * MAX_AMOUNT_TO_MOVE_RANDOMLY and min is the negation of it
	 */
	private int getRandomIntWithinRange() {
		return ThreadLocalRandom.current().nextInt(-MAX_RANGE_TO_MOVE_RANDOMLY, MAX_RANGE_TO_MOVE_RANDOMLY + 1);
	}

	/*
	 * Called after the enemy has moved and checks if the player is within range to be
	 * attacked.Returns true if the player is within range on both x and y. 
	 */
	public boolean hasPlayerInDetectionRange(World world) {
		Position playerPos = world.getPlayer().getPosition();
		Position enemyPos = getPosition();
		return isInDetectionRange(enemyPos.getX(), playerPos.getX()) && isInDetectionRange(enemyPos.getY(), playerPos.getY());
	}

	private boolean isInDetectionRange(int enemyPos, int playerPos) {
		return enemyPos > playerPos ? enemyPos - playerPos <= DETECTION_RANGE : playerPos - enemyPos <= DETECTION_RANGE;
	}

	private void startCombat() {
		isInCombat = true;
	}

	public void attack(Player player) {
		player.takeDamage(getStrength());
	}

}

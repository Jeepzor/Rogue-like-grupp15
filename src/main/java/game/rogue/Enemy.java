package game.rogue;

public class Enemy extends Character{
	private final int maxHitPoints;
	private int currentHitPoints;
	private boolean isAggressive;
	private boolean isInCombat;


	public Enemy(int maxHitPoints, boolean isAggressive, int xPos, int yPos) {
		super(xPos, yPos);
		if (maxHitPoints < 100 || maxHitPoints > 1000 || xPos < 0 || yPos < 0) {
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

	public boolean isDead() {
		return currentHitPoints <= 0;
	}


}

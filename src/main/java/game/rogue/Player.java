package game.rogue;

import java.util.ArrayList;
import java.util.Iterator;

public class Player extends Character {
	private static final int MAX_LEVEL = 100;
	private static final int BASE_LEVEL_UP_THRESHOLD = 100;
	private static final int CLASS_CHANGE_COST = 5;
	private final ArrayList<Ability> abilities;
	private int level;
	private int experience;
	private double currentHitPoints;
	private double currentMana;
	private PlayerClass playerClass;
	private Inventory inventory;

	public Player(PlayerClass playerClass, Position position) {
		super(position);
		if (playerClass == null){
			throw new IllegalArgumentException("Class can not be null");
		}
		this.abilities = new ArrayList<>();
		this.playerClass = playerClass;
		this.level = 1;
		this.currentHitPoints = playerClass.getMaxHitPoints(this.level);
		this.currentMana = playerClass.getMaxMana(this.level);
		this.experience = 0;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Inventory getInventory() {
		if (hasInventory()) {
			return this.inventory;
		} else {
			throw new IllegalStateException("Player has no inventory!");
		}
	}

	private boolean hasInventory() {
		return inventory != null;
	}

	public double getMaxHitPoints() {
		return this.playerClass.getMaxHitPoints(this.level);
	}

	public double getMaxMana() {
		return this.playerClass.getMaxMana(this.level);
	}

	public boolean canEquipCloth() {
		return playerClass.canEquipCloth();
	}

	public boolean canEquipPlate() {
		return playerClass.canEquipPlate();
	}

	public double getCurrentHitPoints() {
		return currentHitPoints;
	}

	public double getCurrentMana() {
		return currentMana;
	}

	public int getLevel() {
		return this.level;
	}

	public int getNextLevelThreshold() {
		return BASE_LEVEL_UP_THRESHOLD * this.level;
	}

	public int getExperience() {
		return this.experience;
	}

	/*
	 * If the amount is less than the needed XP for next level, simply increment XP
	 * Else, gain a level and set the current experience to 0,
	 * then call gainExperience again while passing the remainder (if it's greater than 0).
	 */
	public void gainExperience(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Experience amount has to be above 0!");
		}

		if (getLevel() == MAX_LEVEL) {
			return;
		}

		if (this.experience + amount < getNextLevelThreshold()) {
			this.experience += amount;
		} else {
			int previousExperience = this.experience;
			int overflowingExperience = (amount - (getNextLevelThreshold() - previousExperience));
			this.experience = 0;
			if (overflowingExperience != 0) {
				levelUp();
				gainExperience(overflowingExperience);
			}
		}
	}

	public void levelUp() {
		incrementLevel();
		healToMaxHitPoints();
		gainManaToMaxMana();
	}

   /*
	* Changes the class of the player as long as: The player is at least level 6,
	* and the new class is different from the current class. Reduce level by 6, Then check if spells and equipment
	* need to be un-equipped / un-learned and do so.
	*/
	public void changeClass(PlayerClass newClass) {
		if (newClass.equals(this.playerClass)) {
			throw new IllegalArgumentException("New class can't be the same as the current class");
		}
		if (CLASS_CHANGE_COST >= getLevel()) {
			throw new IllegalStateException("Too low level to change class!");
		}
		setNewClass(newClass);
		setLevel(getLevel() - CLASS_CHANGE_COST);
		if (hasInventory()) {
			getInventory().validateEquippedItems();
		}
		validateAbilities();
	}

	public void gainAbility(Ability newAbility) {
		if (canHaveAbility(newAbility)) {
			boolean found = false;
			for (Ability temp : abilities)
				if (newAbility.equals(temp)) {
					found = true;
					temp.incrementLevel();
				}
			if (!found) {
				abilities.add(newAbility);
			}
		} else {
			throw new IllegalArgumentException("Class requirement unmet!");
		}
	}

	public boolean canHaveAbility(Ability ability) {
		return ability.hasRequiredClass(this.playerClass);
	}

   /*
    * Iterate through all abilities and remove them if the player no longer meets the requirements.
    */
	public void validateAbilities() {
		Iterator<Ability> iterator = abilities.iterator();
		while (iterator.hasNext()) {
			Ability current = iterator.next();
			if (!canHaveAbility(current)) {
				iterator.remove();
			}
		}
	}

	public int getAmountOfAbilities() {
		return abilities.size();
	}

	public void setNewClass(PlayerClass newClass) {
		this.playerClass = newClass;
	}

	public void takeDamage(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Damage can't be negative!");
		}
		this.currentHitPoints = Math.max(0, this.currentHitPoints - amount);
	}

	public double getDamageValue() {
		// Needs to also get the damage from all items
		return this.playerClass.getDamage(getLevel());
	}

	public void gainHitPoints(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Healing amount has to be above 0!");
		}

		if (this.currentHitPoints == getMaxHitPoints()) {
			throw new IllegalStateException("Player is already at full health");
		}
		this.currentHitPoints = Math.min(this.currentHitPoints + amount, getMaxHitPoints());
	}

	public void gainMana(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Mana gained has to be above 0!");
		}

		if (this.currentMana == getMaxMana()) {
			throw new IllegalStateException("Player is already at full mana");
		}

		this.currentMana = Math.min(this.currentMana + amount, getMaxMana());
	}

	public void spendMana(double amount) {
		if (canAffordMana(amount)) {
			this.currentMana -= amount;
		} else {
			throw new IllegalStateException("Player does not have enough mana!");
		}
	}

	private boolean canAffordMana(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Mana cost can't be negative!");
		}

		return amount <= getCurrentMana();
	}

	public boolean isAlive() {
		return this.currentHitPoints > 0;
	}

	private void healToMaxHitPoints() {
		this.currentHitPoints = playerClass.getMaxHitPoints(this.level);
	}

	private void gainManaToMaxMana() {
		this.currentMana = playerClass.getMaxMana(this.level);
	}

	private void setLevel(int level) {
		this.level = level;
	}

	public void incrementLevel() {
		if (this.level < MAX_LEVEL) {
			this.level += 1;
		}
	}

	@Override
	public String toString() {
		return "Player - " + this.playerClass + " - Level: " + getLevel();
	}
}

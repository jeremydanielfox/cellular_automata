package CellsAndComponents;

/**
 * This class extends aquatic creature to hold the extra information (energy
 * level) characteristic of sharks in the WaTor World simulation.
 * 
 * @author Megan
 *
 */
public class Shark extends AquaticCreature {
	private int energyLevel;

	public Shark(int state) {
		super(state);
		energyLevel = 0;
	}

	/**
	 * Decreases the shark energy by one unit
	 */
	public void decreaseEnergy() {
		energyLevel += 1;
	}

	/**
	 * increases the shark energy by one unit
	 */
	public void increaseEnergy() {
		energyLevel -= 1;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}
}

package CellsAndComponents;

/**
 * This class extends Inhabitant to keep track of an additional piece of
 * information for the WaTor World simulation - the reproduction counter of a
 * creature in the simulation.
 * 
 * @author Megan
 *
 */
public class AquaticCreature extends Inhabitant {
	private int reproductionCounter;

	public AquaticCreature(int state) {
		super(state);
		reproductionCounter = 0;
	}

	public void increaseReproductionCounter() {
		reproductionCounter += 1;
	}

	public void resetReproductionCounter() {
		reproductionCounter = 0;
	}

	public int getReproductionCounter() {
		return reproductionCounter;
	}
}

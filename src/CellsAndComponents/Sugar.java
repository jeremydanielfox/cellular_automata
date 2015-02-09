package CellsAndComponents;

import javafx.scene.paint.Color;

/**
 * This class extends the patch object to contain the extra info needed by a
 * sugar patch in the SugarScape simulation.
 * 
 * @author Megan
 *
 */
public class Sugar extends Patch {
	private int currentAmount;
	private int maxAmount;

	public Sugar(int max) {
		maxAmount = max;
		currentAmount = maxAmount;
	}

	/**
	 * Returns the current amount of Sugar in this patch
	 */
	@Override
	public int getStateForCell() {
		return currentAmount;
	}

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	public int getSugarAmount() {
		return currentAmount;
	}

	public void takeAllSugar() {
		currentAmount = 0;
	}

	public void takeSugar(int sugarToTake) {
		currentAmount -= sugarToTake;
	}

	/**
	 * Adds the sugarGrowBackRate amount of sugar to the sugar patch, or set the
	 * current amount of sugar to the max amount of sugar in the patch if adding
	 * the grow back amount will exceed the max sugar level.
	 * 
	 * @param sugarGrowBackRate
	 */
	public void sugarGrowBack(int sugarGrowBackRate) {
		if (currentAmount + sugarGrowBackRate >= maxAmount) {
			currentAmount = maxAmount;
		} else {
			currentAmount += sugarGrowBackRate;
		}
	}

}

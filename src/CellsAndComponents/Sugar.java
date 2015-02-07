package CellsAndComponents;
import javafx.scene.paint.Color;


public class Sugar extends Patch {
	private int currentAmount;
	private int maxAmount;
	
	public Sugar(int max) {
		maxAmount = max;
		currentAmount = maxAmount;
	}

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
	
	public void sugarGrowBack(int sugarGrowBackRate) {
		if (currentAmount + sugarGrowBackRate >= maxAmount){
			currentAmount = maxAmount;
		}
		else {
			currentAmount += sugarGrowBackRate;
		}
	}
	
	

}

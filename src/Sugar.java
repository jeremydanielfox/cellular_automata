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
		// TODO Auto-generated method stub
		return currentAmount;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
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
		currentAmount += sugarGrowBackRate;
	}
	


}

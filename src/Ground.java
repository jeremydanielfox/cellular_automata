import CellsAndComponents.Patch;
import javafx.scene.paint.Color;


public class Ground extends Patch {
	
	private int foodPheremones;
	private int homePheremones;
	
	public Ground() {
		
	}

	@Override
	public int getStateForCell() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}

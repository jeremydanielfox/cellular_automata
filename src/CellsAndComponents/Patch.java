package CellsAndComponents;
import javafx.scene.paint.Color;


public abstract class Patch {
	
	public Patch() {
	}
	
	public abstract int getStateForCell();
	
	public abstract Color getColor();
	
}
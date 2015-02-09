package CellsAndComponents;

import javafx.scene.paint.Color;

public class Water extends Patch {

	public Water() {
	}

	@Override
	public int getStateForCell() {
		return 0;
	}

	@Override
	public Color getColor() {
		return Color.AQUAMARINE;
	}

}

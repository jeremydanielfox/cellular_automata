package CellsAndComponents;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * This is an extension of the Cell class to create more complex Cells with a single
 * Inhabitant. This will allow more much more complex phenomena, like simulating
 * creatures with health or more complex states.
 * 
 * @author Jeremy
 *
 */
public class CellWithInhabitant extends Cell {
	private Inhabitant myInhabitant;

	public CellWithInhabitant(int id, Polygon shape, List<Point2D> verticies,
			int defaultState, Color defaultColor) {
		super(id, shape, verticies, defaultState, defaultColor);
		myInhabitant = new Inhabitant(defaultState);
	}

	public void setInhabitant(Inhabitant inhabitant) {
		myInhabitant = inhabitant;
	}

	public Inhabitant getInhabitant() {
		return myInhabitant;
	}

}

import java.util.List;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class CellWithInhabitant extends Cell {
	private Inhabitant myInhabitant;

	public CellWithInhabitant(int id, Polygon shape, List<Point2D> verticies,
			int defaultState, Color defaultColor) {
		super(id, shape, verticies, defaultState, defaultColor);
		myInhabitant = new Inhabitant(defaultState);
	}

	//
	// public CellWithInhabitant(int id, int currentState, int futureState,
	// Shape shape) {
	// super(id, currentState, futureState, shape);
	// }

	public void setInhabitant(Inhabitant inhabitant) {
		myInhabitant = inhabitant;
	}

	public Inhabitant getInhabitant() {
		return myInhabitant;
	}

}

import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class CellFactory {

	public Cell createSpecifiedCell(String cell, int id, Shape shape,
			Set<Point2D> verticies, int defaultState, Color defaultColor) {

		switch (cell) {
		case "Cell":
			return new Cell(id, shape, verticies, defaultState, defaultColor);
		case "CellWithInhabitants":
			return new CellWithInhabitant(id, shape, verticies, defaultState,
					defaultColor);
		default:
			return new Cell(id, shape, verticies, defaultState, defaultColor);
		}

	}
}

import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class CellFactory {

	public Cell createSpecifiedCell(String model, int id, Shape shape,
			Set<Point2D> verticies, int defaultState, Color defaultColor) {

		switch (model) {
		case "WaTorWorld":
			return new CellWithInhabitant(id, shape, verticies, defaultState,
					defaultColor);
		default:
			return new Cell(id, shape, verticies, defaultState, defaultColor);
		}

	}
}

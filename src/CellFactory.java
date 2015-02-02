import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * This is the factory that makes Cell objects depending on the model type
 * 
 * @author Jeremy
 *
 */
public class CellFactory {

	public Cell createSpecifiedCell(String model, int id, Polygon shape,
			List<Point2D> verticies, int defaultState, Color defaultColor) {

		switch (model) {
		case "WaTorWorld":
			return new CellWithInhabitant(id, shape, verticies, defaultState,
					defaultColor);
		default:
			return new Cell(id, shape, verticies, defaultState, defaultColor);
		}

	}
}

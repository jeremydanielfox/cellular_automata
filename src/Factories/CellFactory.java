package Factories;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import CellsAndComponents.AdvancedCell;
import CellsAndComponents.Cell;
import CellsAndComponents.CellWithInhabitant;

/**
 * This is the factory that makes Cell objects depending on the model type.
 * 
 * @author Jeremy
 *
 */
public class CellFactory {

	/**
	 * Generates and returns the proper cell based on the model name given
	 * @param model
	 * @param id
	 * @param shape
	 * @param verticies
	 * @param defaultState
	 * @param defaultColor
	 * @return
	 */
	public Cell createSpecifiedCell(String model, int id, Polygon shape,
			List<Point2D> verticies, int defaultState, Color defaultColor) {

		switch (model) {
		case "WaTorWorld":
			return new CellWithInhabitant(id, shape, verticies, defaultState,
					defaultColor);
		case "Sugarscape":
			return new AdvancedCell(id, shape, verticies, defaultState,
					defaultColor);
		default:
			return new Cell(id, shape, verticies, defaultState, defaultColor);
		}

	}
}

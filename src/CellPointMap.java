import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Point2D;

/**
 * This class handles the mappings between Cells and their Point2D in the grid.
 * 
 * @author Jeremy, Team 12
 *
 */
public class CellPointMap {
	private Map<Cell, Point2D> myCellsToPoints = new HashMap<>();
	private Map<Point2D, Cell> myPointsToCells = new HashMap<>();

	public void put(Cell myCell, Point2D myPoint) {
		myCellsToPoints.put(myCell, myPoint);
		myPointsToCells.put(myPoint, myCell);
	}

	public Cell get(Point2D myPoint) {
		return myPointsToCells.get(myPoint);
	}

	public Point2D get(Cell myCell) {
		return myCellsToPoints.get(myCell);
	}

}

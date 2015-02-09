package Graphs;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import CellsAndComponents.Cell;

/**
 * This is the graph class that will give cells eight neighbors. This class is
 * appropriate for both triangles and squares
 * 
 * @author Jeremy
 *
 */
public class EightNeighorBasicGraph extends BasicGraph {
	private Point2D RIGHT;
	private Point2D LEFT;
	private Point2D UP;
	private Point2D DOWN;
	private Point2D UP_LEFT;
	private Point2D UP_RIGHT;
	private Point2D DOWN_LEFT;
	private Point2D DOWN_RIGHT;

	public EightNeighorBasicGraph(Polygon[][] myShapes, int numCellsWidth,
			int numCellsHeight, int defaultState, Color defaultColor,
			String model, String graphType, String edgeType) {
		super(myShapes, numCellsWidth, numCellsHeight, defaultState,
				defaultColor, model, graphType, edgeType);
	}

	public void initializeConstants() {
		RIGHT = new Point2D(1, 0);
		LEFT = new Point2D(-1, 0);
		UP = new Point2D(0, -1);
		DOWN = new Point2D(0, 1);
		UP_LEFT = new Point2D(-1, -1);
		UP_RIGHT = new Point2D(1, -1);
		DOWN_LEFT = new Point2D(-1, 1);
		DOWN_RIGHT = new Point2D(1, 1);
	}

	@Override
	public void connectCells() {
		for (Cell current : this.getAllCells()) {
			checkConnect(current, RIGHT);
			checkConnect(current, LEFT);
			checkConnect(current, DOWN);
			checkConnect(current, UP);
			checkConnect(current, UP_LEFT);
			checkConnect(current, UP_RIGHT);
			checkConnect(current, DOWN_LEFT);
			checkConnect(current, DOWN_RIGHT);
		}

	}

}

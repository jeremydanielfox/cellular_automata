// This entire file is part of my masterpiece
// Jeremy Fox
package Graphs;

import javafx.geometry.Point2D;
import CellsAndComponents.Cell;

public class EightNeighborConnector extends CellConnector {
	private static final Point2D RIGHT = new Point2D(1, 0);
	private static final Point2D LEFT = new Point2D(-1, 0);
	private static final Point2D UP = new Point2D(0, -1);
	private static final Point2D DOWN = new Point2D(0, 1);
	private static final Point2D UP_LEFT = new Point2D(-1, -1);
	private static final Point2D UP_RIGHT = new Point2D(1, -1);
	private static final Point2D DOWN_LEFT = new Point2D(-1, 1);
	private static final Point2D DOWN_RIGHT = new Point2D(1, 1);

	public EightNeighborConnector(BaseGraph graph) {
		super(graph);
	}

	@Override
	protected void checkAllConnections(Cell current) {
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

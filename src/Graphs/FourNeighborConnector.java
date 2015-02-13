// This entire file is part of my masterpiece
// Jeremy Fox
package Graphs;

import CellsAndComponents.Cell;
import javafx.geometry.Point2D;

public class FourNeighborConnector extends CellConnector {
	private static final Point2D RIGHT = new Point2D(1, 0);
	private static final Point2D LEFT = new Point2D(-1, 0);
	private static final Point2D UP = new Point2D(0, -1);
	private static final Point2D DOWN = new Point2D(0, 1);
	public FourNeighborConnector(BaseGraph graph) {
		super(graph);
	}

	@Override
	protected void checkAllConnections(Cell current) {
		checkConnect(current, RIGHT);
		checkConnect(current, LEFT);
		checkConnect(current, DOWN);
		checkConnect(current, UP);
	}

}

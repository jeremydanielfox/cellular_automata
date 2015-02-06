package Graphs;

import java.util.List;

import CellsAndComponents.Cell;
import javafx.geometry.Point2D;

public class FourNeighborToroid extends EdgeManager {
	private final Point2D RIGHT = new Point2D(1, 0);
	private final Point2D LEFT = new Point2D(-1, 0);
	private final Point2D UP = new Point2D(0, -1);
	private final Point2D DOWN = new Point2D(0, 1);
	private final boolean VERTICAL = false;
	private final boolean HORIZONTAL = true;

	public FourNeighborToroid(BaseGraph graph) {
		super(graph);
	}

	protected void linkEdges() {
		connectWithOpposite(getLeftCol(),HORIZONTAL);
		connectWithOpposite(getTopRow(),VERTICAL);
	}

	private void connectWithOpposite(List<Cell> currentList, boolean sideways) {
		for (Cell current : currentList) {
			Point2D currentPoint = getGraph().getCellPointMap().get(current);
			Point2D temp;
			if (sideways)
				temp = getRight(currentPoint);
			else
				temp = getBottom(currentPoint);
			Cell tempCell = getGraph().getCellPointMap().get(temp);
			getGraph().connect(tempCell, current);
		}
	}

	protected Point2D getRight(Point2D current) {
		return new Point2D(getGraph().getNumCellsAcross(), current.getY());
	}

	protected Point2D getBottom(Point2D current) {
		return new Point2D(current.getX(), getGraph().getNumCellsUpDown());
	}

}

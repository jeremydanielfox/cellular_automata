package Graphs;
import java.util.List;

import CellsAndComponents.Cell;
import javafx.geometry.Point2D;

public class FourNeighborToroid extends EdgeManager {
	private final Point2D RIGHT = new Point2D(1, 0);
	private final Point2D LEFT = new Point2D(-1, 0);
	private final Point2D UP = new Point2D(0, -1);
	private final Point2D DOWN = new Point2D(0, 1);

	public FourNeighborToroid(BaseGraph graph) {
		super(graph);
		// TODO Auto-generated constructor stub
	}

	protected void linkEdges() {
		connectWithRight();
		connectWithBottom();
		// connectWithOther(getTopRow(), getGraph().getNumCellsUpDown());
		// connectWithOther(getLeftCol(), getGraph().getNumCellsAcross());
	}

	// private void connectWithOther(List<Cell> currentList, int length) {
	// for (Cell current : currentList) {
	// Point2D currentPoint = getGraph().getCellPointMap().get(current);
	// Point2D temp = new Point2D(currentPoint.getX(), length);
	// Cell tempCell = getGraph().getCellPointMap().get(temp);
	// getGraph().connect(tempCell, current);
	// }
	// }
	//
	private void connectWithRight() {
		for (Cell current : getLeftCol()) {
			Point2D currentPoint = getGraph().getCellPointMap().get(current);
			Point2D temp = new Point2D(getGraph().getNumCellsAcross(),
					currentPoint.getY());
			Cell tempCell = getGraph().getCellPointMap().get(temp);
			getGraph().connect(tempCell, current);
		}
	}

	private void connectWithBottom() {
		for (Cell current : getTopRow()) {
			Point2D currentPoint = getGraph().getCellPointMap().get(current);
			Point2D temp = new Point2D(currentPoint.getX(), getGraph()
					.getNumCellsUpDown());
			Cell tempCell = getGraph().getCellPointMap().get(temp);
			getGraph().connect(tempCell, current);
		}
	}

}

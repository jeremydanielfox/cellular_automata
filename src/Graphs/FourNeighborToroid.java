package Graphs;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import CellsAndComponents.Cell;

/**
 * This is the EdgeManager class that will wrap edges in a four neighbor
 * toroidal shape
 * 
 * @author Jeremy
 *
 */
public class FourNeighborToroid extends EdgeManager {
	private final boolean VERTICAL = false;
	private final boolean HORIZONTAL = true;

	public FourNeighborToroid(BaseGraph graph) {
		super(graph);
		graph.setWrap();
	}

	protected void linkEdges() {
		connectWithOpposite(getLeftCol(), HORIZONTAL);
		connectWithOpposite(getTopRow(), VERTICAL);
	}

	private void connectWithOpposite(List<Cell> currentList, boolean sideways) {
		for (Cell currentCell : currentList) {
			Point2D currentPoint = getGraph().getCellPointMap()
					.get(currentCell);
			List<Point2D> pointList = new ArrayList<>();
			if (sideways)
				sidewaysCondition(pointList, currentPoint);
			else
				verticalCondition(pointList, currentPoint);
			for (Point2D currentNewPoint : pointList) {
				Cell tempCell = getGraph().getCellPointMap().get(
						currentNewPoint);
				getGraph().connect(tempCell, currentCell);
			}
		}
	}

	protected void sidewaysCondition(List<Point2D> pointList,
			Point2D currentPoint) {
		pointList.add(getRight(currentPoint));
	}

	protected void verticalCondition(List<Point2D> pointList,
			Point2D currentPoint) {
		pointList.add(getBottom(currentPoint));
	}

	protected Point2D getRight(Point2D current) {
		return new Point2D(getGraph().getNumCellsAcross(), current.getY());
	}

	protected Point2D getBottom(Point2D current) {
		return new Point2D(current.getX(), getGraph().getNumCellsUpDown());
	}

}

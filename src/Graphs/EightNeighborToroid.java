// This entire file is part of my masterpiece
// Jeremy Fox
package Graphs;

import java.util.List;

import javafx.geometry.Point2D;

/**
 * This is the EdgeManager class that will wrap edges into an eight-neighbor
 * toroid
 * 
 * @author Jeremy
 *
 */
public class EightNeighborToroid extends FourNeighborToroid {

	public EightNeighborToroid(BaseGraph graph) {
		super(graph);
	}

	protected void sidewaysCondition(List<Point2D> pointList,
			Point2D currentPoint) {
		pointList.add(getUpRight(currentPoint));
		pointList.add(getRight(currentPoint));
		pointList.add(getDownRight(currentPoint));
	}

	protected void verticalCondition(List<Point2D> pointList,
			Point2D currentPoint) {
		pointList.add(getBottomLeft(currentPoint));
		pointList.add(getBottom(currentPoint));
		pointList.add(getBottomRight(currentPoint));
	}

	protected Point2D getUpRight(Point2D current) {
		double ycoord;
		if (current.getY() == 1)
			ycoord = getGraph().getNumCellsUpDown();
		else
			ycoord = current.getY() - 1;
		return new Point2D(getGraph().getNumCellsAcross(), ycoord);
	}

	protected Point2D getDownRight(Point2D current) {
		double ycoord;
		if (current.getY() == getGraph().getNumCellsUpDown())
			ycoord = 1;
		else
			ycoord = current.getY() + 1;
		return new Point2D(getGraph().getNumCellsAcross(), ycoord);
	}

	protected Point2D getBottomLeft(Point2D current) {
		double xcoord;
		if (current.getX() == 1)
			xcoord = getGraph().getNumCellsAcross();
		else
			xcoord = current.getX() - 1;
		return new Point2D(xcoord, getGraph().getNumCellsUpDown());
	}

	protected Point2D getBottomRight(Point2D current) {
		double xcoord;
		if (current.getX() == getGraph().getNumCellsAcross())
			xcoord = 1;
		else
			xcoord = current.getX() + 1;
		return new Point2D(xcoord, getGraph().getNumCellsUpDown());
	}
}

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class SquareGraph extends BaseGraph {
	private int MIN_POINTS_IN_COMMON = 2;
	private int cellWidth;
	private int cellHeight;

	/**
	 * The constructor for a SquareGraph takes in the desired number of cells
	 * across the grid in each direction, as well as the screen size in pixels
	 * in each direction. The constructor then assigns all instance variables,
	 * calculates cell widths and heights, initializes all cells, and then
	 * connects all cells to complete the graph.
	 * 
	 * @param numCellsWidth
	 * @param numCellsHeight
	 * @param screenWidth
	 * @param screenHeight
	 */
	public SquareGraph(int numCellsWidth, int numCellsHeight, int screenWidth,
			int screenHeight) {
		super(numCellsWidth, numCellsHeight, screenWidth, screenHeight);
	}

	/**
	 * Calculate the width and height of each cell
	 */
	protected void calculateValues() {
		cellWidth = getMyScreenWidth() / getNumCellsAcross();
		cellHeight = getMyScreenHeight() / getNumCellsUpDown();
	}

	/**
	 * Generate all necessary cells. Assign each cell an appropriate ID. Create
	 * a Polygon for each Cell. Assign correct matrix of points for each
	 * Polygon. Add each Polygon to each Cell. Add each Cell to the Graph.
	 */
	public void initializeCells() {
		int count = 1;
		for (int i = 1; i <= getNumCellsAcross(); i++)
			for (int j = 1; j <= getNumCellsUpDown(); j++) {
				Polygon tempShape = new Polygon();
				Set<Point2D> tempSet = new HashSet<>();
				tempSet.add(new Point2D((i - 1) * cellWidth, (j - 1)
						* cellHeight));
				tempSet.add(new Point2D(i * cellWidth, (j - 1) * cellHeight));
				tempSet.add(new Point2D((i - 1) * cellWidth, j * cellHeight));
				tempSet.add(new Point2D(i * cellWidth, j * cellHeight));
				tempShape.getPoints().addAll(
						new Double[] { (double) ((i - 1) * cellWidth),
								(double) ((j - 1) * cellHeight),
								(double) (i * cellWidth),
								(double) ((j - 1) * cellHeight),
								(double) ((i - 1) * cellWidth),
								(double) (j * cellHeight),
								(double) (i * cellWidth),
								(double) (j * cellHeight) });
				Cell temp = new Cell(count, tempShape, tempSet);
				addVertex(temp);
				count++;
			}
	}

	/**
	 * Correctly connect all cells to simulate a square grid where each cell has
	 * four neighbors. This means connecting each cell with the cell of ID one
	 * greater than it, except if the ID of the cell is divisible by the number
	 * of cells in numCellsAcross. This also means connecting each cell with the
	 * cell of ID greater by numCellsAcross.
	 */

	// public boolean isNeighbors(Cell first, Cell second) {
	// return first.getID() == second.getID() - 1
	// && first.getID() % getNumCellsAcross() != 0
	// || first.getID() == second.getID() - getNumCellsAcross();
	// }

	public boolean isNeighbors(Cell first, Cell second) {
		return numPointsInCommon(first, second) >= MIN_POINTS_IN_COMMON;
	}

	/**
	 * Calculate the appropriate ID for a row and column by multiplying the row
	 * number by the number of cells across, and adding the column number
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public int calculateID(int row, int col) {
		return (row - 1) * getNumCellsAcross() + col;
	}

}

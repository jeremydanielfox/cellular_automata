import javafx.scene.shape.Polygon;

public class SquareGraph extends BaseGraph {
	private int numCellsAcross;
	private int numCellsUpDown;
	private int myScreenWidth;
	private int myScreenHeight;
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
		numCellsAcross = numCellsWidth;
		numCellsUpDown = numCellsHeight;
		myScreenWidth = screenWidth;
		myScreenHeight = screenHeight;
		calculateValues();
		initializeCells();
		connectCells();
	}

	/**
	 * Calculate the width and height of each cell
	 */
	private void calculateValues() {
		cellWidth = myScreenWidth / numCellsAcross;
		cellHeight = myScreenHeight / numCellsUpDown;
	}

	/**
	 * Generate all necessary cells. Assign each cell an appropriate ID. Create
	 * a Polygon for each Cell. Assign correct matrix of points for each
	 * Polygon. Add each Polygon to each Cell. Add each Cell to the Graph.
	 */
	public void initializeCells() {
		int count  = 1;
		for (int i = 1; i <= numCellsAcross; i++)
			for (int j = 1; j <= numCellsUpDown; j++) {
				Polygon tempShape = new Polygon();
				tempShape.getPoints().addAll(
						new Double[] { (double) ((i - 1) * cellWidth),
								(double) ((j - 1) * cellHeight),
								(double) (i * cellWidth),
								(double) ((j - 1) * cellHeight),
								(double) ((i - 1) * cellWidth),
								(double) (j * cellHeight),
								(double) (i * cellWidth),
								(double) (j * cellHeight) });
				Cell temp = new Cell(count, tempShape);
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
	public void connectCells() {
		for (Cell first : this.getAllCells())
			for (Cell second : this.getAllCells()) {
				if (first.getID() == second.getID() - 1
						&& first.getID() % numCellsAcross != 0)
					connect(first, second);
				if (first.getID() == second.getID() - numCellsAcross)
					connect(first, second);
			}

	}
}

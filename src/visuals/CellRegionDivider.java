package visuals;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * This is the abstract class for the CellRegionDivider, which will create all
 * of the appropriate Polygon objects, assign their coordinates, and return them
 * as a 2D array. This array will then be fed into the graph, which will use it
 * to add each shape to each cell.
 * 
 * @author Jeremy
 *
 */
public abstract class CellRegionDivider {
	private int numCellsWidth;
	private int numCellsHeight;
	private int screenHeight;
	private int screenWidth;
	private int xOffset;
	private int yOffset;
	private int myCellWidth;
	private int myCellHeight;
	private int numPoints;
	private static final Color GRID_LINES_COLOR = Color.BLACK;
	private static final String SET_GRID_LINES = "On";

	public CellRegionDivider(int cellsAcross, int cellsUpDown, int height,
			int width, int xspace, int yspace) {
		numCellsWidth = cellsAcross;
		numCellsHeight = cellsUpDown;
		screenHeight = height;
		screenWidth = width;
		xOffset = xspace;
		yOffset = yspace;
		calculateValues();

	}

	/**
	 * Divides the screen into polygon objects and returns a 2D array of these
	 * polygons based on their row and column on the screen
	 * 
	 * @param setStroke
	 *            , a string that represents whether or not grid lines should be
	 *            displayed
	 * @return
	 */
	public Polygon[][] divideSpace(String setStroke) {
		Polygon[][] myShapeArray = new Polygon[getNumCellsHeight()][getNumCellsWidth()];
		int count = 0;
		for (int i = 0; i < getNumCellsHeight(); i++) {
			for (int j = 0; j < getNumCellsWidth(); j++) {
				Polygon tempShape = new Polygon();
				if (setStroke.equals((SET_GRID_LINES)))
					tempShape.setStroke(GRID_LINES_COLOR);
				assignPoints(tempShape, i, j, count);
				myShapeArray[i][j] = tempShape;
				count++;
			}
			count++;
		}

		return myShapeArray;
	}

	/**
	 * Calculates the height and width of each cell
	 */
	public abstract void calculateValues();

	/**
	 * Assigns points to the given polygon based on the row, column, and number
	 * of polygon
	 * 
	 * @param myShape
	 * @param i
	 * @param j
	 * @param count
	 */
	protected abstract void assignPoints(Polygon myShape, int i, int j,
			int count);

	protected int getCellWidth() {
		return myCellWidth;
	}

	protected int getCellHeight() {
		return myCellHeight;
	}

	protected void setCellWidth(int width) {
		myCellWidth = width;
	}

	protected void setCellHeight(int height) {
		myCellHeight = height;
	}

	protected void setNumPoints(int points) {
		numPoints = points;
	}

	protected int getNumPoints() {
		return numPoints;
	}

	protected int getNumCellsWidth() {
		return numCellsWidth;
	}

	protected int getNumCellsHeight() {
		return numCellsHeight;
	}

	protected int getScreenHeight() {
		return screenHeight;
	}

	protected int getScreenWidth() {
		return screenWidth;
	}

	protected int getXOffset() {
		return xOffset;
	}

	protected int getYOffset() {
		return yOffset;
	}

}

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
	
	public Polygon[][] divideSpace() {
		Polygon[][] myShapeArray = new Polygon[getNumCellsHeight()][getNumCellsWidth()];
		for (int i=0;i<getNumCellsHeight();i++)
			for (int j=0;j<getNumCellsWidth();j++) {
				Polygon tempShape = new Polygon();
				assignPoints(tempShape, i, j);
				myShapeArray[i][j] = tempShape;
			}
		return myShapeArray;
	}
	
	public void calculateValues() {
		myCellWidth = getScreenWidth() / getNumCellsWidth();
		myCellHeight = getScreenHeight() / getNumCellsHeight();
	}
	
	protected abstract void assignPoints(Polygon myShape,int i, int j);
	
	protected int getCellWidth() {
		return myCellWidth;
	}
	
	protected int getCellHeight() {
		return myCellHeight;
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

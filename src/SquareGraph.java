import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class SquareGraph extends BaseGraph {
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
			int screenHeight, int xOffset, int yOffset, int points,
			int defaultState, Color defaultColor, String model) {
		super(numCellsWidth, numCellsHeight, screenWidth, screenHeight,
				xOffset, yOffset, points, defaultState, defaultColor, model);
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
	public void initializeCells(int defaultState, Color defaultColor) {
		int count = 1;
		CellFactory myFactory = new CellFactory();
		for (int i = 1; i <= getNumCellsAcross(); i++)
			for (int j = 1; j <= getNumCellsUpDown(); j++) {
				Polygon tempShape = new Polygon();
				List<Point2D> tempList = new ArrayList<>();
				tempList.add(new Point2D(getXOffset() + (i - 1) * cellWidth,
						getYOffset() + (j - 1) * cellHeight));
				tempList.add(new Point2D(getXOffset() + (i - 1) * cellWidth,
						getYOffset() + j * cellHeight));
				tempList.add(new Point2D(getXOffset() + i * cellWidth,
						getYOffset() + j * cellHeight));
				tempList.add(new Point2D(getXOffset() + i * cellWidth,
						getYOffset() + (j - 1) * cellHeight));
				// tempShape
				// .getPoints()
				// .addAll(new Double[] {
				// (double) (getXOffset() + (i - 1) * cellWidth),
				// (double) (getYOffset() + (j - 1) * cellHeight),
				// (double) (getXOffset() + (i - 1) * cellWidth),
				// (double) (getYOffset() + j * cellHeight),
				// (double) (getXOffset() + i * cellWidth),
				// (double) (getYOffset() + j * cellHeight),
				// (double) (getXOffset() + i * cellWidth),
				// (double) (getYOffset() + (j - 1) * cellHeight) });
				Cell temp = myFactory.createSpecifiedCell(getModelName(),
						count, tempShape, tempList, defaultState, defaultColor);
				temp.setShapeVerticies();
				addVertex(temp);
				
				count++;
			}
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

	public void updateStateOfCell(ConfigCellInfo myBabyCell, Color color) {
		if (myBabyCell == null) {
			System.out
					.println("Can't update state of cell because ConfigCellInfo is null");
			return;
		}
		int row = myBabyCell.getRow();
		int col = myBabyCell.getCol();
		int ID = calculateID(row, col);
		getCell(ID).setCurrentState(myBabyCell.getIntState());
		getCell(ID).getShape().setFill(color);
		InhabitantFactory myInhabitantFactory = new InhabitantFactory();
		getCell(ID).setInhabitant(
				myInhabitantFactory.createSpecifiedInhabitant(
						myBabyCell.getStringState(), myBabyCell.getIntState()));
		System.out.println(color + " " + ID);
	}
}

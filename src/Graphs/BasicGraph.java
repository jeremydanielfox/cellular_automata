package Graphs;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import CellsAndComponents.Cell;
import Factories.CellFactory;
import Factories.EdgeManagerFactory;
import Factories.InhabitantFactory;

public class BasicGraph extends BaseGraph {
	private Point2D RIGHT;
	private Point2D LEFT;
	private Point2D UP;
	private Point2D DOWN;

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
	public BasicGraph(Polygon[][] myShapes, int numCellsWidth,
			int numCellsHeight, int defaultState, Color defaultColor,
			String model,String graphType, String edgeType) {
		super(myShapes, numCellsWidth, numCellsHeight, defaultState,
				defaultColor, model,graphType, edgeType);
	}

	public void initializeConstants() {
		RIGHT = new Point2D(1, 0);
		LEFT = new Point2D(-1, 0);
		UP = new Point2D(0, -1);
		DOWN = new Point2D(0, 1);
	}

	/**
	 * Generate all necessary cells. Assign each cell an appropriate ID. Create
	 * a Polygon for each Cell. Assign correct matrix of points for each
	 * Polygon. Add each Polygon to each Cell. Add each Cell to the Graph.
	 */
	public void initializeCells(Polygon[][] myShapes, int defaultState,
			Color defaultColor) {
		int count = 1;
		CellFactory myFactory = new CellFactory();
		for (int i = 0; i < myShapes.length; i++)
			for (int j = 0; j < myShapes[0].length; j++) {
				List<Point2D> tempList = new ArrayList<>();
				Cell temp = myFactory.createSpecifiedCell(getModelName(),
						count, myShapes[i][j], tempList, defaultState,
						defaultColor);
				temp.setFutureState(defaultState);
				// do plus one in order to have a grid that starts at (1,1)
				getCellPointMap().put(temp, new Point2D(i + 1, j + 1));
				addVertex(temp);
				temp.setShapeVerticies();
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

	//
	// public void updateStateOfCell(ConfigCellInfo myBabyCell, Color color) {
	// if (myBabyCell == null) {
	// System.out
	// .println("Can't update state of cell because ConfigCellInfo is null");
	// return;
	// }
	// int row = myBabyCell.getRow();
	// int col = myBabyCell.getCol();
	// int ID = calculateID(row, col);
	// getCell(ID).setCurrentState(myBabyCell.getIntState());
	// getCell(ID).getShape().setFill(color);
	// InhabitantFactory myInhabitantFactory = new InhabitantFactory();
	// getCell(ID).setInhabitant(
	// myInhabitantFactory.createSpecifiedInhabitant(
	// myBabyCell.getStringState(), myBabyCell.getIntState()));
	// }

	@Override
	public void connectCells() {
		for (Cell current : this.getAllCells()) {
			checkConnect(current, RIGHT);
			checkConnect(current, LEFT);
			checkConnect(current, DOWN);
			checkConnect(current, UP);
		}
	}

	public void checkConnect(Cell myCell, Point2D myPoint) {
		Cell neighbor = getNeighbor(myCell, myPoint);
		if (neighbor != null)
			connect(myCell, neighbor);
	}

	// public static void main(String[] args) {
	// SquareGraph myGraph = new
	// SquareGraph(3,5,100,100,0,0,1,0,Color.BEIGE,"");
	// for (Cell current: myGraph.getAllCells()) {
	// printNeighbors(current, myGraph);
	// }
	// }
	// public static void printNeighbors(Cell myCell, BaseGraph myGraph) {
	// System.out.println();
	// System.out.println(myCell.getID());
	// for (Cell current:myGraph.getNeighbors(myCell)) {
	// System.out.println(current.getID());
	// }
	// }

	@Override
	public void manageEdgeConditions() {
		// TODO Auto-generated method stub
//		EdgeManager myManager = new EightNeighborToroid(this);
		 EdgeManagerFactory myManagerFactory = new EdgeManagerFactory();
		 EdgeManager myEdgeManager = myManagerFactory.createSpecifiedManager(
		 getType() + getEdgeType(), this);

	}
}

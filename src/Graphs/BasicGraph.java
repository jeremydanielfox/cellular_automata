package Graphs;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import CellsAndComponents.Cell;
import Factories.CellConnectorFactory;
import Factories.CellFactory;
import Factories.EdgeManagerFactory;

/**
 * This is the graph class that will give all shapes four neighbors, one in each
 * of the cardinal directions. This class will work for both squares and
 * triangles
 * 
 * @author Jeremy
 *
 */
public class BasicGraph extends BaseGraph {
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
			String model, String graphType, String edgeType) {
		super(myShapes, numCellsWidth, numCellsHeight, defaultState,
				defaultColor, model, graphType, edgeType);
	}

	/**
	 * Generate all necessary cells. Assign each cell an appropriate ID. Create
	 * a Polygon for each Cell. Assign correct matrix of points for each
	 * Polygon. Add each Polygon to each Cell. Add each Cell to the Graph.
	 */
	protected void initializeCells(Polygon[][] myShapes, int defaultState,
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
				// add one to both i and j so that the grid starts at (1,1)
				getCellPointMap().put(temp, new Point2D(i + 1, j + 1));
				addVertex(temp);
				count++;
			}
	}

	@Override
	protected void connectCells() {
		CellConnectorFactory myFactory = new CellConnectorFactory();
		CellConnector myConnector = myFactory.createSpecifiedConnector(
				getType(), this);
		myConnector.connectCells();
	}

	protected void manageEdgeConditions() {
		EdgeManagerFactory myManagerFactory = new EdgeManagerFactory();
		EdgeManager myEdgeManager = myManagerFactory.createSpecifiedManager(
				getType() + getEdgeType(), this);
		myEdgeManager.linkEdges();

	}
}

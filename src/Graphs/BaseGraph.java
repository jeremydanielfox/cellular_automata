package Graphs;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import CellsAndComponents.Cell;

/**
 * This the abstract graph class that every type of graph we create will extend
 * This is a basic implementation of a graph of Cells to Cells that uses a map
 * to simulate Cell edges
 * 
 * 
 * @author Jeremy, Team 12
 *
 */

public abstract class BaseGraph {

	private Map<Cell, Collection<Cell>> myEdges = new HashMap<>();
	private CellPointMap myCellPointMap = new CellPointMap();
	private int numCellsAcross;
	private int numCellsUpDown;
	private String myModel;
	private String myEdgeType;
	private String myType;
	private boolean isWrapped;

	public BaseGraph(Polygon[][] myShapes, int numCellsWidth,
			int numCellsHeight, int defaultState, Color defaultColor,
			String model) {

		numCellsAcross = myShapes.length;
		numCellsUpDown = myShapes[0].length;
		myModel = model;
		isWrapped = false;
		initializeConstants();
		initializeCells(myShapes, defaultState, defaultColor);
		connectCells();
		manageEdgeConditions();

	}

	public void setWrap() {
		isWrapped = true;
	}

	public boolean isWrapped() {
		return isWrapped;
	}

	public int getNumCellsAcross() {
		return numCellsAcross;
	}

	public int getNumCellsUpDown() {
		return numCellsUpDown;
	}


	public CellPointMap getCellPointMap() {
		return myCellPointMap;
	}

	public Collection<Cell> getNeighbors(Cell myCell) {
		return myEdges.get(myCell);
	}

	public Cell getNeighbor(Cell myCell, Point2D change) {
		Point2D myPoint = getCellPointMap().get(myCell);
		Point2D temp = myPoint.add(change);
		return myCellPointMap.get(temp);
	}

	public void addVertex(Cell myCell) {
		if (myCell == null) {
			System.out.println("Can't add vertex because not a cell");
			return;
		}
		Collection<Cell> temp = new HashSet<Cell>();
		myEdges.put(myCell, temp);
	}

	private void addEdge(Cell from, Cell to) {
		if (from == null || to == null) {
			System.out
					.println("Can't addEdge because one ore more of the inputs is not a cell");
			return;
		}
		Collection<Cell> temp = getNeighbors(from);
		temp.add(to);
		myEdges.put(from, temp);
	}

	public void connect(Cell first, Cell second) {
		if (first == null || second == null) {
			System.out
					.println("Can't Connect because one of the inputs is null");
			return;
		}
		if (first.equals(second))
			return;
		addEdge(first, second);
		addEdge(second, first);
	}

	public Iterable<Cell> getAllCells() {
		return Collections.unmodifiableSet(myEdges.keySet());
	}

	public Cell getCell(int ID) {
		for (Cell current : getAllCells())
			if (current.getID() == ID)
				return current;
		return null;

	}

	public abstract void connectCells();

	public abstract void manageEdgeConditions();

	public Cell getTranslatedCell(Cell current, Point2D change) {
		if (!isWrapped()) {
			Point2D dest = myCellPointMap.get(current).add(change);
			return myCellPointMap.get(dest);
		}
		Point2D dest = myCellPointMap.get(current).add(change);
		double x = dest.getX();
		double y = dest.getX();
		if (x < 1)
			x += numCellsAcross;
		else if (x > numCellsAcross)
			x = x % numCellsAcross;
		if (y < 1)
			y += numCellsUpDown;
		else if (y > numCellsUpDown)
			y = y % numCellsUpDown;
		dest = new Point2D(x, y);
		return myCellPointMap.get(dest);
	}

	public Point2D getPointFromCell(Cell current) {
		return myCellPointMap.get(current);
	}

	public String getModelName() {
		return myModel;
	}

	public String getEdgeType() {
		return myEdgeType;
	}

	public String getType() {
		return myType;
	}

	public abstract void initializeConstants();

	public abstract void initializeCells(Polygon[][] myShapes,
			int defaultState, Color defaultColor);

	public abstract int calculateID(int row, int col);

	public abstract void updateStateOfCell(ConfigCellInfo myBabyCell,
			Color color);
}

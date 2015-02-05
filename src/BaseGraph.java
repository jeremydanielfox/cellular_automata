import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

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
	private CellPointMap myCellPointMap= new CellPointMap();
	private int MIN_POINTS_IN_COMMON;
	private int horizontalOffset;
	private int verticalOffset;
	private int numCellsAcross;
	private int numCellsUpDown;
	private int myScreenWidth;
	private int myScreenHeight;
	private String myModel;

	public BaseGraph(int numCellsWidth, int numCellsHeight, int screenWidth,
			int screenHeight, int xOffset, int yOffset, int points,
			int defaultState, Color defaultColor, String model) {
		
		numCellsAcross = numCellsWidth;
		numCellsUpDown = numCellsHeight;
		myScreenWidth = screenWidth;
		myScreenHeight = screenHeight;
		horizontalOffset = xOffset;
		verticalOffset = yOffset;
		MIN_POINTS_IN_COMMON = points;
		myModel = model;
		calculateValues();
		initializeCells(defaultState, defaultColor);
		connectCells();
		manageEdgeConditions();

	}

	public int getNumCellsAcross() {
		return numCellsAcross;
	}

	public int getNumCellsUpDown() {
		return numCellsUpDown;
	}

	public int getMyScreenWidth() {
		return myScreenWidth;
	}

	public int getMyScreenHeight() {
		return myScreenHeight;
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

	public int getXOffset() {
		return horizontalOffset;
	}

	public int getYOffset() {
		return verticalOffset;
	}

	public abstract void connectCells();
	
	public abstract void manageEdgeConditions();
//	{
//		for (Cell first : this.getAllCells())
//			for (Cell second : this.getAllCells())
//				if (isNeighbors(first, second))
//					connect(first, second);
//	}
//
//	public int numPointsInCommon(Cell first, Cell second) {
//		Set<Point2D> temp = new HashSet(first.getVerticies());
//		Set<Point2D> newtemp = new HashSet(second.getVerticies());
//		temp.retainAll(new HashSet(second.getVerticies()));
//		return temp.size();
//	}
//
//	public int getMinPointsInCommon() {
//		return MIN_POINTS_IN_COMMON;
//	}
//
//	public boolean isNeighbors(Cell first, Cell second) {
//		return numPointsInCommon(first, second) >= getMinPointsInCommon()
//				&& !first.equals(second) || additionalNeighborCondition(first,second);
//	}

//	public boolean additionalNeighborCondition(Cell first, Cell second) {
//		return false;
//	}

	public String getModelName() {
		return myModel;
	}

	public abstract void initializeCells(int defaultState, Color defaultColor);

	public abstract void calculateValues();

	public abstract int calculateID(int row, int col);

	public abstract void updateStateOfCell(ConfigCellInfo myBabyCell,
			Color color);
}

package Graphs;
import java.util.ArrayList;
import java.util.List;

import CellsAndComponents.Cell;
import javafx.geometry.Point2D;

/**
 * This manager class will allow us to implement new edge conditions separate
 * from the graph shape. The graph will create a new edge manager and pass in
 * the type of edge condition it wants.
 * 
 * @author Jeremy, Team 12
 *
 */
public class EdgeManager {
	private List<Cell> topRow = new ArrayList<>();
	private List<Cell> leftCol = new ArrayList<>();
//	private final Point2D RIGHT = new Point2D(1, 0);
//	private final Point2D LEFT = new Point2D(-1, 0);
//	private final Point2D UP = new Point2D(0, -1);
//	private final Point2D DOWN = new Point2D(0, 1);
//	private final Point2D UP_RIGHT = new Point2D(1, -1);
//	private final Point2D UP_LEFT = new Point2D(-1, -1);
//	private final Point2D DOWN_RIGHT = new Point2D(1, 1);
//	private final Point2D DOWN_LEFT = new Point2D(-1, 1);
	private final int BEGINNING = 1;
	private final BaseGraph myGraph;

	public EdgeManager(BaseGraph graph) {
		myGraph = graph;
		makeRowAndCol();
		linkEdges();
	}

	private void makeRowAndCol() {
		for (Cell current : myGraph.getAllCells()) {
			// unchain method by pulling point map method into graph
			if (myGraph.getCellPointMap().get(current).getY() == BEGINNING)
				topRow.add(current);
			if (myGraph.getCellPointMap().get(current).getX() == BEGINNING)
				leftCol.add(current);
		}
	}
	
	protected void linkEdges() {
		
	}
	
	protected BaseGraph getGraph() {
		return myGraph;
	}
	
	protected List<Cell> getTopRow() {
		return topRow;
	}
	
	protected List<Cell> getLeftCol() {
		return leftCol;
	}

}

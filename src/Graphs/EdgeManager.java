package Graphs;

import java.util.ArrayList;
import java.util.List;

import CellsAndComponents.Cell;

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
	private final int BEGINNING = 1;
	private final BaseGraph myGraph;

	public EdgeManager(BaseGraph graph) {
		myGraph = graph;
		makeRowAndCol();
		linkEdges();
	}

	private void makeRowAndCol() {
		for (Cell current : myGraph.getAllCells()) {
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

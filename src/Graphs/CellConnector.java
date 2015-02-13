// This entire file is part of my masterpiece
// Jeremy Fox
package Graphs;

import javafx.geometry.Point2D;
import CellsAndComponents.Cell;

public abstract class CellConnector {
	private BaseGraph myGraph;
	public CellConnector(BaseGraph graph) {
		myGraph = graph;
	}
	
	protected void connectCells() {
		for (Cell current : myGraph.getAllCells()) {
			checkAllConnections(current);
		}
	}
	
	protected abstract void checkAllConnections(Cell current);

	protected void checkConnect(Cell myCell, Point2D myPoint) {
		Cell neighbor = myGraph.getNeighbor(myCell, myPoint);
		if (neighbor != null)
			myGraph.connect(myCell, neighbor);
	}
}

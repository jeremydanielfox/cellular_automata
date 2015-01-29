import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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

	public BaseGraph() {
		initializeCells();
		connectCells();
	}

	public Collection<Cell> getNeighbors(Cell myCell) {
		return myEdges.get(myCell);
	}

	public void addVertex(Cell myCell) {
		Collection<Cell> temp = new HashSet<Cell>();
		myEdges.put(myCell, temp);
	}

	public void removeVertex(Cell myCell) {
		if (myEdges.containsKey(myCell))
			myEdges.remove(myCell);
	}

	public void addEdge(Cell from, Cell to) {
		Collection<Cell> temp = getNeighbors(from);
		if (to != null)
			temp.add(to);
		myEdges.put(from, temp);
	}

	public void connect(Cell first, Cell second) {
		addEdge(first, second);
		addEdge(second, first);
	}

	public Iterable<Cell> getAllCells() {
		return myEdges.keySet();
	}

	public abstract void initializeCells();

	public abstract void connectCells();

}

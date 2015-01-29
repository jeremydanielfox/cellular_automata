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
	private int numCellsAcross;
	private int numCellsUpDown;
	private int myScreenWidth;
	private int myScreenHeight;

	public BaseGraph(int numCellsWidth, int numCellsHeight, int screenWidth,
			int screenHeight) {
		numCellsAcross = numCellsWidth;
		numCellsUpDown = numCellsHeight;
		myScreenWidth = screenWidth;
		myScreenHeight = screenHeight;
		initializeCells();
		connectCells();
		calculateValues();
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

	public Cell getCell(int ID) {
		for (Cell current : getAllCells())
			if (current.getID() == ID)
				return current;
		return null;

	}

	public void connectCells() {
		for (Cell first : this.getAllCells())
			for (Cell second : this.getAllCells())
				if (isNeighbors(first, second))
					connect(first, second);
	}

	public abstract boolean isNeighbors(Cell first, Cell second);

	public abstract void initializeCells();

	protected abstract void calculateValues();

	public abstract int calculateID(int row, int col);
}

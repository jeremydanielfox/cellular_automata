import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * This class changes the states of cells, connects model and graph.
 * 
 * @author Megan Gutter
 *
 */

public class SimEngine {
	private String myModelName;
	private Map<String, Double> myParameters;
	private List<ConfigCellInfo> myCellsToConfig;

	// while we don't have factories
	private BaseGraph myGraph;
	private BaseModel myModel;

	public SimEngine(String model, Map<String, Double> parameters,
			List<ConfigCellInfo> cellsToConfig, int xOffset, int yOffset) {
		myModelName = model;
		myParameters = parameters;
		myCellsToConfig = cellsToConfig;
		myModel = new GameOfLife(myParameters);
		// assuming this data is passed in the parameters map?
		myGraph = new SquareGraph(myParameters.get("columns").intValue(),
				myParameters.get("rows").intValue(),
				myParameters.get("screenWidth").intValue(),
				myParameters.get("screenHeight").intValue(), 
				xOffset, yOffset,
				myModel.getSharePointsForNeighbor());
		myGraph.initializeCells();
	}

	public Collection<Cell> updateCells() {
		determineFutureStates();
		setFutureToCurrentStates();
		return (Collection<Cell>) myGraph.getAllCells();
	}

	private void determineFutureStates() {
		for (Cell c : myGraph.getAllCells()) {
			myModel.updateFutureState(c, myGraph.getNeighbors(c));
		}
	}

	private void setFutureToCurrentStates() {
		for (Cell c : myGraph.getAllCells()) {
			c.setCurrentState(c.getFutureState());
		}
	}

	public Collection<Cell> getListOfCells() {
		return (Collection<Cell>) myGraph.getAllCells();
	}
}
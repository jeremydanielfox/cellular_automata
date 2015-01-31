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
			List<ConfigCellInfo> cellsToConfig, int cellRegionWidth,
			int cellRegionHeight, int cellRegionXOffset, int cellRegionYOffset) {
		myModelName = model;
		myParameters = parameters;
		myCellsToConfig = cellsToConfig;
		myModel = new Fire(myParameters);
		myGraph = new SquareGraph(myParameters.get("columns").intValue(),
				myParameters.get("rows").intValue(), cellRegionWidth,
				cellRegionHeight, cellRegionXOffset, cellRegionYOffset,
				myModel.getSharePointsForNeighbor(),
				myModel.getDefaultState(),
				myModel.getDefaultColor());
		//myGraph.initializeCells(myModel.getDefaultState(), myModel.getDefaultColor());
		setUpInitCells();
	}

	public Collection<Cell> updateCells() {
		determineFutureStates();
		setFutureToCurrentStates();
		return (Collection<Cell>) myGraph.getAllCells();
	}

	private void determineFutureStates() {
		/*for (Cell c : myGraph.getAllCells()) {
			myModel.updateFutureState(c, myGraph.getNeighbors(c));
		}*/
		myModel.updateFutureStates(myGraph.getAllCells(), myGraph);
	}

	private void setFutureToCurrentStates() {
		for (Cell c : myGraph.getAllCells()) {
			c.setCurrentState(c.getFutureState());
		}
	}

	public Collection<Cell> getListOfCells() {
		return (Collection<Cell>) myGraph.getAllCells();
	}
	
	private void setUpInitCells() {
		for (ConfigCellInfo c : myCellsToConfig) {
			c.setIntState(myModel.getIntForState(c.getStringState()));
			myGraph.updateStateOfCell(c, myModel.getColorForStringState(c.getStringState()));
		}
	}
}

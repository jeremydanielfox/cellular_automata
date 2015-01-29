import java.util.Collection;
import java.util.List;
import java.util.Map;
/**
 * This class changes the states of cells, connects model and graph.
 * 
 * @author Megan Gutter
 *
 */

public class simEngine {
	private String myModelName;
	private Map<String, Integer> myParameters;
	private List<ConfigCellInfo> myCellsToConfig;
	
	//while we don't have factories
	private BaseGraph myGraph;
	private BaseModel myModel;
	
	public simEngine(String model, Map<String, Integer> parameters, List<ConfigCellInfo> cellsToConfig) {
		myModelName = model;
		myParameters = parameters;
		myCellsToConfig = cellsToConfig;
		//assuming this data is passed in the parameters map?
		myGraph = new SquareGraph(myParameters.get("numCellsWidth"), myParameters.get("numCellsHeight"), 
				myParameters.get("screenWidth"), myParameters.get("screenHeight"), myParameters.get("points"));
		myGraph.initializeCells();
		myModel = new GameOfLife(myParameters);
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
		for (Cell c: myGraph.getAllCells()) {
			c.setCurrentState(c.getFutureState());
		}
	}
	
	public Collection<Cell> getListOfCells() {
		return (Collection<Cell>) myGraph.getAllCells();
	}
}

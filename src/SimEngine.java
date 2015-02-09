import java.util.Collection;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import CellsAndComponents.Cell;
import Factories.GraphFactory;
import Factories.ModelFactory;
import Graphs.BaseGraph;
import Graphs.ConfigCellInfo;
import Models.BaseModel;

/**
 * This class acts as the "model" in our MVC design. It is responsible for
 * keeping track of the model and all of the cells, and contains the method to
 * update the simulation at each iteration of the simulation game loop. It
 * initializes the correct simulation model and graph type and acts as a bridge
 * between the two to run the simulation.
 * 
 * @author Megan Gutter
 *
 */

public class SimEngine {
	private String myModelName;
	private Map<String, Double> myParameters;
	private List<ConfigCellInfo> myCellsToConfig;
	private BaseGraph myGraph;
	private BaseModel myModel;

	public SimEngine(Polygon[][] myPolygons, String random,
			boolean randWithParams, Map<String, Double> initProportions,
			String model, String graphType, String edgeType,
			Map<String, Double> parameters, List<ConfigCellInfo> cellsToConfig,
			int cellRegionWidth, int cellRegionHeight,
			Map<String, Color> stateToColorMap) {
		myModelName = model;
		myParameters = parameters;
		ModelFactory myModFactory = new ModelFactory();
		myModel = myModFactory.createSpecifiedModel(myModelName, myParameters,
				stateToColorMap);
		if (randWithParams || random.equals("YES")) {
			RandomConfiguration randConfigGenerator = new RandomConfiguration(
					myModel, myParameters.get("rows").intValue(), myParameters
							.get("columns").intValue(), randWithParams,
					initProportions);
			myCellsToConfig = randConfigGenerator.getRandConfigCells();
		} else {
			myCellsToConfig = cellsToConfig;
		}
		Color temp = myModel.getDefaultColor();
		GraphFactory myGraphFactory = new GraphFactory();
		myGraph = myGraphFactory.createSpecifiedGraph(myPolygons, myParameters
				.get("columns").intValue(),
				myParameters.get("rows").intValue(), myModel
						.getDefaultIntState(), myModel.getDefaultColor(),
				myModelName, graphType, edgeType);
		setUpInitCells();
	}

	/**
	 * This method is called to advance the state state of the simulation by one
	 * iteration. It calls on the model to update the future states of the
	 * cells, then iterates through the cells to make the future state the
	 * current state in order to implement the lock-step synchronization of the
	 * models.
	 * 
	 * @return a list of updated cells
	 */
	public Collection<Cell> updateCells() {
		determineFutureStates();
		setFutureToCurrentStates();
		return (Collection<Cell>) myGraph.getAllCells();
	}

	private void determineFutureStates() {
		myModel.updateFutureStates(myGraph.getAllCells(), myGraph);
	}

	private void setFutureToCurrentStates() {
		for (Cell c : myGraph.getAllCells()) {
			c.setCurrentState(c.getFutureState());
			c.setFutureState(myModel.getDefaultIntState());
		}
	}

	/**
	 * Method returns a list of all cells in the current simulation
	 * 
	 * @return a list of all cells in the current simulation
	 */
	public Collection<Cell> getListOfCells() {
		return (Collection<Cell>) myGraph.getAllCells();
	}

	private void setUpInitCells() {
		myModel.setUpCellContents(myGraph, myCellsToConfig);
	}

	/**
	 * Takes in the string of a parameter name and a double value to change it
	 * to, and passes this information to the current model to change the value
	 * of the parameter.
	 * 
	 * @param paramName
	 * @param paramValue
	 */
	public void changeParam(String paramName, Double paramValue) {
		myModel.changeParam(paramName, paramValue);
	}

	/**
	 * Asks the model for the map of parameter string names to a list of their
	 * min, max, and current values and returns it.
	 * 
	 * @return a map of parameter string names to a list of their double min,
	 *         max, and cur values
	 */
	public Map<String, List<Double>> getParamMap() {
		return myModel.getParamNameMinMaxCur();
	}

	/**
	 * Returns an array of the string state names of the current model
	 * 
	 * @return an array of the string state names of the current model
	 */
	public String[] getStateNames() {
		return myModel.getMainStateNames();
	}

	/**
	 * Returns an array of integers that represents the number of cells of each
	 * type of state in the current phase of the simulation.
	 * 
	 * @return an int array representing the number of cells with each state in
	 *         the current state of the simulation
	 */
	public int[] getStateCounts() {
		return myModel.getCountMainStates(myGraph);
	}
}

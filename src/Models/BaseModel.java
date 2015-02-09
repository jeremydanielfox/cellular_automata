package Models;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import CellsAndComponents.Cell;
import Graphs.BaseGraph;
import Graphs.ConfigCellInfo;

/**
 * This the abstract model class that every type of model will extend
 * 
 * @author Megan Gutter
 *
 */

public abstract class BaseModel {
	private Map<String, Integer> stateToInt;
	private Map<String, Color> stateToColor;
	private Map<String, Double> parameterValues;

	public BaseModel(Map<String, Double> parameters) {
		stateToInt = new HashMap<String, Integer>();
		stateToColor = new HashMap<String, Color>();
		parameterValues = new HashMap<String, Double>();
	}

	protected void initializeMaps(List<String> states, List<Integer> stateInts,
			List<Color> stateColors) {
		for (int i = 0; i < states.size(); i++) {
			stateToInt.put(states.get(i), stateInts.get(i));
			stateToColor.put(states.get(i), stateColors.get(i));
		}
	}

	public Color selectNonNullColor(Color specifiedColor, Color defaultColor) {
		if (specifiedColor != null) {
			return specifiedColor;
		} else {
			return defaultColor;
		}
	}

	public Map<String, Integer> getStateToIntMap() {
		return stateToInt;
	}

	public Map<String, Color> getStateToColorMap() {
		return stateToColor;
	}

	public Map<String, Double> getParameterValuesMap() {
		return parameterValues;
	}

	/**
	 * Implemented in the models, getParamNameMinMaxCur() gets a map that
	 * correlates a string representing a state with a list of its min, max and
	 * current values, used to create the parameter sliders
	 */
	public Map<String, List<Double>> getParamNameMinMaxCur() {
		return new HashMap<String, List<Double>>();
	}

	public void changeParam(String param, Double value) {
		parameterValues.put(param, value);
	}

	public abstract Cell updateFutureState(Cell cellToUpdate,
			Collection<Cell> neighbors);

	/**
	 * This method is what takes each cell and sets the future state of each
	 * cell based on the rules of the model, either overriden in by the model or
	 * calls updateFutureCell which is written in the model.
	 * 
	 * @param cellsToUpdate
	 * @param graph
	 */

	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate,
			BaseGraph graph) {
		for (Cell c : cellsToUpdate) {
			updateFutureState(c, graph.getNeighbors(c));
		}
		return (Collection<Cell>) cellsToUpdate;
	}

	public int getIntForState(String state) {
		return stateToInt.get(state);
	}

	public abstract Color getDefaultColor();

	public abstract int getDefaultIntState();

	public abstract String getDefaultStringState();

	public Color getColorForStringState(String state) {
		return stateToColor.get(state);
	}

	/**
	 * Method used in many models that extend BaseModel, used to calculate the
	 * number of neighbors with the specified state.
	 * 
	 * @param state
	 * @param neighbors
	 */

	public int countNeighbors(int state, Collection<Cell> neighbors) {
		int neighborsWithState = 0;
		for (Cell c : neighbors) {
			if (c.getCurrentState() == state) {
				neighborsWithState += 1;
			}
		}
		return neighborsWithState;
	}

	public void changeFutureState(Cell cellToUpdate, int state, Color stateColor) {
		cellToUpdate.setFutureState(state);
		cellToUpdate.setColor(stateColor);
	}

	/**
	 * Purpose of setUpCellContents, which loops through the cellsToConfig and
	 * calls UpdateStateOfCell on them, is to set up the initial configuration
	 * of cells based on the information in the ConfigCellInfo objects that
	 * contain information parsed from the XML file
	 * 
	 * @param graph
	 * @param cellsToConfig
	 */

	public void setUpCellContents(BaseGraph graph,
			Iterable<ConfigCellInfo> cellsToConfig) {

		for (ConfigCellInfo c : cellsToConfig) {
			c.setIntState(getIntForState(c.getStringState()));
			updateStateOfCell(graph, c,
					getColorForStringState(c.getStringState()));
		}

	}

	public void updateStateOfCell(BaseGraph graph, ConfigCellInfo myBabyCell,
			Color color) {
		if (myBabyCell == null) {
			System.out
					.println("Can't update state of cell because ConfigCellInfo is null");
			return;
		}
		Cell current = graph.getCell(new Point2D(myBabyCell.getRow(),
				myBabyCell.getCol()));
		current.setCurrentState(myBabyCell.getIntState());
		current.getShape().setFill(color);
		addAdditionalCellInfo(current, myBabyCell);

	}

	public void addAdditionalCellInfo(Cell c, ConfigCellInfo myBabyCell) {
	}

	public int getNumStates() {
		return stateToInt.size();
	}

	public abstract String[] getMainStateNames();

	public int[] getCountMainStates(BaseGraph myGraph) {
		String[] myStrings = getMainStateNames();
		int[] returnValue = new int[myStrings.length];
		for (int i = 0; i < returnValue.length; i++)
			returnValue[i] = 0;
		for (Cell currentCell : myGraph.getAllCells()) {
			for (String currentString : stateToInt.keySet()) {
				if (currentCell.getCurrentState() == stateToInt
						.get(currentString))
					for (int i = 0; i < myStrings.length; i++)
						if (myStrings[i].equals(currentString))
							returnValue[i]++;

			}
		}
		return returnValue;
	}

}

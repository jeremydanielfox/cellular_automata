package Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import CellsAndComponents.Cell;
import Factories.InhabitantFactory;
import Graphs.BaseGraph;
import Graphs.ConfigCellInfo;

/**
 * This the abstract model class that every type of model will extend
 * 
 * @author Megan Gutter
 *
 */

public abstract class BaseModel {
	private int numPointsForNeighbor;
	private Map<String, Integer> stateToInt;
	private Map<String, Color> stateToColor;
	private Map<String, Double> parameterValues;

	public BaseModel(Map<String, Double> parameters, int points) {
		numPointsForNeighbor = points;
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

	public Map<String, Integer> getStateToIntMap() {
		return stateToInt;
	}

	public Map<String, Color> getStateToColorMap() {
		return stateToColor;
	}

	public Map<String, Double> getParameterValuesMap() {
		return parameterValues;
	}

	public Map<String, List<Double>> getParamNameMinMaxCur() {
		return new HashMap<String, List<Double>>();
	}

	public void changeParam(String param, Double value) {
		parameterValues.put(param, value);
	}

	public abstract Cell updateFutureState(Cell cellToUpdate,
			Collection<Cell> neighbors);

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

	public abstract int getDefaultState();

	public Color getColorForStringState(String state) {
		return stateToColor.get(state);
	}

	public int getSharePointsForNeighbor() {
		return numPointsForNeighbor;
	}

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

	public void setUpCellContents(BaseGraph graph,
			Iterable<ConfigCellInfo> cellsToConfig) {
		
		for (ConfigCellInfo c : cellsToConfig) {
			c.setIntState(getIntForState(c.getStringState()));
			updateStateOfCell(graph, c, getColorForStringState(c.getStringState()));
		}

	}
	
	public void updateStateOfCell(BaseGraph graph, ConfigCellInfo myBabyCell, Color color) {
		if (myBabyCell == null) {
			System.out
					.println("Can't update state of cell because ConfigCellInfo is null");
			return;
		}
		Cell current = graph.getCell(new Point2D(myBabyCell.getRow(), myBabyCell.getCol()));
		current.setCurrentState(myBabyCell.getIntState());
		current.getShape().setFill(color);
		addAdditionalCellInfo(current, myBabyCell);
//		needs to be overriden, deleted from sugarscape, and added only in water world
//		InhabitantFactory myInhabitantFactory = new InhabitantFactory();
//		current.setInhabitant(
//				myInhabitantFactory.createSpecifiedInhabitant(
//						myBabyCell.getStringState(), myBabyCell.getIntState()));
	}
	
	public void addAdditionalCellInfo(Cell c, ConfigCellInfo myBabyCell){
	}
	
	

}

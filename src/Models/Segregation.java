package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import CellsAndComponents.Cell;
import Graphs.BaseGraph;

/**
 * 
 * This is the Segregation model based off of Thomas Schelling's model
 * 
 * @author Jeremy, Team 12
 *
 */
public class Segregation extends BaseModel {
	public static final int EMPTY = 0;
	public static final int GROUP_ONE = 1;
	public static final int GROUP_TWO = 2;
	public static final int DEFAULT_INT_STATE = EMPTY;
	public static final String DEFAULT_STRING_STATE = "empty";
	public Color emptyColor;
	public Color groupOneColor;
	public Color groupTwoColor;
	public Color defaultColor;
	private static final double MIN_RATIO_NEIGHBORS = 0;
	private static final double MAX_RATIO_NEIGHBORS = 1.0;

	public Segregation(Map<String, Double> parameters,
			Map<String, Color> stateToColorMap) {
		super(parameters);
		emptyColor = selectNonNullColor(stateToColorMap.get("empty"),
				Color.WHITE);
		groupOneColor = selectNonNullColor(stateToColorMap.get("group_one"),
				Color.RED);
		groupTwoColor = selectNonNullColor(stateToColorMap.get("group_two"),
				Color.GREEN);
		defaultColor = emptyColor;

		List<String> myStates = new ArrayList<String>(Arrays.asList("empty",
				"group_one", "group_two"));
		List<Color> myColors = new ArrayList<>(Arrays.asList(emptyColor,
				groupOneColor, groupTwoColor));
		List<Integer> myInts = new ArrayList<>(Arrays.asList(EMPTY, GROUP_ONE,
				GROUP_TWO));
		initializeMaps(myStates, myInts, myColors);
		try {
			getParameterValuesMap().put("happinessRatio",
					(double) parameters.get("HappinessRatio"));
		} catch (NullPointerException e) {
			getParameterValuesMap().put("happinessRatio",
					(MIN_RATIO_NEIGHBORS + MAX_RATIO_NEIGHBORS) / 2);
		}
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		return null;
	}

	@Override
	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate,
			BaseGraph myGraph) {
		List<Cell> myCells = getRandomCollection(myGraph);
		for (Cell current : myCells) {
			current.setFutureState(current.getCurrentState());
		}
		Cell myUnhappyCell = null;
		Cell myEmptyCell = null;
		for (Cell current : myCells) {
			if (isReadyToSwitch(myUnhappyCell, myEmptyCell, myGraph))
				break;
			if (!isHappy(current, myGraph.getNeighbors(current))
					&& myUnhappyCell == null)
				myUnhappyCell = current;
			if (current.getCurrentState() == EMPTY && myEmptyCell == null)
				myEmptyCell = current;
		}
		if (isReadyToSwitch(myUnhappyCell, myEmptyCell, myGraph)) {
			myUnhappyCell.setFutureState(myEmptyCell.getCurrentState());
			myUnhappyCell.setColor(emptyColor);
			myEmptyCell.setFutureState(myUnhappyCell.getCurrentState());
			if (myUnhappyCell.getCurrentState() == GROUP_ONE)
				myEmptyCell.setColor(groupOneColor);

			else
				myEmptyCell.setColor(groupTwoColor);

		}
		return (Collection<Cell>) myGraph.getAllCells();

	}

	private boolean isReadyToSwitch(Cell first, Cell second, BaseGraph myGraph) {
		if (first == null || second == null)
			return false;
		return !isHappy(first, myGraph.getNeighbors(first))
				&& second.getCurrentState() == EMPTY
				|| !isHappy(second, myGraph.getNeighbors(second))
				&& first.getCurrentState() == EMPTY;
	}

	@Override
	public Color getDefaultColor() {
		return defaultColor;
	}

	@Override
	public int getDefaultIntState() {
		return DEFAULT_INT_STATE;
	}

	private boolean isHappy(Cell myCell, Collection<Cell> myNeighbors) {
		int myState = myCell.getCurrentState();
		if (myState == EMPTY)
			return true;
		float countDifferent = 0;
		for (Cell current : myNeighbors) {
			if (current.getCurrentState() != myState
					&& current.getCurrentState() != EMPTY)
				countDifferent++;
		}
		float ratio = countDifferent / (float) myNeighbors.size();

		return ratio <= getParameterValuesMap().get("happinessRatio");
	}

	private List<Cell> getRandomCollection(BaseGraph myGraph) {
		List<Cell> myCells = new ArrayList<Cell>();
		myCells.addAll((Collection<? extends Cell>) myGraph.getAllCells());
		Collections.shuffle(myCells);
		return myCells;
	}

	@Override
	public Map<String, List<Double>> getParamNameMinMaxCur() {
		Map<String, List<Double>> toReturn = new HashMap<>();
		ArrayList<Double> minMaxCurReproduce = new ArrayList<>();
		minMaxCurReproduce.add(0, MIN_RATIO_NEIGHBORS);
		minMaxCurReproduce.add(1, MAX_RATIO_NEIGHBORS);
		minMaxCurReproduce
				.add(2, getParameterValuesMap().get("happinessRatio"));
		toReturn.put("happinessRatio", minMaxCurReproduce);
		return toReturn;
	}

	@Override
	public String[] getMainStateNames() {
		return new String[] { "group_one", "group_two" };
	}

	public String getDefaultStringState() {
		return DEFAULT_STRING_STATE;
	}
}

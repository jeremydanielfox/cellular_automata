import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Graphs.ConfigCellInfo;
import Models.BaseModel;

/**
 * This class can create a configuration of randomly placed cells given the
 * proportion of states to be represented, or it can create a completely random
 * configuration including each state proportion. It depends on methods in the
 * BaseModel class that it uses to get critical information about the states
 * available.
 * 
 * @author Sierra, Megan
 *
 */
public class RandomConfiguration {
	private BaseModel myModel;
	private List<ConfigCellInfo> myRandConfigCells;
	private Random myGenerator;

	public RandomConfiguration(BaseModel model, int rows, int cols,
			boolean useConfigMap, Map<String, Double> stateProportions) {
		myGenerator = new Random();
		myModel = model;
		myRandConfigCells = new ArrayList<ConfigCellInfo>();
		if (!useConfigMap) {
			setRandConfigProp(stateProportions);
		}
		initializeConfigCellsDefault(rows, cols,
				myModel.getDefaultStringState());
		Collections.shuffle(myRandConfigCells);
		assignRandomStates(stateProportions, rows * cols);
		setRandomParams(myModel.getParamNameMinMaxCur());
	}

	private void assignRandomStates(Map<String, Double> stateProportions,
			int totalCells) {
		int curStartIndex = 0;
		for (String s : stateProportions.keySet()) {
			int numToDo = (int) (stateProportions.get(s) * totalCells);
			initializeConfigCellsProperProp(curStartIndex, numToDo, s);
			curStartIndex += numToDo;
		}
	}

	private void setRandConfigProp(Map<String, Double> stateProp) {
		ArrayList<String> states = new ArrayList<>(myModel.getStateToIntMap()
				.keySet());
		double proportionLeft = 1.0;
		Double proportion;
		int i = 0;
		for (i = 0; i < myModel.getNumStates() - 1; i++) {
			proportion = myGenerator.nextDouble() * proportionLeft;
			try {
				stateProp.put(states.get(i), proportion);
			} catch (IndexOutOfBoundsException e) {
				stateProp.put(Integer.toString(i), proportion);
			}
			proportionLeft -= proportion;
		}
		try {
			stateProp.put(states.get(states.size() - 1), proportionLeft);
		} catch (IndexOutOfBoundsException e) {
			stateProp.put(Integer.toString(i), proportionLeft);
		}
	}

	private void initializeConfigCellsProperProp(int start, int numToSet,
			String stateToSet) {
		for (int i = start; i < numToSet && i < myRandConfigCells.size(); i++) {
			(myRandConfigCells.get(i)).setStringState(stateToSet);
		}
	}

	private void initializeConfigCellsDefault(int rows, int cols,
			String defaultState) {
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				ConfigCellInfo toAdd = new ConfigCellInfo(i, j, defaultState);
				myRandConfigCells.add(toAdd);
			}
		}
	}

	private void setRandomParams(Map<String, List<Double>> params) {
		for (String s : params.keySet()) {
			Double minimum = params.get(s).get(0);
			Double range = params.get(s).get(1) - minimum;
			Double randParam = (myGenerator.nextDouble() * range) + minimum;
			myModel.getParameterValuesMap().put(s, randParam);
		}
	}

	public List<ConfigCellInfo> getRandConfigCells() {
		return myRandConfigCells;
	}

}

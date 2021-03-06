package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import CellsAndComponents.Cell;
import javafx.scene.paint.Color;

/**
 * This class implements the Fire model and extends BaseModel.
 * 
 * @author Megan Gutter
 *
 */

public class Fire extends BaseModel {
	private static final int EMPTY = 0;
	private static final int TREE = 1;
	private static final int BURNING = 2;
	private static final int DEFAULT_INT_STATE = TREE;
	private static final String DEFAULT_STRING_STATE = "tree";
	private Color emptyColor;
	private Color treeColor;
	private Color burningColor;
	private Color defaultColor;
	private static final double MIN_PROB_CATCH = 0;
	private static final double MAX_PROB_CATCH = 1.0;

	public Fire(Map<String, Double> parameters,
			Map<String, Color> stateToColorMap) {
		super(parameters);
		emptyColor = selectNonNullColor(stateToColorMap.get("empty"),
				Color.YELLOW);
		treeColor = selectNonNullColor(stateToColorMap.get("tree"),
				Color.FORESTGREEN);
		burningColor = selectNonNullColor(stateToColorMap.get("burning"),
				Color.RED);
		defaultColor = treeColor;
		List<String> myStates = new ArrayList<String>(Arrays.asList("empty",
				"tree", "burning"));
		List<Color> myColors = new ArrayList<>(Arrays.asList(emptyColor,
				treeColor, burningColor));
		List<Integer> myInts = new ArrayList<>(Arrays.asList(EMPTY, TREE,
				BURNING));
		initializeMaps(myStates, myInts, myColors);
		try {
			getParameterValuesMap().put("probCatch",
					(double) parameters.get("probCatch"));
		} catch (NullPointerException e) {
			getParameterValuesMap().put("probCatch",
					(MIN_PROB_CATCH + MAX_PROB_CATCH) / 2);
		}
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		double random = new Random().nextDouble();

		if (cellToUpdate.getCurrentState() == TREE
				&& countNeighbors(2, neighbors) > 0
				&& random < getParameterValuesMap().get("probCatch")) {
			changeFutureState(cellToUpdate, BURNING, burningColor);
		} else if (cellToUpdate.getCurrentState() == BURNING
				|| cellToUpdate.getCurrentState() == EMPTY) {
			changeFutureState(cellToUpdate, EMPTY, emptyColor);
		} else {
			changeFutureState(cellToUpdate, TREE, treeColor);
		}
		return cellToUpdate;
	}

	@Override
	public Color getDefaultColor() {
		return defaultColor;
	}

	@Override
	public int getDefaultIntState() {
		return DEFAULT_INT_STATE;
	}

	@Override
	public Map<String, List<Double>> getParamNameMinMaxCur() {
		Map<String, List<Double>> toReturn = new HashMap<>();
		ArrayList<Double> minMaxCur = new ArrayList<>();
		minMaxCur.add(0, MIN_PROB_CATCH);
		minMaxCur.add(1, MAX_PROB_CATCH);
		minMaxCur.add(2, getParameterValuesMap().get("probCatch"));
		toReturn.put("probCatch", minMaxCur);
		return toReturn;
	}

	@Override
	public String[] getMainStateNames() {
		return new String[] { "tree", "burning" };
	}

	public String getDefaultStringState() {
		return DEFAULT_STRING_STATE;

	}

}

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
	private static final int DEFAULT_STATE = TREE;
	private static final Color EMPTY_COLOR = Color.YELLOW;
	private static final Color TREE_COLOR = Color.FORESTGREEN;
	private static final Color BURNING_COLOR = Color.RED;
	private static final Color DEFAULT_COLOR = TREE_COLOR;
	private static final double MIN_PROB_CATCH = 0;
	private static final double MAX_PROB_CATCH = 1.0;
//	private double probCatch;

	public Fire(Map<String, Double> parameters) {
		super(parameters, 2);
		getStateToIntMap().put("empty", EMPTY);
		getStateToIntMap().put("tree", TREE);
		getStateToIntMap().put("burning", BURNING);
		getStateToColorMap().put("empty", EMPTY_COLOR);
		getStateToColorMap().put("tree", TREE_COLOR);
		getStateToColorMap().put("burning", BURNING_COLOR);
//		getParameterValuesMap().put("minProbCatch", MIN_PROB_CATCH);
//		getParameterValuesMap().put("maxProbCatch", MAX_PROB_CATCH);
		
		try {
//			probCatch = parameters.get("probCatch");
			getParameterValuesMap().put("probCatch", parameters.get("probCatch"));
		} catch (NullPointerException e) {
			getParameterValuesMap().put("probCatch", (MIN_PROB_CATCH + MAX_PROB_CATCH) / 2);
		}
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		double random = new Random().nextDouble();

		if (cellToUpdate.getCurrentState() == TREE
				&& countNeighbors(2, neighbors) > 0 && random < getParameterValuesMap().get("probCatch")) {
			changeFutureState(cellToUpdate, BURNING, BURNING_COLOR);
		} else if (cellToUpdate.getCurrentState() == BURNING
				|| cellToUpdate.getCurrentState() == EMPTY) {
			changeFutureState(cellToUpdate, EMPTY, EMPTY_COLOR);
		} else {
			changeFutureState(cellToUpdate, TREE, TREE_COLOR);
		}
		return cellToUpdate;
	}

	@Override
	public Color getDefaultColor() {
		return DEFAULT_COLOR;
	}

	@Override
	public int getDefaultState() {
		return DEFAULT_STATE;
	}

	@Override
	public Map<String, ArrayList<Double>> getParamNameMinMaxCur() {
		Map<String, ArrayList<Double>> toReturn = new HashMap<>();
		ArrayList<Double> minMaxCur = new ArrayList<>();
		minMaxCur.add(0, MIN_PROB_CATCH);
		minMaxCur.add(1, MAX_PROB_CATCH);
		minMaxCur.add(2, getParameterValuesMap().get("probCatch"));
		toReturn.put("probCatch", minMaxCur);
		return toReturn;
	}

}

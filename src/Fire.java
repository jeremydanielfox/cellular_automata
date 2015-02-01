import java.util.Collection;
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
	private static final int empty = 0;
	private static final int tree = 1;
	private static final int burning = 2;
	private static final int defaultState = tree;
	private static final Color emptyColor = Color.YELLOW;
	private static final Color treeColor = Color.FORESTGREEN;
	private static final Color burningColor = Color.RED;
	private static final Color defaultColor = treeColor;
	private double probCatch;

	public Fire(Map<String, Double> parameters) {
		super(parameters, 2);
		// TODO Auto-generated constructor stub
		getStateToIntMap().put("empty", empty);
		getStateToIntMap().put("tree", tree);
		getStateToIntMap().put("burning", burning);
		getStateToColorMap().put("empty", emptyColor);
		getStateToColorMap().put("tree", treeColor);
		getStateToColorMap().put("burning", burningColor);

		probCatch = parameters.get("probCatch");
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		// TODO Auto-generated method stub
		double random = new Random().nextDouble();

		if (cellToUpdate.getCurrentState() == tree
				&& countNeighbors(2, neighbors) > 0 && random < probCatch) {
			changeFutureState(cellToUpdate, burning, burningColor);
		} else if (cellToUpdate.getCurrentState() == burning
				|| cellToUpdate.getCurrentState() == empty) {
			changeFutureState(cellToUpdate, empty, emptyColor);
		} else {
			changeFutureState(cellToUpdate, tree, treeColor);
		}
		return cellToUpdate;
	}

	@Override
	public Color getDefaultColor() {
		// TODO Auto-generated method stub
		return defaultColor;
	}

	@Override
	public int getDefaultState() {
		// TODO Auto-generated method stub
		return defaultState;
	}

}

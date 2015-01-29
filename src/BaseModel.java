import java.util.Collection;
import java.util.Map;

/**
 * This the abstract model class that every type of model will extend
 * 
 * @author Megan Gutter
 *
 */

public abstract class BaseModel {
	private int numPointsForNeighbor;

	public BaseModel(Map<String, Double> parameters, int points) {
		numPointsForNeighbor = points;
	}

	// Takes in a cell and updates it's future state based on it's neighbors and
	// the rules of the model
	public abstract Cell updateFutureState(Cell cellToUpdate,
			Collection<Cell> neighbors);

	public int getSharePointsForNeighbor() {
		return numPointsForNeighbor;
	}

	public int countNeighbors(Cell cellToUpdate, int state,
			Collection<Cell> neighbors) {
		int neighborsWithState = 0;
		for (Cell c : neighbors) {
			if (c.getCurrentState() == state) {
				neighborsWithState += 1;
			}
		}
		return neighborsWithState;
	}
}

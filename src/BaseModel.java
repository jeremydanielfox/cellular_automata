import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

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

	public BaseModel(Map<String, Double> parameters, int points) {
		numPointsForNeighbor = points;
		stateToInt = new HashMap<String, Integer>();
		stateToColor = new HashMap<String, Color>();
	}

	public Map<String, Integer> getStateToIntMap() {
		return stateToInt;
	}
	
	public Map<String, Color> getStateToColorMap() {
		return stateToColor;
	}
	
	public abstract Cell updateFutureState(Cell cellToUpdate,
			Collection<Cell> neighbors);
	
	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate, BaseGraph graph) {
		for (Cell c: cellsToUpdate) {
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

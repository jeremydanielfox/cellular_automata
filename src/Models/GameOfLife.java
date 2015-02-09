package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import CellsAndComponents.Cell;
import javafx.scene.paint.Color;

/**
 * This class implements the GameOfLife model and extends BaseModel.
 * 
 * @author Megan Gutter
 *
 */
public class GameOfLife extends BaseModel {
	private static final int DEAD = 0;
	private static final int ALIVE = 1;
	private static final int DEFAULT_INT_STATE = DEAD;
	private static final String DEFAULT_STRING_STATE = "dead";
	private static final int NUM_LIVE_NEIGHBORS_TO_LIVE = 2;
	private static final int NUM_LIVE_NEIGHBORS_TO_REVIVE = 3;
	//private static final int NUM_POINTS_FOR_NEIGHBOR = 1;
	private Color DEAD_COLOR;
	private Color ALIVE_COLOR;
	private Color DEFAULT_COLOR;
	private Map<String, Color> colorMap;
	

	public GameOfLife(Map<String, Double> parameters, Map<String, Color> stateToColorMap) {
		super(parameters);
		colorMap = stateToColorMap;
		DEAD_COLOR = selectNonNullColor(stateToColorMap.get("dead"), Color.BLUE);
		ALIVE_COLOR = selectNonNullColor(stateToColorMap.get("alive"), Color.FUCHSIA);
		DEFAULT_COLOR = DEAD_COLOR;
		List<String> myStates = new ArrayList<String>(Arrays.asList("dead", "alive"));
		List<Color> myColors = new ArrayList<>(Arrays.asList(DEAD_COLOR, ALIVE_COLOR));
		List<Integer> myInts = new ArrayList<>(Arrays.asList(DEAD, ALIVE));
		initializeMaps(myStates, myInts, myColors);
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		int aliveNeighbors = countNeighbors(ALIVE, neighbors);
		if ((cellToUpdate.getCurrentState() == DEAD && aliveNeighbors == NUM_LIVE_NEIGHBORS_TO_REVIVE)
				|| (cellToUpdate.getCurrentState() == ALIVE && (aliveNeighbors == NUM_LIVE_NEIGHBORS_TO_LIVE || aliveNeighbors == NUM_LIVE_NEIGHBORS_TO_REVIVE))) {
			changeFutureState(cellToUpdate, ALIVE, ALIVE_COLOR);
		} else {
			changeFutureState(cellToUpdate, DEAD, DEAD_COLOR);
		}
		return cellToUpdate;
	}
	
	public void setColor(Color toSet, Color defaultColor, String state) {
		try {
			toSet = (Color) colorMap.get(state);
		} catch(NullPointerException e) {
			toSet = defaultColor;
		}
	}
	
	
	public Color getDefaultColor() {
		return DEFAULT_COLOR;
	}
	
	public int getDefaultIntState() {
		return DEFAULT_INT_STATE;
	}

	@Override
	public String getDefaultStringState() {
		return DEFAULT_STRING_STATE;
	}

}

package Models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
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
	private static final int DEFAULT_STATE = DEAD;
	private static final int NUM_LIVE_NEIGHBORS_TO_LIVE = 2;
	private static final int NUM_LIVE_NEIGHBORS_TO_REVIVE = 3;
	private static final int NUM_POINTS_FOR_NEIGHBOR = 1;
	private static final Color DEAD_COLOR = Color.BLACK;
	private static final Color ALIVE_COLOR = Color.FUCHSIA;
	private static final Color DEFAULT_COLOR = DEAD_COLOR;
	

	public GameOfLife(Map<String, Double> parameters) {
		super(parameters, NUM_POINTS_FOR_NEIGHBOR);
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
	
	
	public Color getDefaultColor() {
		return DEFAULT_COLOR;
	}
	
	public int getDefaultState() {
		return DEFAULT_STATE;
	}

}

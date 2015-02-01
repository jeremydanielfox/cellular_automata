import java.util.Collection;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 * This class implements the GameOfLife model and extends BaseModel.
 * 
 * @author Megan Gutter
 *
 */
public class GameOfLife extends BaseModel {
	private static final int dead = 0;
	private static final int alive = 1;
	private static final int defaultState = dead;
	private static final int numLiveNeighborsToLive = 2;
	private static final int numLiveNeighborsToRevive = 3;
	private static final int NUM_POINTS_FOR_NEIGHBOR = 1;
	private static final Color deadColor = Color.BLACK;
	private static final Color aliveColor = Color.FUCHSIA;
	private static final Color defaultColor = deadColor;
	

	public GameOfLife(Map<String, Double> parameters) {
		super(parameters, NUM_POINTS_FOR_NEIGHBOR);
		getStateToIntMap().put("dead", dead);
		getStateToIntMap().put("alive", alive);
		getStateToColorMap().put("dead", deadColor);
		getStateToColorMap().put("alive", aliveColor);
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		// TODO Auto-generated method stub
		int aliveNeighbors = countNeighbors(alive, neighbors);
		if ((cellToUpdate.getCurrentState() == dead && aliveNeighbors == numLiveNeighborsToRevive)
				|| (cellToUpdate.getCurrentState() == alive && (aliveNeighbors == numLiveNeighborsToLive || aliveNeighbors == numLiveNeighborsToRevive))) {
			changeStateTo(cellToUpdate, alive, aliveColor);
		} else {
			changeStateTo(cellToUpdate, dead, deadColor);
		}
		return cellToUpdate;
	}
	
	
	public Color getDefaultColor() {
		return defaultColor;
	}
	
	public int getDefaultState() {
		return defaultState;
	}
}

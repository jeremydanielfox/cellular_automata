import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

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
	public static final int DEFAULT_STATE = EMPTY;
	public static final int NUM_POINTS_FOR_NEIGHBOR = 1;
	public static final Color EMPTY_COLOR = Color.WHITE;
	public static final Color GROUP_ONE_COLOR = Color.RED;
	public static final Color GROUP_TWO_COLOR = Color.GREEN;
	public static final Color DEFAULT_COLOR = EMPTY_COLOR;
	public static final float RATIO_NEIGHBORS = (float) .5;

	public Segregation(Map<String, Double> parameters) {
		// possibly we should create a setNumPointsForNeighbor method in the
		// superclass so that we can
		// set up that information using the constructor
		super(parameters, NUM_POINTS_FOR_NEIGHBOR);
		getStateToIntMap().put("empty", EMPTY);
		getStateToIntMap().put("group_one", GROUP_ONE);
		getStateToIntMap().put("group_two", GROUP_TWO);
		getStateToColorMap().put("empty", EMPTY_COLOR);
		getStateToColorMap().put("group_one", GROUP_ONE_COLOR);
		getStateToColorMap().put("group_two", GROUP_TWO_COLOR);
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateAllCells(BaseGraph myGraph) {
		List<Cell> myCells = getRandomCollection(myGraph);
		Cell myUnhappyCell = null;
		Cell myEmptyCell = null;
		for (Cell current : myCells) {
			if (isReadyToSwitch(myUnhappyCell, myEmptyCell, myGraph))
				break;
			if (!isHappy(current, myGraph.getNeighbors(current)))
				myUnhappyCell = current;
			if (current.getCurrentState() == EMPTY)
				myEmptyCell = current;
		}
		if (isReadyToSwitch(myUnhappyCell, myEmptyCell, myGraph)) {
			myUnhappyCell.setFutureState(myEmptyCell.getCurrentState());
			myUnhappyCell.getShape().setFill(EMPTY_COLOR);
			myEmptyCell.setFutureState(myUnhappyCell.getCurrentState());
			if (myUnhappyCell.getCurrentState()==GROUP_ONE)
				myEmptyCell.getShape().setFill(GROUP_TWO_COLOR);
			else
				myEmptyCell.getShape().setFill(GROUP_ONE_COLOR);
		}

	}

	private boolean isReadyToSwitch(Cell first, Cell second, BaseGraph myGraph) {
		return !isHappy(first, myGraph.getNeighbors(first))
				&& second.getCurrentState() == EMPTY
				|| !isHappy(second, myGraph.getNeighbors(second))
				&& first.getCurrentState() == EMPTY;
	}

	@Override
	public Color getDefaultColor() {
		return DEFAULT_COLOR;
	}

	@Override
	public int getDefaultState() {
		return DEFAULT_STATE;
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
		return ratio <= RATIO_NEIGHBORS;
	}

	private List<Cell> getRandomCollection(BaseGraph myGraph) {
		List<Cell> myCells = (List<Cell>) myGraph.getAllCells();
		Collections.shuffle(myCells);
		return myCells;
	}

}

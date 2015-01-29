import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * This class implements the GameOfLife model and extends BaseModel.
 * 
 * @author Megan Gutter
 *
 */
public class GameOfLife extends BaseModel {
	private static final int dead = 0;
	private static final int alive = 1;
	private Map<String, Integer> stateToInt;
	private static final int numLiveNeighborsToLive = 2;
	private static final int numLiveNeighborsToRevive = 3;

	public GameOfLife(Map<String, Double> parameters) {
		super(parameters, 1);
		stateToInt = new HashMap<String, Integer>();
		stateToInt.put("dead", dead);
		stateToInt.put("alive", alive);
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		// TODO Auto-generated method stub
		int aliveNeighbors = countNeighbors(cellToUpdate, alive, neighbors);
		if ((cellToUpdate.getCurrentState() == dead && aliveNeighbors == numLiveNeighborsToRevive)
				|| (cellToUpdate.getCurrentState() == alive && (aliveNeighbors == numLiveNeighborsToLive || aliveNeighbors == numLiveNeighborsToRevive))) {
			cellToUpdate.setFutureState(alive);
			cellToUpdate.getShape().setFill(Color.BLUE);
		} else {
			cellToUpdate.setFutureState(dead);
			cellToUpdate.getShape().setFill(Color.HONEYDEW);
		}
		return cellToUpdate;
	}
	
	public int getIntForState(String state) {
		return stateToInt.get(state);
	}

	/*
	 * public static void main (String[] args) { Collection<Cell> neighbors =
	 * new ArrayList<Cell>(); Collection<Point2D> vertices = new
	 * ArrayList<Point2D>(); Shape circle = new Circle(); Cell c1 = new Cell(1,
	 * 0, 0, circle, neighbors, vertices); Cell c2 = new Cell(1, 0, 0, circle,
	 * neighbors, vertices); Cell c3 = new Cell(1, 0, 0, circle, neighbors,
	 * vertices); Cell c4 = new Cell(1, 0, 0, circle, neighbors, vertices); Cell
	 * c5 = new Cell(1, 1, 0, circle, neighbors, vertices); Cell c6 = new
	 * Cell(1, 1, 0, circle, neighbors, vertices); Cell c7 = new Cell(1, 1, 0,
	 * circle, neighbors, vertices); Cell c8 = new Cell(1, 1, 0, circle,
	 * neighbors, vertices); neighbors.add(c1); neighbors.add(c2);
	 * neighbors.add(c3); neighbors.add(c4); neighbors.add(c5);
	 * neighbors.add(c6); neighbors.add(c7); neighbors.add(c8); Cell c9 = new
	 * Cell(1, 1, 0, circle, neighbors, vertices); GameOfLife test = new
	 * GameOfLife();
	 * System.out.println(test.updateFutureState(c9).getFutureState()); }
	 */
}

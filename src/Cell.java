import java.util.Collection;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * This is the class in which the Cell object is defined.
 * 
 * @author Jeremy, Team 12
 */
public class Cell {
	private int myID;
	private int myCurrentState;
	private int myFutureState;
	private Shape myShape;
	private Collection<Cell> myNeighbors;
	private Collection<Point2D> myVertices;

	public Cell(int id, int currentState, int futureState, Shape shape,
			Collection<Cell> neighbors, Collection<Point2D> vertices) {
		myID = id;
		myCurrentState = currentState;
		myFutureState = futureState;
		myShape = shape;
		myNeighbors = neighbors;
		myVertices = vertices;
	}

	public int getID() {
		return myID;
	}

	public void setID(int id) {
		myID = id;
	}

	public int getCurrentState() {
		return myCurrentState;
	}

	public void setCurrentState(int currentState) {
		myCurrentState = currentState;
	}

	public int getFutureState() {
		return myFutureState;
	}

	public void setFutureState(int futureState) {
		myFutureState = futureState;
	}

	public Shape getShape() {
		return myShape;
	}

	public void setShape(Shape shape) {
		myShape = shape;
	}

	public void addNeighbor(Cell neighbor) {
		myNeighbors.add(neighbor);
	}

	public void removeNeighbor(Cell neighbor) {
		if (myNeighbors.contains(neighbor))
			myNeighbors.remove(neighbor);
	}

	public Collection<Cell> getNeighbors() {
		return myNeighbors;
	}

	public Collection<Point2D> getVertices() {
		return myVertices;
	}

}

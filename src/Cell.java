import java.util.Set;

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
	private Set<Point2D> myVerticies;
	private Shape myShape;

	public Cell(int id, Shape shape,Set<Point2D> verticies) {
		myID = id;
		myShape = shape;
		myVerticies = verticies;
	}

	public Cell(int id, int currentState, int futureState, Shape shape) {
		myID = id;
		myCurrentState = currentState;
		myFutureState = futureState;
		myShape = shape;
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
	
	public Set<Point2D> getVerticies() {
		return myVerticies;
	}
}

import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
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

	public Cell(int id, Shape shape, Set<Point2D> verticies, int defaultState, Color defaultColor) {
		setID(id);
		setShape(shape);
		myVerticies = verticies;
		setCurrentState(defaultState);
		setFutureState(1);
		myShape.setFill(defaultColor);
	}

	public Cell(int id, int currentState, int futureState, Shape shape) {
		setID(id);
		setCurrentState(currentState);
		setFutureState(futureState)	;
		setShape(shape);
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

	private void setShape(Shape shape) {
		myShape = shape;
	}

	public Set<Point2D> getVerticies() {
		return myVerticies;
	}
	
	public void setColor (Color current) {
		myShape.setFill(current);
	}
}

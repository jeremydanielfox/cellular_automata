package CellsAndComponents;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
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
	private Polygon myShape;

	public Cell(int id, Polygon shape, List<Point2D> verticies,
			int defaultState, Color defaultColor) {
		setID(id);
		setShape(shape);
		setCurrentState(defaultState);
		setFutureState(1);
		myShape.setFill(defaultColor);
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

	private void setShape(Polygon shape) {
		myShape = shape;
	}

	public void setColor(Color current) {
		myShape.setFill(current);
	}
}

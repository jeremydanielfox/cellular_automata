package Factories;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import Graphs.BaseGraph;
import Graphs.EightNeighorBasicGraph;
import Graphs.BasicGraph;

/**
 * This is the factory to make new graphs
 * 
 * @author Jeremy, Team 12
 *
 */
public class GraphFactory {
	private String myType;

	public GraphFactory() {

	}

	public GraphFactory(String type) {
		myType = type;
	}

	public BaseGraph createSpecifiedGraph(Polygon[][] myShapes,
			int numCellsWidth, int numCellsHeight, int defaultState,
			Color defaultColor, String model) {

		switch (model) {
		case "FourNeighborSquareGraph":
			return new BasicGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);
		case "FourNeighborTriangleGraph":
			return new BasicGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);
		case "EightNeighborTriangleGraph":
			return new EightNeighorBasicGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);
		case "EightNeighor":
			return new EightNeighorBasicGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);

		case "GameOfLife":
			return new EightNeighorBasicGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model);
		default:
			return new BasicGraph(myShapes, numCellsWidth, numCellsHeight,
					defaultState, defaultColor, model);
		}

	}
}

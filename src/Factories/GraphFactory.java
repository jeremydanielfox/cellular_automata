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

	public BaseGraph createSpecifiedGraph(Polygon[][] myShapes,
			int numCellsWidth, int numCellsHeight, int defaultState,
			Color defaultColor, String model, String graphType, String edgeType) {

		switch (model) {
		case "FourNeighborSquareGraph":
			return new BasicGraph(myShapes, numCellsWidth, numCellsHeight,
					defaultState, defaultColor, model, graphType, edgeType);
		case "FourNeighborTriangleGraph":
			return new BasicGraph(myShapes, numCellsWidth, numCellsHeight,
					defaultState, defaultColor, model, graphType, edgeType);
		case "EightNeighborTriangleGraph":
			return new EightNeighorBasicGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model,
					graphType, edgeType);
		case "EightNeighor":
			return new EightNeighorBasicGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model,
					graphType, edgeType);

		case "GameOfLife":
			return new EightNeighorBasicGraph(myShapes, numCellsWidth,
					numCellsHeight, defaultState, defaultColor, model,
					graphType, edgeType);
		default:
			return new BasicGraph(myShapes, numCellsWidth, numCellsHeight,
					defaultState, defaultColor, model, graphType, edgeType);
		}

	}
}

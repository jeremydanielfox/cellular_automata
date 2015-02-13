package Factories;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import Graphs.BaseGraph;
import Graphs.BasicGraph;

/**
 * This is the factory to make new graphs
 * 
 * @author Jeremy, Team 12
 *
 */
public class GraphFactory {

	/**
	 * Creates and returns the correct type of graph based on the string given
	 * 
	 * @param myShapes
	 * @param numCellsWidth
	 * @param numCellsHeight
	 * @param defaultState
	 * @param defaultColor
	 * @param model
	 * @param graphType
	 * @param edgeType
	 * @return
	 */
	public BaseGraph createSpecifiedGraph(Polygon[][] myShapes,
			int numCellsWidth, int numCellsHeight, int defaultState,
			Color defaultColor, String model, String graphType, String edgeType) {

		switch (model) {
		default:
			return new BasicGraph(myShapes, numCellsWidth, numCellsHeight,
					defaultState, defaultColor, model, graphType, edgeType);
		}

	}
}

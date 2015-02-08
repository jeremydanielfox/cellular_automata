import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import CellsAndComponents.Cell;
import Factories.GraphFactory;
import Factories.ModelFactory;
import Graphs.BaseGraph;
import Graphs.ConfigCellInfo;
import Models.BaseModel;

/**
 * This class changes the states of cells, connects model and graph.
 * 
 * @author Megan Gutter
 *
 */

public class SimEngine {
	private String myModelName;
	private Map<String, Double> myParameters;
	private List<ConfigCellInfo> myCellsToConfig;

	private BaseGraph myGraph;
	private BaseModel myModel;

	public SimEngine(Polygon[][] myPolygons, String random, String model,
			String graphType, String edgeType, Map<String, Double> parameters,
			List<ConfigCellInfo> cellsToConfig, int cellRegionWidth,
			int cellRegionHeight, Map<String, Color> stateToColorMap) {
		myModelName = model;
		myParameters = parameters;
		ModelFactory myModFactory = new ModelFactory();
		myModel = myModFactory.createSpecifiedModel(myModelName, myParameters, stateToColorMap);
		if (random.equals("YES")) {
			//eliminate this and give it the hash map from the parser
			Map<String, Double> paramProp = new HashMap<>();
			RandomConfiguration randConfigGenerator = new RandomConfiguration(
					myModel, myParameters.get("rows").intValue(), myParameters
							.get("columns").intValue(), paramProp);
			myCellsToConfig = randConfigGenerator.getRandConfigCells();
		} else {
			myCellsToConfig = cellsToConfig;
		}
		GraphFactory myGraphFactory = new GraphFactory();

		myGraph = myGraphFactory.createSpecifiedGraph(myPolygons, myParameters
				.get("columns").intValue(),
				myParameters.get("rows").intValue(), myModel.getDefaultIntState(),
				myModel.getDefaultColor(), myModelName, graphType, edgeType);
		// myGraph = new SquareGraph(myParameters.get("columns").intValue(),
		// myParameters.get("rows").intValue(), cellRegionWidth,
		// cellRegionHeight, cellRegionXOffset, cellRegionYOffset,
		// myModel.getSharePointsForNeighbor(), myModel.getDefaultState(),
		// myModel.getDefaultColor(), myModelName);

		// myGraph.initializeCells(myModel.getDefaultState(),
		// myModel.getDefaultColor());
		setUpInitCells();
	}

	public Collection<Cell> updateCells() {
		determineFutureStates();
		setFutureToCurrentStates();
		return (Collection<Cell>) myGraph.getAllCells();
	}

	private void determineFutureStates() {
		myModel.updateFutureStates(myGraph.getAllCells(), myGraph);
	}

	private void setFutureToCurrentStates() {
		for (Cell c : myGraph.getAllCells()) {
			c.setCurrentState(c.getFutureState());
			c.setFutureState(myModel.getDefaultIntState());
		}
	}

	public Collection<Cell> getListOfCells() {
		return (Collection<Cell>) myGraph.getAllCells();
	}

	private void setUpInitCells() {
		myModel.setUpCellContents(myGraph, myCellsToConfig);
	}

	public void changeParam(String paramName, Double paramValue) {
		myModel.changeParam(paramName, paramValue);
	}

	public Map<String, List<Double>> getParamMap() {
		return myModel.getParamNameMinMaxCur();
	}
}

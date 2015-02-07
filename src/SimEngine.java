import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

	public SimEngine(Polygon[][] myPolygons, String model,
			Map<String, Double> parameters, List<ConfigCellInfo> cellsToConfig,
			int cellRegionWidth, int cellRegionHeight) {
		myModelName = model;
		myParameters = parameters;
		myCellsToConfig = cellsToConfig;
		ModelFactory myModFactory = new ModelFactory();
		myModel = myModFactory.createSpecifiedModel(model, parameters);
		GraphFactory myGraphFactory = new GraphFactory();
		myGraph = myGraphFactory.createSpecifiedGraph(myPolygons, myParameters
				.get("columns").intValue(),
				myParameters.get("rows").intValue(),  myModel.getDefaultState(), myModel
						.getDefaultColor(), myModelName);
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
			c.setFutureState(myModel.getDefaultState());
		}
	}

	public Collection<Cell> getListOfCells() {
		return (Collection<Cell>) myGraph.getAllCells();
	}

	private void setUpInitCells() {
		for (ConfigCellInfo c : myCellsToConfig) {
			c.setIntState(myModel.getIntForState(c.getStringState()));
			myGraph.updateStateOfCell(c,
					myModel.getColorForStringState(c.getStringState()));
		}
	}

	public void changeParam(String paramName, Double paramValue) {
		myModel.changeParam(paramName, paramValue);
	}

	public Map<String, ArrayList<Double>> getParamMap() {
		return myModel.getParamNameMinMaxCur();
	}
}

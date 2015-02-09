import java.util.Collection;

import visuals.ChartMaster;
import CellsAndComponents.Cell;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class acts as the "view" for the simulation. It displays the stage that
 * the simulation runs, the control panel, and displays the cells of the
 * simulation and the graph of the current simulation populations.
 * 
 * @author sierrasmith95
 *
 */
public class SimWindow {
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	private Group myCellRegion;
	private Group myChartRegion;
	private VBox myControlPanels;
	private HBox myParamControls;

	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 1100;

	public SimWindow(Stage stage, HBox controls, String title) {
		myStage = stage;
		myStage.setTitle(title);
		myRoot = new Group();
		myCellRegion = new Group();
		myChartRegion = new Group();
		myControlPanels = new VBox();
		myControlPanels.getChildren().add(controls);
		myRoot.getChildren().add(myControlPanels);
		myRoot.getChildren().add(myCellRegion);
		myRoot.getChildren().add(myChartRegion);
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		myStage.setScene(myScene);
		myStage.show();
	}

	/**
	 * Takes in a list of cells and adds their shapes to the simulation window
	 * 
	 * @param collection
	 *            of cell objects
	 */
	public void paintCells(Collection<Cell> collection) {
		clearCells();
		for (Cell c : collection) {
			myCellRegion.getChildren().add(c.getShape());
		}
	}

	public void clearParameterControlPanel() {
		if (myParamControls != null) {
			myControlPanels.getChildren().remove(myParamControls);
		}
	}

	/**
	 * Takes in a HBox, assigns it to the myParamControls instance variable, and
	 * adds it to the VBox at the top of the simulation window.
	 * 
	 * @param toAdd
	 */
	public void addControlPanel(HBox toAdd) {
		myParamControls = toAdd;
		myControlPanels.getChildren().add(myParamControls);
	}

	public void setStageTitle(String newTitle) {
		myStage.setTitle(newTitle);
	}

	public void clearCells() {
		myCellRegion.getChildren().clear();
	}

	/**
	 * Adds the chart of the ChartMaster object to the display window
	 * 
	 * @param myChart
	 *            , a ChartMaster object
	 */
	public void addChart(ChartMaster myChart) {
		myChartRegion.getChildren().clear();
		myChartRegion.getChildren().add(myChart.getChart());
	}
}

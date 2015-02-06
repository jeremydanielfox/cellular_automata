import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class acts as the "view" for the simulation. It displays the stage that
 * the simulation runs, the control panel, and displays the cells of the
 * simulation.
 * 
 * @author sierrasmith95
 *
 */
public class SimWindow {
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	private Group myCellRegion;
	private VBox myControlPanels;

	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 600;
	public static final int SIM_WINDOW_X_OFFSET = 25;
	public static final int SIM_WINDOW_Y_OFFSET = 25;

	public SimWindow(Stage stage, HBox controls, String title) {
		myStage = stage;
		myStage.setTitle(title);
		myRoot = new Group();
		myCellRegion = new Group();
		myControlPanels = new VBox();
		myControlPanels.getChildren().add(controls);
		myRoot.getChildren().add(myControlPanels);
		myRoot.getChildren().add(myCellRegion);
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		myStage.setScene(myScene);
		myStage.show();
	}

	public void paintCells(Collection<Cell> collection) {
		wipeCells();
		for (Cell c : collection) {
			myCellRegion.getChildren().add(c.getShape());
		}
	}
	
	public void addControlPanel(HBox toAdd){
		myControlPanels.getChildren().add(toAdd);
	}

	public void setStageTitle(String newTitle) {
		myStage.setTitle(newTitle);
	}

	public void wipeCells() {
		myCellRegion.getChildren().clear();
	}
}

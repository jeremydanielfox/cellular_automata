import java.util.Collection;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SimWindow {
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	private Group myCellRegion;

	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 500;
	public static final int SIM_WINDOW_X_OFFSET = 25;
	public static final int SIM_WINDOW_Y_OFFSET = 25;

	public SimWindow(Stage stage, HBox controls) {
		myStage = stage;
		myStage.setTitle("Title from XML");
		myRoot = new Group();
		myCellRegion = new Group();
		myRoot.getChildren().add(controls);
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

	public void wipeCells() {
		myCellRegion.getChildren().clear();
	}
}

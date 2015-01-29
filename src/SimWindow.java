import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SimWindow {
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;

	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 500;

	public SimWindow(Stage stage, HBox controls) {
		myStage = stage;
		myStage.setTitle("Title from XML");
		myRoot = new Group();
		myRoot.getChildren().add(controls);
		myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);
		myStage.setScene(myScene);
		myStage.show();
	}

	public void paintCells(List<Cell> cellsToPaint) {
		for (Cell c : cellsToPaint) {
			myRoot.getChildren().add(c.getShape());
		}
	}

	public void wipeCells() {

	}
}
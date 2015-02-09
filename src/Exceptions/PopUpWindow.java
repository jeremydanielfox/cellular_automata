package Exceptions;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The purpose of this class is to display a string message in a small pop up
 * window.
 * 
 * @author Sierra
 *
 */
public class PopUpWindow {
	private Stage myStage;
	private Scene myScene;
	private Group myGroup;
	private Label myErrorLabel;

	public PopUpWindow() {
		myStage = new Stage();
		myStage.setHeight(100);
		myStage.setWidth(400);
		myGroup = new Group();
		myScene = new Scene(myGroup);
		myErrorLabel = new Label();
		myGroup.getChildren().add(myErrorLabel);
		myStage.setScene(myScene);
	}

	/**
	 * Sets the text of the error label in the pop up window to be that of the
	 * string passed in.
	 * 
	 * @param message, the message to be displayed in the window
	 */
	public void setDisplayMessage(String message) {
		myErrorLabel.setText(message);
	}

	/**
	 * Displays the pop-up window
	 */
	public void displayError() {
		myStage.show();
	}

}

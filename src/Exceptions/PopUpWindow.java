package Exceptions;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpWindow {
	private Stage myStage;
	private Scene myScene;
	private Group myGroup;
	private Label myErrorLabel;
	
	public PopUpWindow(){
		myStage = new Stage();
		myStage.setHeight(100);
		myStage.setWidth(400);
		myGroup = new Group();
		myScene = new Scene(myGroup);
		myErrorLabel = new Label();
		myGroup.getChildren().add(myErrorLabel);
		myStage.setScene(myScene);
	}
	
	public void setDisplayMessage(String message){
		myErrorLabel.setText(message);
	}
	
	public void displayError(){
		myStage.show();
	}
	
}

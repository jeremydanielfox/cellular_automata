package Exceptions;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CellSocietyException extends RuntimeException{

	/**
	 * 
	 */
	public static final String MISSING_INFO_MESSAGE = "The XML file is missing one of the following tags:\n \"Model\", "
			+ "\"GridRows\", \"GridColumns\".";
	public static final String INCORRECT_MODEL_MESSAGE = "The model given does not match a model implemented\n by "
			+ "this simulation.";
	
	private static final long serialVersionUID = 1L;
	private Stage myStage;
	private Scene myScene;
	private Group myGroup;
	private Label myErrorLabel;
	
	public CellSocietyException(String errorMessage){
		myStage = new Stage();
		myStage.setHeight(100);
		myStage.setWidth(400);
		myGroup = new Group();
		myScene = new Scene(myGroup);
		myErrorLabel = new Label(errorMessage);
		myGroup.getChildren().add(myErrorLabel);
		myStage.setScene(myScene);
	}
	
	public void displayError(){
		myStage.show();
	}
	
}

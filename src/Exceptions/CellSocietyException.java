package Exceptions;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CellSocietyException extends RuntimeException{

	public static final String MISSING_INFO_MESSAGE = "The XML file is missing one of the following tags:\n \"Model\", "
			+ "\"GridRows\", \"GridColumns\".";
	public static final String INCORRECT_MODEL_MESSAGE = "The model given does not match a model implemented\n by "
			+ "this simulation.";
	
	private static final long serialVersionUID = 1L;
	private String myErrorMessage;
	
	public CellSocietyException(String errorMessage){
		myErrorMessage = errorMessage;
	}
	
	public String getErrorMessage(){
		return myErrorMessage;
	}
	
}

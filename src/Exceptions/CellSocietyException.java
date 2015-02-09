package Exceptions;

/**
 * This class extends RunTime exception to create an exception that has a private
 * instance variable error message String.
 * 
 * @author Sierra
 *
 */
public class CellSocietyException extends RuntimeException {

	public static final String MISSING_INFO_MESSAGE = "The XML file is missing one of the following tags:\n \"Model\", "
			+ "\"GridRows\", \"GridColumns\".";
	public static final String INCORRECT_MODEL_MESSAGE = "The model given does not match a model implemented\n by "
			+ "this simulation.";

	private static final long serialVersionUID = 1L;
	private String myErrorMessage;

	public CellSocietyException(String errorMessage) {
		myErrorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return myErrorMessage;
	}

}

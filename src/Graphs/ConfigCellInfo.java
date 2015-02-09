package Graphs;

/**
 * This class contains row, column, string state, and int state, and it was
 * created because as we read in the XML file, we need to save the informatino
 * somewhere while we gather the rest of the information to create a Cell
 * 
 * @author Sierra
 *
 */
public class ConfigCellInfo {
	private static final int DEFAULT_STATE = 0;

	private int myRow;
	private int myCol;
	private String myStringState;
	private int myIntState;

	public ConfigCellInfo(int row, int col, String state) {
		myRow = row;
		myCol = col;
		myStringState = state;
		myIntState = DEFAULT_STATE;

	}

	public int getRow() {
		return myRow;
	}

	public int getCol() {
		return myCol;
	}

	public String getStringState() {
		return myStringState;
	}

	public void setIntState(int intState) {
		myIntState = intState;
	}

	public int getIntState() {
		return myIntState;
	}

	public void setStringState(String newState) {
		myStringState = newState;
	}
}

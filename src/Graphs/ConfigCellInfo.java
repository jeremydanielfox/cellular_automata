package Graphs;

public class ConfigCellInfo {
	private final int DEFAULT_STATE = 0;

	private int myRow;
	private int myCol;
	private String myState;
	private int myIntState;

	public ConfigCellInfo(int row, int col, String state) {
		myRow = row;
		myCol = col;
		myState = state;
		try{
			myIntState = Integer.parseInt(state);
		}catch(NumberFormatException e){
			myIntState = DEFAULT_STATE;
		}
		
	}

	public int getRow() {
		return myRow;
	}

	public int getCol() {
		return myCol;
	}

	public String getStringState() {
		return myState;
	}

	public void setIntState(int intState) {
		myIntState = intState;
	}

	public int getIntState() {
		return myIntState;
	}
}

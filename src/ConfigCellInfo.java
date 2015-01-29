
public class ConfigCellInfo {
	private int myRow;
	private int myCol;
	private String myState;
	
	public ConfigCellInfo(int row, int col, String state){
		myRow = row;
		myCol = col;
		myState = state;
	}
	
	public int getRow(){
		return myRow;
	}
	
	public int getCol(){
		return myCol;
	}
	
	public String getState(){
		return myState;
	}
	
}

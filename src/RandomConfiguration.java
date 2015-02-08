import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Graphs.ConfigCellInfo;
import Models.BaseModel;


public class RandomConfiguration {
	private BaseModel myModel;
	private List<ConfigCellInfo> myRandConfigCells;
	private Random myGenerator;
	
	public RandomConfiguration(BaseModel model){
		myGenerator = new Random();
		myModel = model;
		myRandConfigCells = new ArrayList<ConfigCellInfo>();
		initializeConfigCells(myModel.getNumState(), myModel.getParamNameMinMaxCur().keySet());
		setRandomParams(myModel.getParamNameMinMaxCur());
	}
	
	private void initializeConfigCells(int numStates, List<String> params){
		
	}
	
	private void setRandomParams(Map<String, List<Double>> params){
		
	}
	
	public List<ConfigCellInfo> getRandConfigCells(){
		return myRandConfigCells;
	}
	
	
}

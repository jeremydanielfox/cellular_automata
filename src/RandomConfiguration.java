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

	public RandomConfiguration(BaseModel model, int rows, int cols){
		myGenerator = new Random();
		myModel = model;
		myRandConfigCells = new ArrayList<ConfigCellInfo>();
		initializeConfigCells(rows, cols, myModel.getNumStates(), new ArrayList<String>(myModel.getStateToIntMap().keySet()));
		setRandomParams(myModel.getParamNameMinMaxCur());
	}

	private void initializeConfigCells(int rows, int cols, int numStates, List<String> states){
		for(int i = 1; i <= rows; i++){
			for(int j = 1; j <= cols; j++){
				ConfigCellInfo toAdd;
				int stateIndex = myGenerator.nextInt(numStates);
				try{
					toAdd = new ConfigCellInfo(i, j, states.get(stateIndex));
				}catch(IndexOutOfBoundsException e){
					toAdd = new ConfigCellInfo(i, j, Integer.toString(stateIndex));
				}
				myRandConfigCells.add(toAdd);
			}
		}
	}

	private void setRandomParams(Map<String, List<Double>> params){
		for(String s: params.keySet()){
			Double minimum = params.get(s).get(0);
			Double range = params.get(s).get(1) - minimum;
			Double randParam = (myGenerator.nextDouble() * range) + minimum;
			myModel.getParameterValuesMap().put(s, randParam);
		}
	}

	public List<ConfigCellInfo> getRandConfigCells(){
		return myRandConfigCells;
	}

}

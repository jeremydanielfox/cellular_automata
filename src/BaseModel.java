
/**
 * This the abstract model class that every type of model will extend
 * 
 * @author Megan, Team 12
 *
 */

public abstract class BaseModel {
	public int numPointsForNeighbor;
	
	// Takes in a cell and updates it's future state based on it's neighbors and the rules of the model
	public abstract Cell updateFutureState(Cell cellToUpdate);
	
	public int getSharePointsForNeighbor() {
		return numPointsForNeighbor;
	}
}

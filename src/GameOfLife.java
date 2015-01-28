import java.util.Map;


public class GameOfLife extends BaseModel {
	private static final int dead = 0;
	private static final int alive = 1;
	private static final int numLiveNeighborsToLive = 2;
	private static final int numLiveNeighborsToRevive = 3;
	
	public GameOfLife(Map<String, Integer> parameters) {
		numPointsForNeighbor = 8;
	}
	
	@Override
	public Cell updateFutureState(Cell cellToUpdate) {
		// TODO Auto-generated method stub
		int aliveNeighbors = countLiveNeighbors(cellToUpdate);
		if ((cellToUpdate.getCurrentState() == dead && aliveNeighbors == numLiveNeighborsToRevive) ||
				(cellToUpdate.getCurrentState() == alive && 
				(aliveNeighbors == numLiveNeighborsToLive || aliveNeighbors == numLiveNeighborsToRevive))) {
			cellToUpdate.setFutureState(alive);
		}
		else {
			cellToUpdate.setFutureState(dead);
		}
		return cellToUpdate;
	}
		
	private int countLiveNeighbors(Cell cellToUpdate) {
		int aliveNeighbors = 0;
		for (Cell c : cellToUpdate.getNeighbors()) {
			if (c.getCurrentState() == alive) {
				aliveNeighbors += 1;
			}
		}
		return aliveNeighbors;
	}
}

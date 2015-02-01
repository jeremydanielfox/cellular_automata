import java.util.Collection;
import java.util.Map;

import javafx.scene.paint.Color;


public class WaTorWorld extends BaseModel {
	private static final int water = 0;
	private static final int fish = 1;
	private static final int shark = 2;
	private static final int NUM_POINTS_FOR_NEIGHBOR = 2;
	private static final int defaultState = water;
	private static final Color waterColor = Color.AQUAMARINE;
	private static final Color fishColor = Color.YELLOW;
	private static final Color sharkColor = Color.LAVENDER;
	private static final Color defaultColor = waterColor;

	public WaTorWorld(Map<String, Double> parameters) {
		super(parameters, NUM_POINTS_FOR_NEIGHBOR);
		// TODO Auto-generated constructor stub
		getStateToIntMap().put("water", water);
		getStateToIntMap().put("fish", fish);
		getStateToIntMap().put("shark", shark);
		getStateToColorMap().put("water", waterColor);
		getStateToColorMap().put("fish", fishColor);
		getStateToColorMap().put("shark", sharkColor);
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getDefaultColor() {
		// TODO Auto-generated method stub
		return defaultColor;
	}

	@Override
	public int getDefaultState() {
		// TODO Auto-generated method stub
		return defaultState;
	}

}

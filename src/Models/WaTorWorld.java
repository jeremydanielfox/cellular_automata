package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import CellsAndComponents.AquaticCreature;
import CellsAndComponents.Cell;
import CellsAndComponents.CellWithInhabitant;
import CellsAndComponents.Inhabitant;
import CellsAndComponents.Shark;
import Factories.InhabitantFactory;
import Graphs.BaseGraph;
import Graphs.ConfigCellInfo;

/**
 * This class implements the WaTorWorld model and extends BaseModel.
 * 
 * @author Megan Gutter
 *
 */

public class WaTorWorld extends BaseModel {
	private static final int WATER = 0;
	private static final int FISH = 1;
	private static final int SHARK = 2;
	private static final int DEFAULT_INT_STATE = WATER;
	private static final String DEFAULT_STRING_STATE = "water";
	private Color waterColor = Color.AQUAMARINE;
	private Color fishColor = Color.YELLOW;
	private Color sharkColor = Color.PURPLE;
	private Color defaultColor;
	private static final double MIN_SHARK_ENERGY = 1;
	private static final double MAX_SHARK_ENERGY = 100;
	private static final double MIN_TIME_TILL_REPRODUCE = 1;
	private static final double MAX_TIME_TILL_REPRODUCE = 100;

	public WaTorWorld(Map<String, Double> parameters,
			Map<String, Color> stateToColorMap) {
		super(parameters);
		waterColor = selectNonNullColor(stateToColorMap.get("water"),
				Color.AQUAMARINE);
		fishColor = selectNonNullColor(stateToColorMap.get("fish"),
				Color.YELLOW);
		sharkColor = selectNonNullColor(stateToColorMap.get("shark"),
				Color.PURPLE);
		defaultColor = waterColor;
		List<String> myStates = new ArrayList<String>(Arrays.asList("water",
				"fish", "shark"));
		List<Color> myColors = new ArrayList<>(Arrays.asList(waterColor,
				fishColor, sharkColor));
		List<Integer> myInts = new ArrayList<>(
				Arrays.asList(WATER, FISH, SHARK));
		initializeMaps(myStates, myInts, myColors);
		try {
			getParameterValuesMap().put("sharkEnergy",
					(double) parameters.get("energyLevel"));
			getParameterValuesMap().put("timeTillReproduce",
					(double) parameters.get("timeTillReproduce"));
		} catch (NullPointerException e) {
			getParameterValuesMap().put("sharkEnergy", MIN_SHARK_ENERGY);
			getParameterValuesMap().put("timeTillReproduce",
					MAX_TIME_TILL_REPRODUCE);
		}

	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		return null;
	}

	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate,
			BaseGraph graph) {
		for (Cell c : cellsToUpdate) {
			if (c.getCurrentState() == SHARK) {
				Shark currentShark = ((Shark) ((CellWithInhabitant) c)
						.getInhabitant());
				if (currentShark.getEnergyLevel() < getParameterValuesMap()
						.get("sharkEnergy").intValue()
						&& countNeighbors(FISH, graph.getNeighbors(c)) > 0) {
					moveShark(graph, c, currentShark, FISH);
				} else if (currentShark.getEnergyLevel() >= getParameterValuesMap()
						.get("sharkEnergy").intValue()) {
					changeStateAndInhabitant(c, new Inhabitant(WATER), WATER,
							waterColor);
				} else {
					moveShark(graph, c, currentShark, WATER);
				}
			}
		}
		for (Cell c : cellsToUpdate) {
			if (c.getCurrentState() == FISH) {
				AquaticCreature currentFish = ((AquaticCreature) ((CellWithInhabitant) c)
						.getInhabitant());
				if (countNeighbors(0, graph.getNeighbors(c)) > 0
						&& currentFish.getReproductionCounter() >= getParameterValuesMap()
								.get("timeTillReproduce").intValue()) {
					currentFish.resetReproductionCounter();
					Cell cellToMoveTo = getCellToMoveTo(graph, c, WATER);
					if (cellToMoveTo != null) {
						changeStateAndInhabitant(cellToMoveTo, currentFish,
								FISH, fishColor);
						changeStateAndInhabitant(c, new AquaticCreature(FISH),
								FISH, fishColor);
					}
				} else {
					Cell cellToMoveTo = getCellToMoveTo(graph, c, WATER);
					if (cellToMoveTo != null) {
						currentFish.increaseReproductionCounter();
						changeStateAndInhabitant(cellToMoveTo, currentFish,
								FISH, fishColor);
						changeStateAndInhabitant(c, new Inhabitant(WATER),
								WATER, waterColor);
					} else {
						changeFutureState(c, FISH, fishColor);
					}
				}
			}
		}
		return (Collection<Cell>) cellsToUpdate;
	}

	/**
	 * This method finds a cell with the given state for a cell with a shark to
	 * move to, then manipulates the inhabitants of the cell to move the shark
	 * 
	 * @param graph
	 * @param c
	 * @param currentShark
	 * @param stateToCheckAgainst
	 */
	private void moveShark(BaseGraph graph, Cell c, Shark currentShark,
			int stateToCheckAgainst) {
		Cell cellToMoveTo = getCellToMoveTo(graph, c, stateToCheckAgainst);
		if (cellToMoveTo != null) {
			changeSharkCounters(currentShark, stateToCheckAgainst);
			cellToMoveTo.setCurrentState(DEFAULT_INT_STATE);
			changeStateAndInhabitant(cellToMoveTo, currentShark, SHARK,
					sharkColor);
			if (currentShark.getReproductionCounter() >= getParameterValuesMap()
					.get("timeTillReproduce").intValue()) {
				currentShark.resetReproductionCounter();
				changeStateAndInhabitant(c, new Shark(SHARK), SHARK, sharkColor);
			} else {
				changeStateAndInhabitant(c, new Inhabitant(WATER), WATER,
						waterColor);
			}
		} else {
			changeFutureState(c, SHARK, sharkColor);
		}
	}

	private void changeSharkCounters(Shark currentShark, int nextCellState) {
		if (nextCellState == FISH)
			currentShark.increaseEnergy();
		else
			currentShark.decreaseEnergy();
		currentShark.increaseReproductionCounter();
	}

	private void changeStateAndInhabitant(Cell c, Inhabitant i, int state,
			Color stateColor) {
		((CellWithInhabitant) c).setInhabitant(i);
		changeFutureState(c, state, stateColor);
	}

	/**
	 * Loops through the neighbors to find a random cell of the specified state
	 * to move to
	 * 
	 * @param graph
	 * @param c
	 * @param state
	 */
	private Cell getCellToMoveTo(BaseGraph graph, Cell c, int state) {
		Cell toMove = null;
		List<Cell> myCells = new ArrayList<Cell>(graph.getNeighbors(c));
		Collections.shuffle(myCells);
		for (Cell cell : myCells) {
			if (cell.getCurrentState() == state
					&& (c.getCurrentState() != FISH || (c.getCurrentState() == FISH && cell
							.getFutureState() == state))) {
				toMove = cell;
				break;
			}
		}
		return toMove;
	}

	@Override
	public Color getDefaultColor() {
		return defaultColor;
	}

	@Override
	public int getDefaultIntState() {
		return DEFAULT_INT_STATE;
	}

	@Override
	public Map<String, List<Double>> getParamNameMinMaxCur() {
		Map<String, List<Double>> toReturn = new HashMap<>();
		ArrayList<Double> minMaxCurReproduce = new ArrayList<>();
		minMaxCurReproduce.add(0, MIN_TIME_TILL_REPRODUCE);
		minMaxCurReproduce.add(1, MAX_TIME_TILL_REPRODUCE);
		minMaxCurReproduce.add(2,
				getParameterValuesMap().get("timeTillReproduce"));
		toReturn.put("timeTillReproduce", minMaxCurReproduce);
		ArrayList<Double> minMaxCurSharkEnergy = new ArrayList<>();
		minMaxCurSharkEnergy.add(0, MIN_SHARK_ENERGY);
		minMaxCurSharkEnergy.add(1, MAX_SHARK_ENERGY);
		minMaxCurSharkEnergy.add(2, getParameterValuesMap().get("sharkEnergy"));
		toReturn.put("sharkEnergy", minMaxCurSharkEnergy);
		return toReturn;
	}

	/**
	 * Upon initializing, the cells for the WaTorWorld model need additional
	 * setup during the configuration. This adds inhabitants to cells of certain
	 * states, generated by the InhabitantFactory
	 * 
	 * @param c
	 * @param myBabyCell
	 */
	@Override
	public void addAdditionalCellInfo(Cell c, ConfigCellInfo myBabyCell) {
		CellWithInhabitant curCell = (CellWithInhabitant) c;
		InhabitantFactory myInhabitantFactory = new InhabitantFactory();
		curCell.setInhabitant(myInhabitantFactory.createSpecifiedInhabitant(
				myBabyCell.getStringState(), myBabyCell.getIntState()));
	}

	@Override
	public String[] getMainStateNames() {
		return new String[] { "fish", "shark" };
	}

	public String getDefaultStringState() {
		return DEFAULT_STRING_STATE;

	}

}

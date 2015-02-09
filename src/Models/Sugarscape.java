package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import CellsAndComponents.AdvancedCell;
import CellsAndComponents.Agent;
import CellsAndComponents.Cell;
import CellsAndComponents.Inhabitant;
import CellsAndComponents.Patch;
import CellsAndComponents.Sugar;
import Graphs.BaseGraph;
import Graphs.ConfigCellInfo;

/**
 * This class implements the SugarScape model and extends BaseModel.
 * 
 * @author Megan Gutter and Sierra Smith
 *
 */

public class Sugarscape extends BaseModel {
	private static final int SUGAR_GROW_BACK_RATE = 1;
	private static final int SUGAR_GROW_BACK_INTERVAL = 1;
	private static final int WITH_AGENT = -1;
	private Color agentColor;
	private Color sugarColor;
	private static final double MIN_NUM_AGENTS = 0;
	private static final double MAX_NUM_AGENTS = 100;
	private static final int DEFAULT_MAX_SUGAR = 10;
	private static final String DEFAULT_STRING_MAX_SUGAR = "10";
	private Color defaultSugarColor;
	private int maxSugarLevel;
	private int sugarGrowCounter;

	public Sugarscape(Map<String, Double> parameters,
			Map<String, Color> stateToColorMap) {
		super(parameters);
		sugarColor = selectNonNullColor(stateToColorMap.get("sugar"),
				Color.ORANGE);
		agentColor = selectNonNullColor(stateToColorMap.get("agent"),
				Color.RED);
		defaultSugarColor = sugarColor;
		List<String> myStates = new ArrayList<String>(Arrays.asList("agent"));
		List<Color> myColors = new ArrayList<>(Arrays.asList(agentColor));
		List<Integer> myInts = new ArrayList<>(Arrays.asList(WITH_AGENT));
		initializeMaps(myStates, myInts, myColors);
		sugarGrowCounter = 0;
		maxSugarLevel = DEFAULT_MAX_SUGAR;
		try {
			getParameterValuesMap().put("numAgents",
					(double) parameters.get("numAgents"));
		} catch (NullPointerException e) {
			getParameterValuesMap().put("numAgents",
					(MIN_NUM_AGENTS + MAX_NUM_AGENTS) / 2);
		}
	}

	@Override
	public Cell updateFutureState(Cell cellToUpdate, Collection<Cell> neighbors) {
		return null;
	}

	public Collection<Cell> updateFutureStates(Iterable<Cell> cellsToUpdate,
			BaseGraph graph) {
		ArrayList<Cell> shuffledCells = createArrayListFromIterable(cellsToUpdate);
		Collections.shuffle(shuffledCells);
		for (Cell c : shuffledCells) {
			AdvancedCell curCell = (AdvancedCell) c;
			if (curCell.getNumInhabitants() > 0
					&& curCell.getFutureState() != WITH_AGENT) {
				HashMap<AdvancedCell, Integer> possibleCells = (HashMap<AdvancedCell, Integer>) getVacantPatchesInSight(
						curCell, graph);
				HashMap<AdvancedCell, Integer> possibleMaxSugar = (HashMap<AdvancedCell, Integer>) findMaxSugarCells(possibleCells);
				AdvancedCell toMoveTo = getClosest(possibleMaxSugar, curCell,
						graph);
				if (toMoveTo != null) {
					moveAgent(toMoveTo, curCell);
					((Agent) toMoveTo.getInhabitants().get(0))
							.addSugar(((Sugar) toMoveTo.getPatch())
									.getSugarAmount());
					((Sugar) toMoveTo.getPatch()).takeAllSugar();
					if (((Agent) toMoveTo.getInhabitants().get(0)).checkDead()) {
						toMoveTo.getInhabitants().remove(0);
					} else {
						changeFutureState(toMoveTo, WITH_AGENT,
								agentColor);
					}
				} else {
					changeFutureState(curCell, WITH_AGENT, agentColor);
				}
			}
		}
		sugarGrowCounter += 1;
		for (Cell c : shuffledCells) {
			AdvancedCell curCell = (AdvancedCell) c;
			if (sugarGrowCounter >= SUGAR_GROW_BACK_INTERVAL) {
				((Sugar) curCell.getPatch())
						.sugarGrowBack(SUGAR_GROW_BACK_RATE);
			}
			if (curCell.getNumInhabitants() == 0) {
				changeFutureState(
						curCell,
						((Sugar) curCell.getPatch()).getSugarAmount(),
						calculateColorForSugarLevel(((Sugar) curCell.getPatch())
								.getSugarAmount()));
			}
		}
		return shuffledCells;
	}

	private ArrayList<Cell> createArrayListFromIterable(
			Iterable<Cell> cellsToUpdate) {
		ArrayList<Cell> shuffledCells = new ArrayList<Cell>();
		for (Cell c : cellsToUpdate) {
			shuffledCells.add(c);
		}
		return shuffledCells;
	}

	@Override
	public void setUpCellContents(BaseGraph graph,
			Iterable<ConfigCellInfo> cellsToConfig) {
		for (Cell c : graph.getAllCells()) {
			AdvancedCell cell = (AdvancedCell) c;
			cell.setPatch(new Sugar(DEFAULT_MAX_SUGAR));
			cell.setCurrentState(DEFAULT_MAX_SUGAR);
			cell.setColor(calculateColorForSugarLevel(DEFAULT_MAX_SUGAR));
		}
		for (ConfigCellInfo c : cellsToConfig) {
			try {
				c.setIntState(Integer.parseInt(c.getStringState()));
			} catch (NumberFormatException e) {
				c.setIntState(DEFAULT_MAX_SUGAR);
			}
			updateStateOfCell(graph, c,
					calculateColorForSugarLevel(c.getIntState()));
			if (c.getIntState() > maxSugarLevel) {
				maxSugarLevel = c.getIntState();
			}
		}
		ArrayList<Cell> shuffledCells = createArrayListFromIterable(graph
				.getAllCells());
		Collections.shuffle(shuffledCells);
		for (int i = 0; i < getParameterValuesMap().get("numAgents"); i++) {
			AdvancedCell toAddto = (AdvancedCell) shuffledCells.get(i);
			toAddto.addInhabitant(new Agent(WITH_AGENT));
			toAddto.setCurrentState(getIntForState("agent"));
			toAddto.setColor(agentColor);
		}
	}

	@Override
	public void addAdditionalCellInfo(Cell c, ConfigCellInfo myBabyCell) {
		AdvancedCell curCell = (AdvancedCell) c;
		Patch newPatch = new Sugar(myBabyCell.getIntState());
		curCell.setPatch(newPatch);
	}

	private Color calculateColorForSugarLevel(int sugarLevel) {
		double opacity = 1.0 / maxSugarLevel * sugarLevel;
		Color orange = new Color(sugarColor.getRed(), sugarColor.getGreen(),
				sugarColor.getBlue(), opacity);
		return orange;
	}

	private Map<AdvancedCell, Integer> getVacantPatchesInSight(
			AdvancedCell curCell, BaseGraph graph) {
		int vision = ((Agent) curCell.getInhabitants().get(0)).getVision();
		Map<AdvancedCell, Integer> possibleCellMap = new HashMap<AdvancedCell, Integer>();
		for (int i = 1; i <= vision; i++) {
			if (checkIfVacant(curCell, graph, i, 0) != null) {
				possibleCellMap.put(checkIfVacant(curCell, graph, i, 0), i);
			}
			if (checkIfVacant(curCell, graph, -i, 0) != null) {
				possibleCellMap.put(checkIfVacant(curCell, graph, -i, 0), i);
			}
			if (checkIfVacant(curCell, graph, 0, i) != null) {
				possibleCellMap.put(checkIfVacant(curCell, graph, 0, i), i);
			}
			if (checkIfVacant(curCell, graph, 0, -i) != null) {
				possibleCellMap.put(checkIfVacant(curCell, graph, 0, -i), i);
			}
		}
		return possibleCellMap;
	}

	private AdvancedCell checkIfVacant(AdvancedCell curCell, BaseGraph graph,
			int x, int y) {
		Point2D change = new Point2D(x, y);
		AdvancedCell possible = (AdvancedCell) graph.getTranslatedCell(curCell,
				change);
		if (possible != null && possible.getNumInhabitants() == 0) {
			return possible;
		}
		return null;
	}

	private Map<AdvancedCell, Integer> findMaxSugarCells(
			HashMap<AdvancedCell, Integer> possibleCells) {
		int maxSugar = 0;
		for (AdvancedCell curCell : possibleCells.keySet()) {
			if (((Sugar) curCell.getPatch()).getSugarAmount() > maxSugar) {
				maxSugar = ((Sugar) curCell.getPatch()).getSugarAmount();
			}
		}
		Map<AdvancedCell, Integer> possibleMaxSugarMap = new HashMap<AdvancedCell, Integer>();
		for (AdvancedCell curCell : possibleCells.keySet()) {
			if (((Sugar) curCell.getPatch()).getSugarAmount() == maxSugar) {
				possibleMaxSugarMap.put(curCell, possibleCells.get(curCell));
			}
		}
		return possibleMaxSugarMap;
	}

	private AdvancedCell getClosest(
			HashMap<AdvancedCell, Integer> possibleCells, AdvancedCell curCell,
			BaseGraph graph) {
		int minDistance = Integer.MAX_VALUE;
		AdvancedCell toReturn = null;
		for (AdvancedCell c : possibleCells.keySet()) {
			int cellDistance = possibleCells.get(c);
			if (cellDistance < minDistance) {
				minDistance = cellDistance;
			}
		}
		ArrayList<AdvancedCell> minDistList = new ArrayList<AdvancedCell>();
		for (AdvancedCell c : possibleCells.keySet()) {
			int cellDistance = possibleCells.get(c);
			if (cellDistance == minDistance) {
				minDistList.add(c);
			}
		}
		Random generator = new Random();
		toReturn = minDistList.get(generator.nextInt(minDistList.size()));
		return toReturn;
	}

	private void moveAgent(AdvancedCell newCell, AdvancedCell oldCell) {
		Inhabitant toMove = oldCell.getInhabitants().get(0);
		newCell.addInhabitant(toMove);
		oldCell.getInhabitants().remove(0);
	}

	@Override
	public Color getDefaultColor() {
		return defaultSugarColor;
	}

	@Override
	public int getDefaultIntState() {
		return DEFAULT_MAX_SUGAR;
	}

	@Override
	// error check?
	public int getIntForState(String state) {
		if (state.equals("agent")) {
			return getStateToIntMap().get(state);
		}
		return Integer.parseInt(state);
	}

	@Override
	public int getNumStates() {
		return DEFAULT_MAX_SUGAR;
	}

	@Override
	public String[] getMainStateNames() {
		return new String[] { "agent" };
	}

	public String getDefaultStringState() {
		return DEFAULT_STRING_MAX_SUGAR;

	}

}

package Factories;

import java.util.Map;

import javafx.scene.paint.Color;
import Exceptions.CellSocietyException;
import Models.BaseModel;
import Models.Fire;
import Models.GameOfLife;
import Models.Segregation;
import Models.Sugarscape;
import Models.WaTorWorld;

/**
 * This class creates an appropriate model depending on the model string that is
 * fed into it
 * 
 * @author Megan, Sierra, Jeremy
 *
 */
public class ModelFactory {

	public BaseModel createSpecifiedModel(String model,
			Map<String, Double> parameters, Map<String, Color> stateToColorMap) {

		switch (model) {
		case "GameOfLife":
			return new GameOfLife(parameters, stateToColorMap);
		case "Fire":
			return new Fire(parameters, stateToColorMap);
		case "Segregation":
			return new Segregation(parameters, stateToColorMap);
		case "WaTorWorld":
			return new WaTorWorld(parameters, stateToColorMap);
		case "Sugarscape":
			return new Sugarscape(parameters, stateToColorMap);
		default:
			throw new CellSocietyException(
					CellSocietyException.INCORRECT_MODEL_MESSAGE);
		}

	}
}

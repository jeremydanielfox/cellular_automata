package Factories;

import java.util.Map;

import Exceptions.CellSocietyException;
import Models.BaseModel;
import Models.Fire;
import Models.GameOfLife;
import Models.Segregation;
import Models.Sugarscape;
import Models.WaTorWorld;

public class ModelFactory {

	public BaseModel createSpecifiedModel(String model,
			Map<String, Double> parameters) {

		switch (model) {
		case "GameOfLife":
			return new GameOfLife(parameters);
		case "Fire":
			return new Fire(parameters);
		case "Segregation":
			return new Segregation(parameters);
		case "WaTorWorld":
			return new WaTorWorld(parameters);
		case "Sugarscape":
			return new Sugarscape(parameters);
		default:
			throw new CellSocietyException(CellSocietyException.INCORRECT_MODEL_MESSAGE);
		}

	}
}

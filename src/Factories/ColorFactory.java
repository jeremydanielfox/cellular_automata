package Factories;

import java.lang.reflect.Field;

import javafx.scene.paint.Color;

/**
 * This class uses reflection to create appropriate colors from strings in the
 * XML files
 * 
 * @author Megan, Sierra
 *
 */
public class ColorFactory {
	public Color stringToColor(String value) {
		if (value == null) {
			return null;
		}
		try {
			Field f = Color.class.getField(value);
			return (Color) f.get(null);
		} catch (Exception ce) {
			return null;
		}
	}
}

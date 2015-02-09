package Factories;

import java.lang.reflect.Field;

import javafx.scene.paint.Color;

public class ColorFactory {
	public Color stringToColor(String value) {
		if (value == null) {
			return null;
		}
		try {
			Field f = Color.class.getField(value);
			System.out.println((Color) f.get(null));
			return (Color) f.get(null);
		} catch (Exception ce) {
			return null;
		}
	}
}

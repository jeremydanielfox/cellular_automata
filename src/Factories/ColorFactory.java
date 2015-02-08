package Factories;

import java.lang.reflect.Field;

import javafx.scene.paint.Color;

public class ColorFactory {
	public class Main {
		public Color stringToColor(final String value) {
			if (value == null) {
				return Color.BLACK;
			}
			try {
				final Field f = Color.class.getField(value);
				return (Color) f.get(null);
			} catch (Exception ce) {
				return Color.BLACK;
			}
		}
	}
}

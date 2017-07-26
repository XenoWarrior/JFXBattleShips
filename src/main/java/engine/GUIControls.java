package engine;

import javafx.scene.control.*;
import javafx.scene.text.*;

public class GUIControls {

	public static Label createLabel(String text) {
		return new Label(text);
	}

	public static Label createLabel(String text, Font font) {
		Label t = new Label(text);
		t.setFont(font);
		
		return t;
	}

	public static Button createButton(String text) {
		return new Button(text);
	}

	public static Button createButton(String text, int width, int height) {
		
		Button t = new Button(text);
		t.setMinHeight(height);
		t.setMinWidth(width);
		
		return t;
	}
	
}

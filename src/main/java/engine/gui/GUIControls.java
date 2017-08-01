package engine.gui;

import javafx.scene.control.*;
import javafx.scene.text.*;

/**
 * 
 * engine.gui.GUIControls
 * Handles creation of GUI controls to minimise duplicate code in the main code base.
 * @author Ashley Scott
 *
 */
public class GUIControls {

	/**
	 * Creates a standard label with default parameters
	 * @param text, the text to show in the label
	 * @return, a label which can be used in a scene
	 */
	public static Label createLabel(String text) {
		return new Label(text);
	}

	/**
	 * Creates a standard label with custom font parameter
	 * @param text, the text to show in the label
	 * @param font, the font to use for the text
	 * @return, a label which can be used in a scene
	 */
	public static Label createLabel(String text, Font font) {
		Label t = new Label(text);
		t.setFont(font);
		return t;
	}

	/**
	 * Creates a standard button with default parameters
	 * @param text, the text to show in button
	 * @return, a button which can be used in a scene
	 */
	public static Button createButton(String text) {
		return new Button(text);
	}

	/**
	 * Creates a standard button with default parameters
	 * @param text, the text to show in button
	 * @param width, the width size of the button
	 * @param height, the height size of the button
	 * @return, a button which can be used in a scene
	 */
	public static Button createButton(String text, int width, int height) {
		Button t = new Button(text);
		t.setMinHeight(height);
		t.setMinWidth(width);
		return t;
	}
	
}

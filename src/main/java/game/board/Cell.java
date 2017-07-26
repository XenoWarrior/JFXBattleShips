package game.board;

import game.ship.Ship;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

	private Ship cellShip = new Ship();
	
	public Cell() { 
		super(32, 32);
		setFill(Color.WHITE);
		setStroke(Color.BLACK);
	}
	
	
	void onMouseClick() {
		setFill(Color.ALICEBLUE);
	}
	
	void onMouseEnter() {
		setFill(Color.GREEN);
	}
	
	void onMouseExit() {
		setFill(Color.WHITE);
	}
	
}

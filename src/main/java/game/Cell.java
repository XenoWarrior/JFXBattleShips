package game;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

	private int cellX, cellY = 0;
	private boolean wasShot = false;
	private Ship cellShip;
	private Board belongsToBoard;
	
	public Cell(int x, int y, Board b) { 
		super(32, 32);
		
		this.cellX = x;
		this.cellY = y;
		
		this.belongsToBoard = b;

		setFill(Color.WHITE);
		setStroke(Color.BLACK);
	}
	
	public boolean onShootShipClick() {
		this.wasShot = true;

		if (cellShip != null) {
			cellShip.removeHealth();
			
			setFill(Color.RED);
			
			return true;
		}

		setFill(Color.BLACK);
		
		return false;
	}
	
	public void onAddShipClick(Ship s) {
		this.cellShip = s;
		setFill(Color.AQUA);
	}

	public int getCellX() {
		 return this.cellX;
	}
	
	public int getCellY() {
		 return this.cellY;
	}

	public boolean containsShip() {
		 if(this.cellShip != null) {
			 return true;
		 }
		 return false;
	}
	
	public void onEnter() {
		if(!this.wasShot) {
			if(this.cellShip == null) {
				this.setFill(Color.GREEN);
			}
		}
	}
	
	public void onLeave() {
		if(!this.wasShot) {
			if(this.cellShip == null) {
				this.setFill(Color.WHITE);
			}
		}
	}
	
}

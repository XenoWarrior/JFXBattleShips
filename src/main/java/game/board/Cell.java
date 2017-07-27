package game.board;

import game.DataStorage;
import game.ship.Ship;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {

	private Ship cellShip = null;
	private int cellX, cellY = 0;
	private boolean cellClicked = false;
	
	public Cell(int cx, int cy) {
		super(32, 32);
		
		setFill(Color.WHITE);
		setStroke(Color.BLACK);

		this.cellX = cx;
		this.cellY = cy;
	}
	
	public int getCellX() {
		return this.cellX;
	}
	
	public int getCellY() {
		return this.cellY;
	}
	
	public void setShip(Ship s) {
		this.cellShip = s;
		setFill(Color.AQUA);
	}
	
	public void hideShip() { 
		setFill(Color.WHITE);
	}
	
	public boolean hasShip() {
		if(this.cellShip == null) {
			return false;
		}
		
		return true;
	}
	
	void onMouseClick() {
		
		if(DataStorage.gameReady() && !this.cellClicked) {
			this.cellClicked = true;
			
			if(this.cellShip == null) {
				setFill(Color.GREEN);
				System.out.println("MISS!");

				DataStorage.getNextPlayerTurn();
			}
			else {
				setFill(Color.RED);
				System.out.println("HIT!");
				
				int enemyPlayer = (DataStorage.getCurrentPlayerTurn() == 1 ? 2 : 1);
				DataStorage.setPlayerHealth(enemyPlayer, DataStorage.getPlayerHealth(enemyPlayer)-1);
				
				System.out.println("CURRENT PLAYER: " + DataStorage.getCurrentPlayerTurn() + " | ENEMY PLAYER: " + enemyPlayer + " | Enemy Health: " + DataStorage.getPlayerHealth(enemyPlayer));
			}
		}
	}
	
	void onMouseEnter() {
		if(DataStorage.gameReady() && !this.cellClicked) {
			setFill(Color.GREEN);
		}
		if(!DataStorage.gameReady() && this.cellShip == null) {
			setFill(Color.GREEN);
		}
	}
	
	void onMouseExit() {
		if(DataStorage.gameReady() && !this.cellClicked) {
			setFill(Color.WHITE);
		}
		if(!DataStorage.gameReady() && this.cellShip == null) {
			setFill(Color.WHITE);
		}
	}
	
}

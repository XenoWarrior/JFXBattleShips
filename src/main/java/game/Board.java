package game;

import javafx.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Board {

	private VBox boardColumns = new VBox();
	private int boardSize = 10;
	private BoardData boardData = new BoardData();

	public Board(BoardType bt) {

		Label boardLabel = new Label("Unset");

		switch (bt) {
		case BOARD_PLAYER_1:
			boardLabel.setText("Player 1");
			boardLabel.setAlignment(Pos.BOTTOM_CENTER);
			break;
		case BOARD_PLAYER_2:
			boardLabel.setText("Player 2");
			break;
		case BOARD_PLAYER_AI:
			boardLabel.setText("AI Player");
			break;
		}

		boardColumns.getChildren().add(boardLabel);

		for (int i = 0; i < boardSize; i++) {
			HBox boardRow = new HBox();

			for (int j = 0; j < boardSize; j++) {
				Cell c = new Cell(i, j, this);
				
				c.setOnMouseEntered((e) -> c.onEnter());
				c.setOnMouseExited((e) -> c.onLeave());
				c.setOnMouseClicked((e) -> { 
					if(GlobalData.getGameState()) {
						System.out.println("Shooting...");
						c.onShootShipClick();
					}
					else {
						if(!boardData.isReady() && checkCanPlace(c.getCellX(), c.getCellY(), (e.getButton() == MouseButton.PRIMARY) ? ShipDirection.DIR_HORIZONTAL : ShipDirection.DIR_VERTICAL)) {
							System.out.println("Placing...");
							placeNextShip(c.getCellX(), c.getCellY(), (e.getButton() == MouseButton.PRIMARY) ? ShipDirection.DIR_HORIZONTAL : ShipDirection.DIR_VERTICAL);
						}
					}
				});
				
				boardRow.getChildren().add(c);
			}

			boardColumns.getChildren().add(boardRow);
		}
	}

	public int getShipSize() {
		int shipSize = 0;
		switch(boardData.getCurrentShip()) {
			case SHIP_PATROL:
				System.out.print("[Patrol Boat]");
				shipSize = 2;
				break;
			case SHIP_BATTLE:
				System.out.print("[Battleship]");
				shipSize = 3;
				break;
			case SHIP_SUBMARINE:
				System.out.print("[Submarine]");
				shipSize = 3;
				break;
			case SHIP_DESTROYER:
				System.out.print("[Destroyer]");
				shipSize = 4;
				break;
			case SHIP_CARRIER:
				System.out.print("[Carrier]");
				shipSize = 5;
				break;
		}
		return shipSize;
	}
	
	public boolean checkCanPlace(int cellX, int cellY, ShipDirection shipDirection) {
		System.out.print("[BattleShips] Checking if space is available for ");
		
		int shipSize = this.getShipSize();
		int finalCellX = 0;
		int finalCellY = 0;
		
		try {
			switch(shipDirection) {
				case DIR_HORIZONTAL:
					finalCellX = cellX;
					finalCellY = cellY + shipSize;
					
					System.out.println(" horizontally from " + shipSize + " at [x=" + cellX + ", y=" + cellY + "] to [x=" + finalCellX + ", y=" + finalCellY + "]");
					for(int i = cellY; i < finalCellY; i++) {
						HBox b = (HBox)boardColumns.getChildren().get(finalCellX+1);
						Cell c = (Cell)b.getChildren().get(i);
						
						if(c.containsShip()) {
							return false;
						}
					}
					break;
				case DIR_VERTICAL:
					finalCellX = cellX + shipSize;
					finalCellY = cellY;
	
					System.out.println(" vertically from " + shipSize + " at [x=" + cellX + ", y=" + cellY + "] to [x=" + finalCellX + ", y=" + finalCellY + "]");
					for(int i = cellX; i < finalCellX; i++) {
						HBox b = (HBox)boardColumns.getChildren().get(i+1);
						Cell c = (Cell)b.getChildren().get(finalCellY);
						
						if(c.containsShip()) {
							return false;
						}
					}
					break;
			}
			return true;
		}
		catch(Exception e) { 
			return false;
		}
		 
	}
	
	public VBox getColumns() {
		return boardColumns;
	}

	public void placeNextShip(int cellX, int cellY, ShipDirection shipDirection) {
		System.out.print("[BattleShips] Placing ship: ");
		
		int shipSize = this.getShipSize();
		int finalCellX = 0;
		int finalCellY = 0;

		switch(shipDirection) {
			case DIR_HORIZONTAL:
				finalCellX = cellX;
				finalCellY = cellY + shipSize;
				
				System.out.println(" horizontally from " + shipSize + " at [x="+cellX+", y="+cellY+"] to [x="+finalCellX+", y="+finalCellY+"]");
				for(int i = cellY; i < finalCellY; i++) {
					HBox b = (HBox)boardColumns.getChildren().get(finalCellX+1);
					Cell c = (Cell)b.getChildren().get(i);
					c.onAddShipClick(new Ship(boardData.getCurrentShip()));
				}
				break;
			case DIR_VERTICAL:
				finalCellX = cellX + shipSize;
				finalCellY = cellY;

				System.out.println(" vertically from " + shipSize + " at [x="+cellX+", y="+cellY+"] to [x="+finalCellX+", y="+finalCellY+"]");
				for(int i = cellX; i < finalCellX; i++) {
					HBox b = (HBox)boardColumns.getChildren().get(i+1);
					Cell c = (Cell)b.getChildren().get(finalCellY);
					c.onAddShipClick(new Ship(boardData.getCurrentShip()));
				}
				break;
		}
		boardData.setNextShip();
	}
}

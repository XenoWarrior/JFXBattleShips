package game.board;

import game.DataStorage;
import game.ship.PlaceDirection;
import game.ship.Ship;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Board {
	
	private VBox cellColumns = new VBox();
	private int shipsPlaced = 0;
	
	public Board(int size) {
		DataStorage.resetShip();
		
		for(int i = 0; i < size; i++) {
			HBox cellRow = new HBox();
			
			for(int j = 0; j < size; j++) {
				Cell cell = new Cell(i, j);

				cell.setOnMouseClicked((e) -> { 
					cell.onMouseClick();
					this.onMouseClick(cell, (e.getButton() == MouseButton.PRIMARY) ? PlaceDirection.DIR_HORIZONTAL : PlaceDirection.DIR_VERTICAL);
				});
				cell.setOnMouseEntered((e) -> cell.onMouseEnter());
				cell.setOnMouseExited((e) -> cell.onMouseExit());
				cellRow.getChildren().add(cell);
			}
			
			cellRow.setAlignment(Pos.CENTER);
			cellColumns.getChildren().add(cellRow);
			
		}
		
		cellColumns.setAlignment(Pos.CENTER);
	}
	
	public Board(Node contents) {
		this.cellColumns = (VBox)contents;
	}
	
	public VBox boardColumns() {
		return cellColumns;
	}
	
	public boolean canPlaceShip(Cell c, PlaceDirection pd) {
		try {
			System.out.println("Checking placement...");
			
			int shipSize = DataStorage.getCurrentShip().getSize();
			
			switch(pd) {
			case DIR_HORIZONTAL:
				int finalCellX = c.getCellX();
				int finalCellY = c.getCellY() + shipSize;
				
				System.out.println(" horizontally from " + shipSize + " at [x=" + c.getCellX() + ", y=" + c.getCellY() + "] to [x=" + finalCellX + ", y=" + finalCellY + "]");
				for(int i = c.getCellY(); i < finalCellY; i++) {
					HBox b = (HBox)cellColumns.getChildren().get(finalCellX);
					Cell cc = (Cell)b.getChildren().get(i);
					
					if(cc.hasShip()) {
						return false;
					}
				}
				break;
			case DIR_VERTICAL:
				finalCellX = c.getCellX() + shipSize;
				finalCellY = c.getCellY();
				
				System.out.println(" vertically from " + shipSize + " at [x=" + c.getCellX() + ", y=" + c.getCellY() + "] to [x=" + finalCellX + ", y=" + finalCellY + "]");
				for(int i = c.getCellX(); i < finalCellX; i++) {
					HBox b = (HBox)cellColumns.getChildren().get(i);
					Cell cc = (Cell)b.getChildren().get(finalCellY);
					
					if(cc.hasShip()) {
						return false;
					}
				}
				break;
			}
		}
		catch(Exception ex) {
			return false;
		}
		
		return true;
	}
	
	public void onMouseClick (Cell c, PlaceDirection pd) {
		if(DataStorage.gameReady()) {
			System.out.println("Shooting...");
			c.onMouseClick();
		}
		else {
			if(this.canPlaceShip(c, pd)) {
				System.out.println("Placing ship...");

				Ship s = DataStorage.getCurrentShip();
				if(s != null) {
					int shipSize = -1;
					if(shipsPlaced == 0) {
						shipSize = s.getSize();
					}
					else {			
						shipSize = s.getSize();
					}
					
					this.shipsPlaced++;
					
					System.out.println("MouseClick: ship is: " + DataStorage.getCurrentShip().getType());
					
					switch(pd) {
					case DIR_HORIZONTAL:
						int finalCellX = c.getCellX();
						int finalCellY = c.getCellY() + shipSize;
						
						System.out.println(" horizontally from " + shipSize + " at [x=" + c.getCellX() + ", y=" + c.getCellY() + "] to [x=" + finalCellX + ", y=" + finalCellY + "]");
						for(int i = c.getCellY(); i < finalCellY; i++) {
							HBox b = (HBox)cellColumns.getChildren().get(finalCellX);
							Cell cc = (Cell)b.getChildren().get(i);
							cc.setShip(DataStorage.getCurrentShip());
						}
						break;
					case DIR_VERTICAL:
						finalCellX = c.getCellX() + shipSize;
						finalCellY = c.getCellY();
						
						System.out.println(" vertically from " + shipSize + " at [x=" + c.getCellX() + ", y=" + c.getCellY() + "] to [x=" + finalCellX + ", y=" + finalCellY + "]");
						for(int i = c.getCellX(); i < finalCellX; i++) {
							HBox b = (HBox)cellColumns.getChildren().get(i);
							Cell cc = (Cell)b.getChildren().get(finalCellY);
							cc.setShip(DataStorage.getCurrentShip());
						}
						break;
					}
					DataStorage.getNextShip();
				}
				else {
					System.out.println("No more ships to place.");
				}
			}
			else {
				System.out.println("Unable to place ship, out of bounds or a cell already has a ship.");
			}
		}
	}

	public void hideShips() {
		for(int i = 0; i < this.cellColumns.getChildren().size(); i++) {
			HBox row = (HBox)this.cellColumns.getChildren().get(i);
			
			for(int j = 0; j < row.getChildren().size(); j++) {
				Cell c = (Cell)row.getChildren().get(j);
				if(c.hasShip()) {
					c.hideShip();
				}
			}
		}
	}
	
}

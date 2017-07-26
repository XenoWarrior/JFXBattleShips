package game;

public class BoardData {

	public boolean currentPlayerTurn = false;
	public ShipType currentShipType = ShipType.SHIP_PATROL;
	public boolean boardReady = false;

	public boolean getCurrentPlayerTurn() {
		return this.currentPlayerTurn;
	}
	
	public ShipType getCurrentShip() {
		return this.currentShipType;
	}
	
	public void togglePlayerTurn() {
		this.currentPlayerTurn = !this.currentPlayerTurn;
	}

	public boolean isReady() { 
		return this.boardReady;
	}
	
	public void isReady(boolean b) { 
		this.boardReady = b;
	}
	
	public void setNextShip() { 
		try {			
			ShipType next = ShipType.values()[this.currentShipType.ordinal() + 1];
			this.currentShipType = next;
		}
		catch(Exception e) {
			System.out.println("No more ships to place for this board.");
			
			this.currentShipType = null;
			this.boardReady = true;
		}
	}
}

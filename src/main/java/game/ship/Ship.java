package game.ship;

public class Ship {

	private int shipSize = 0;
	private ShipType shipType = null; 
	
	public Ship(ShipType t) {
		this.shipType = t;
		switch(t) {
		case SHIP_PATROL:
			this.shipSize = 2;
			break;
		case SHIP_BATTLE: 
			this.shipSize = 3;
			break;
		case SHIP_SUBMARINE: 
			this.shipSize = 3;
			break;
		case SHIP_DESTROYER: 
			this.shipSize = 4;
			break;
		case SHIP_CARRIER: 
			this.shipSize = 5;
			break;
		}
	}
	
	public String getName() {
		switch(this.shipType) {
		case SHIP_PATROL:
			return "Patrol Boat";
		case SHIP_BATTLE: 
			return "Battleship";
		case SHIP_SUBMARINE: 
			return "Submarine";
		case SHIP_DESTROYER: 
			return "Destroyer";
		case SHIP_CARRIER: 
			return "Carrier";
		}
		return "UNKNOWN";
	}
	
	public int getSize() {
		return this.shipSize;
	}
	
	public ShipType getType() {
		return this.shipType;
	}
}

package game;

public class Ship {

	private ShipType shipType;
	private int shipHealth; 
	
	public Ship(ShipType t) {
		
		this.shipType = t; 
		switch(t) { 
		case SHIP_PATROL:
			this.shipHealth = 2;
			break;
		case SHIP_BATTLE:
			this.shipHealth = 3;
			break;
		case SHIP_SUBMARINE:
			this.shipHealth = 3;
			break;
		case SHIP_DESTROYER:
			this.shipHealth = 4;
			break;
		case SHIP_CARRIER:
			this.shipHealth = 5;
			break;
		}
		
	}
	
	public void removeHealth() { 
		shipHealth--;
	}
	
	public int getShipSize() {
		 return this.shipHealth;
	}
	
}

package game;

import java.util.HashMap;

import game.board.Board;
import game.ship.Ship;
import game.ship.ShipType;
import javafx.scene.Node;

public class DataStorage {
	
	private static int playerTurn = 1;
	private static HashMap<Integer, Board> boardList = new HashMap<Integer, Board>();
	private static Ship currentShip = new Ship(ShipType.SHIP_PATROL);

	private static int p1Health = 18;
	private static int p2Health = 18;
	
	public static int currentTurnTime = 0;
	public static boolean lockBoard = false;
	
	/**
	 * Sets the next player turn
	 */
	public static int getNextPlayerTurn() {
		if(DataStorage.playerTurn == 1) {
			DataStorage.playerTurn = 2;
		}
		else {
			DataStorage.playerTurn = 1;
		}
		
		return DataStorage.playerTurn;
	}
	
	/**
	 * Returns the next ship for the board
	 * @return, returns the next available ship
	 */
	public static Ship getNextShip() {
		
		ShipType[] shipList = ShipType.values();
		int currentShipIndex = DataStorage.getCurrentShip().getType().ordinal();
		int nextShipIndex = currentShipIndex+1;
		
		if(nextShipIndex > shipList.length-1) {
			DataStorage.currentShip = null;
			return null;
		}
		
		DataStorage.currentShip = new Ship(shipList[nextShipIndex]);
		return DataStorage.currentShip;
	}
	
	/**
	 * Gets the current selected ship
	 * @return, the current ship
	 */
	public static Ship getCurrentShip() {
		return DataStorage.currentShip;
	}
	
	/**
	 * Gets the current player turn
	 * @return the current integer of the player
	 */
	public static int getCurrentPlayerTurn() {
		return DataStorage.playerTurn;
	}

	/**
	 * Adds a new player board to the board storage
	 * @param the new board to add
	 */
	public static void addBoard(Board node) {
		boardList.put(DataStorage.getCurrentPlayerTurn(), node);
	}
	/**
	 * Adds a new player board to the board storage
	 * @param the index to add the board in
	 * @param the new board to add
	 */
	public static void addBoard(int index, Board node) {
		boardList.put(index, node);
	}

	/**
	 * Gets a list of all the game boards available
	 * @return HashMap with [Integer => Board] as [Key => Value] Pair 
	 */
	public static HashMap<Integer, Board> getBoards() {
		return DataStorage.boardList;
	}
	
	/**
	 * Resets the data storage to default values
	 */
	public static void reset() { 
		DataStorage.p1Health = 18;
		DataStorage.p2Health = 18;
		
		DataStorage.playerTurn = 1;
		DataStorage.boardList.clear();
		DataStorage.currentShip = new Ship(ShipType.SHIP_PATROL);
	}
	
	/**
	 * Resets the current ship selection back to the first for the next player
	 */
	public static void resetShip() {
		DataStorage.currentShip = new Ship(ShipType.SHIP_PATROL);
	}
	
	/**
	 * Resets the current turn back to the first player
	 */
	public static void resetPlayer() {
		DataStorage.playerTurn = 0;
	}
	
	/**
	 * Gets the current player health
	 */
	public static int getPlayerHealth(int p) {
		if(p == 1) {
			return DataStorage.p1Health;
		}
		else{ 
			return DataStorage.p2Health;
		}
	}
	
	public static void setPlayerHealth(int p, int h) {
		if(p == 1) {
			DataStorage.p1Health = h;
		}
		else {
			DataStorage.p2Health = h;
		}
	}
	
	/**
	 * Checks if both boards have been added to the game
	 */
	public static boolean gameReady() {
		return DataStorage.boardList.size() == 2 ? true : false;
	}
}

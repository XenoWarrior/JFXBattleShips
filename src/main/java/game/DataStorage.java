package game;

import java.util.HashMap;

import game.board.Board;

public class DataStorage {
	
	private static int playerTurn = 1;
	private static HashMap<Integer, Board> boardList = new HashMap<Integer, Board>();
	
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
	public static void addBoard(Board b) {
		boardList.put(DataStorage.getCurrentPlayerTurn(), b);
	}

	/**
	 * Gets a list of all the game boards available
	 * @return HashMap with [Integer => Board] as [Key => Value] Pair 
	 */
	public static HashMap<Integer, Board> getBoards() {
		return boardList;
	}
	
	public static void reset() { 
		playerTurn = 1;
		boardList.clear();
	}

}

package game;

public class GlobalData {

	private static boolean gameRunning = false;
	
	public static boolean getGameState() {
		 return gameRunning;
	}
	
	public static void toggleGameState() {
		gameRunning = !gameRunning;
	}
	
}

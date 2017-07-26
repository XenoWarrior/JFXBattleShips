package game;

import java.util.HashMap;

import engine.handlers.GameLoop;
import engine.state.State;
import game.board.Board;
import game.states.EndState;
import game.states.GameState;
import game.states.InitState;
import game.states.MenuState;
import game.states.PlayState;
import game.states.SetupState;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * 
 * Main entrypoint for the game, handles setting up and running of the program
 * @author Ashley Scott
 *
 */
public class App extends Application {

	private static HashMap<String, State> stateList = new HashMap<String, State>();
	private static HashMap<Integer, Board> boardList = new HashMap<Integer, Board>();
	private static Label runTime = new Label();
	private static Stage mainStage = new Stage();;
	private static GameState gameState = GameState.STATE_INIT;
	
	public static long startTime = System.nanoTime();
	public static long runTimeVal = 0;

	/**
	 * Application entry
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Overrides the JavaFX start method to control application from this class
	 */
	@Override
	public void start(Stage mainStage) throws Exception {
		State stateInit = new InitState(GameState.STATE_INIT);
		State stateMenu = new MenuState(GameState.STATE_MENU);
		State stateSetup = new SetupState(GameState.STATE_SETUP);
		State statePlay = new PlayState(GameState.STATE_PLAY);
		State stateEnd = new EndState(GameState.STATE_END);
		
		App.mainStage.setScene(stateInit.getScene());
		App.mainStage.setWidth(1024);
		App.mainStage.setHeight(768);
		App.mainStage.show();
		
		App.stateList.put(stateInit.getType().toString(), stateInit);
		App.stateList.put(stateMenu.getType().toString(), stateMenu);
		App.stateList.put(stateSetup.getType().toString(), stateSetup);
		App.stateList.put(statePlay.getType().toString(), statePlay);
		App.stateList.put(stateEnd.getType().toString(), stateEnd);
		App.printStageList();
		
		new GameLoop() { 
			public void handle(long l) {
				// Main game logic
				App.runTimeVal = ((l - App.startTime) / 1000000000);
				App.runTime.setText("Time: " + App.runTimeVal);
				
				// Update the current scene we are on
				App.stateList.get(App.getState().toString()).update();
			}
		}.start();
	}
	
	/**
	 * Gets the current game state
	 * @return, the current game state
	 */
	public static GameState getState() {
		return App.gameState;
	}
	
	/**
	 * Sets the current game state and switches to it
	 * @param s, the state enum to switch to 
	 * @throws Exception, if the state does not exist next to the state enum
	 */
	public static void setState(GameState s) throws Exception { 
		if(s != App.gameState) {
			try {
				App.gameState = s;
				App.mainStage.setScene(App.stateList.get(App.getState().toString()).getScene());
				App.mainStage.show();
			}
			catch(Exception ex) {
				throw new Exception("State (GameState." + s + ") does not exist.");
			}
		}
	}

	/**
	 * Used to debug states, prints a list of all available states
	 */
	public static void printStageList() {
		for(State s: App.stateList.values()) {
			System.out.println(s.getStage().getTitle() + "\n   => GaneState." + s.getType() + "\n   => " + s.getClass().getName() + "\n");
		}
	}
	
	/**
	 * Gets a list of all the game boards available
	 * @return HashMap with [Integer => Board] as [Key => Value] Pair 
	 */
	public HashMap<Integer, Board> getBoards() {
		return boardList;
	}
}

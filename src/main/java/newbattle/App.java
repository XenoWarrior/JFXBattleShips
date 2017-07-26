package newbattle;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {

	private static HashMap<String, State> stateList = new HashMap<String, State>();
	private static HashMap<Integer, Board> boardList = new HashMap<Integer, Board>();
	
	private static GameState gameState = GameState.STATE_INIT;
	private static long startTime = System.nanoTime();
	private static Label runTime = new Label();

	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage initStage) throws Exception {
		State stateInit = new InitState();
		State stateMenu = new MenuState();
		State stateSetup = new SetupState();
		State statePlay = new PlayState();
		stateInit.getStage().show();
		
		stateList.put(GameState.STATE_INIT.toString(), stateInit);
		stateList.put(GameState.STATE_MENU.toString(), stateMenu);
		stateList.put(GameState.STATE_SETUP.toString(), stateSetup);
		stateList.put(GameState.STATE_PLAY.toString(), statePlay);

		App.printStageList();
		
		new GameLoop() { 
			public void handle(long l) {
				// Main game logic
				App.runTime.setText("Time: " + ((l-startTime) / 1000000000));
				
				if(App.getState() == GameState.STATE_INIT) {
					
					try {
						App.setState(GameState.STATE_MENU);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}.start();
	}
	
	public static GameState getState() {
		return App.gameState;
	}
	
	public static void setState(GameState s) throws Exception { 
		if(s != App.gameState) {
			try {
				App.stateList.get(App.getState().toString()).getStage().hide();
				App.stateList.get(s.toString()).getStage().show();
				
				App.gameState = s;
			}
			catch(Exception ex) {
				throw new Exception("State (" + s + ") does not exist.");
			}
		}
	}

	public static void printStageList() {
		System.out.println(GameState.STATE_INIT.toString());
		System.out.println(GameState.STATE_MENU.toString());
		System.out.println(GameState.STATE_SETUP.toString());
		System.out.println(GameState.STATE_PLAY.toString());
		
		for(State s: App.stateList.values()) {
			System.out.println(s.getStage().getTitle());
		}
	}
	
	public HashMap<Integer, Board> getBoards() {
		return boardList;
	}
}

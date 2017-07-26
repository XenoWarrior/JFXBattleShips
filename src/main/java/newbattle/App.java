package newbattle;

import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	private HashMap<Integer, Board> boardList = new HashMap<Integer, Board>();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.show();
		
		new GameLoop() { 
			public void handle(long l) {
				// Main game logic
			}
		}.start();
		
	}

}

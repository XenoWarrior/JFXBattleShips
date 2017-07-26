package com.projectge.battleship;

import game.Board;
import game.BoardType;
import game.GameLoop;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

@SuppressWarnings("restriction")
public class App extends Application {
	
	Object o = new Object();
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Board b1 = new Board(BoardType.BOARD_PLAYER_1);
		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(10);
		rootContainer.getChildren().add(b1.getColumns()); //createBoards()); //new Board(BoardType.BOARD_PLAYER_1).getColumns());
        Scene scene = new Scene(rootContainer);
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(scene);
        primaryStage.show();

		Board b2 = new Board(BoardType.BOARD_PLAYER_2);
        Stage primaryStage1 = new Stage();
		VBox rootContainer2 = new VBox();
		rootContainer2.setPadding(new Insets(25, 25, 25, 25));
		rootContainer2.setSpacing(10);
		rootContainer2.getChildren().add(b2.getColumns()); //createBoards()); //new Board(BoardType.BOARD_PLAYER_1).getColumns());
        Scene scene2 = new Scene(rootContainer2);
        primaryStage1.setTitle("Battleships");
        primaryStage1.setScene(scene2);
        //primaryStage1.show();
        
        new GameLoop()
        {
            public void handle(long currentNanoTime)
            {
            	if(b1.isReady()) {
            		System.out.println("Player 1 is ready.");
            		b1.isReady(false);
                    primaryStage1.show();
                    primaryStage.hide();
            	}
            	else if(b2.isReady()) {
            		System.out.println("Ready!");
                    primaryStage1.hide();
            	}
            }
        }.start();
	}
	
	public Parent createBoards() {
		
        BorderPane boardRoot = new BorderPane();

		Board p1Board = new Board(BoardType.BOARD_PLAYER_1);
		Board p2Board = new Board(BoardType.BOARD_PLAYER_2);
		
		HBox boardContainer = new HBox(100, p1Board.getColumns(), p2Board.getColumns());
        boardContainer.setAlignment(Pos.CENTER);
        boardRoot.setCenter(boardContainer);
        
		return boardRoot;
		
	}

}

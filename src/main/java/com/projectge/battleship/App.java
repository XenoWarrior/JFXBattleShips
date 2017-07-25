package com.projectge.battleship;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import com.sun.prism.paint.Color;

import game.Board;
import game.BoardType;
import game.Cell;
import game.BoardData;
import game.ShipDirection;
import javafx.application.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("restriction")
public class App extends Application {

	
	Object o = new Object();
	

	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		VBox rootContainer = new VBox();
		rootContainer.setPadding(new Insets(25, 25, 25, 25));
		rootContainer.setSpacing(10);
		rootContainer.getChildren().add(createBoards());
		
        Scene scene = new Scene(rootContainer);
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(scene);
        primaryStage.show();
        
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

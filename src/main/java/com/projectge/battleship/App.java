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
		
		VBox rootContainer1 = new VBox();
		rootContainer1.setPadding(new Insets(25, 25, 25, 25));
		rootContainer1.setSpacing(10);
		rootContainer1.getChildren().add(new Board(BoardType.BOARD_PLAYER_1).getColumns());

        Scene scene1 = new Scene(rootContainer1);
        primaryStage.setTitle("Battleships");
        primaryStage.setScene(scene1);
        primaryStage.show();


        Stage primaryStage1 = new Stage();
        
		VBox rootContainer2 = new VBox();
		rootContainer2.setPadding(new Insets(25, 25, 25, 25));
		rootContainer2.setSpacing(10);
		rootContainer2.getChildren().add(new Board(BoardType.BOARD_PLAYER_2).getColumns());
        
        Scene scene2 = new Scene(rootContainer2);
        primaryStage1.setTitle("Battleships");
        primaryStage1.setScene(scene2);
        primaryStage1.show();
        
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

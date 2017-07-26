package game.board;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Board {
	
	private VBox cellColumns = new VBox();
	
	public Board(int size) {
		
		for(int i = 0; i < size; i++) {
			HBox cellRow = new HBox();
			
			for(int j = 0; j < size; j++) {
				Cell cell = new Cell();

				cell.setOnMouseClicked((e) -> cell.onMouseClick());
				cell.setOnMouseEntered((e) -> cell.onMouseEnter());
				cell.setOnMouseExited((e) -> cell.onMouseExit());
				cellRow.getChildren().add(cell);
			}
			
			cellRow.setAlignment(Pos.CENTER);
			cellColumns.getChildren().add(cellRow);
			
		}
		
		cellColumns.setAlignment(Pos.CENTER);
		
	}
	
	public VBox boardColumns() {
		return cellColumns;
	}

}

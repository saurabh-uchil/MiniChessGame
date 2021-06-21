package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SquareStack extends StackPane {
	private int rowValue;
	private int columnValue;
	private boolean isSelected;
	private boolean isValidMove;

	public SquareStack(int row, int column, String color) {
		this.rowValue = row;
		this.columnValue = column;
		this.setColor(color);
		this.setId("SQ_" + row + "_" + column);
		this.isSelected = false;
	}

	public int getRow() {
		return rowValue;
	}

	public int getColumn() {
		return columnValue;
	}

	public boolean getIsSelected() {
		return isSelected;
	}

	public boolean getIsValidMove() {
		return isValidMove;
	}

	public void setIsSelected(boolean b) {
		isSelected = b;
	}

	public void setIsValidMove(boolean b) {
		isValidMove = b;
	}

	public void setPiece(String imagePath) {
		if (imagePath != null) {
			ImageView img = new ImageView(imagePath);
			this.getChildren().clear();
			this.getChildren().add(img);
		}
		else
			this.getChildren().clear();
			
	}

	public void highlight() {
		setColor("lightgreen");
	}

	public void removeHighlight() {
		if ((this.getRow() + this.getColumn()) % 2 == 0)
			setColor("WHITE");
		else
			setColor("DIMGRAY");
	}

	public void setColor(String color) {
		this.setStyle("-fx-background-color: " + color + ";-fx-border-color: black;");
	}
}

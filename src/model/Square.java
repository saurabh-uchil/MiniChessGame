package model;

public class Square {

	private int rowVal;
	private int columnVal;
	private Piece piece;
	
	public Square(int row, int col) {
		rowVal = row;
		columnVal = col;
	}
	
	public int getRowValue() {
		return rowVal;
	}
	
	public int getColumnValue() {
		return columnVal;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public boolean occupySquare(Piece newPiece) {
		//Replace the piece in the destination square
		if(this.piece != null) {
			this.piece.setSquare(null);
			this.piece.setStatus("removed");
			
			//Add 5 points for the other player
			newPiece.getPlayer().addPoints();
		}
		this.piece = newPiece;
		this.piece.setSquare(this);
		return true;
	}
	
	public boolean releaseSquare() {
		this.piece = null;
		return true;
	}
	
	
}

package model;

public class PieceDecorator extends Piece{
	private Piece piece;
	
	public PieceDecorator(Piece piece) {
		super();
		this.piece = piece;
	}
	
	public void setValidMoves(Board b){
		piece.setValidMoves(b);
	}
}

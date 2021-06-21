package model;

public class Rook extends Piece{
	
	public Rook(String id, String img){
		super(id,img);
	}
	
	public Rook(String id) {
		
	}
	
	public void setValidMoves(Board b){
		
		int rowPos = square.getRowValue();
		int colPos = square.getColumnValue();
		
		resetValidMoves();
		
		//Vertically down
		for(int r=rowPos+1; r<=rowPos+2; r++) {
			if(r > 5)
				break;
			else if(b.getSquare(r, colPos).getPiece()!= null) { 
				if(b.getSquare(r, colPos).getPiece().getPlayer() != this.player)
					this.validMoves[r][colPos] = true;
				break;
			}
			else 
				this.validMoves[r][colPos] = true;
		}
		
		//Vertically up
		for(int r=rowPos-1; r>=rowPos-2; r--) {
			if(r < 0)
				break;
			else if(b.getSquare(r, colPos).getPiece() !=null) { 
				if(b.getSquare(r, colPos).getPiece().getPlayer() != this.player)
					this.validMoves[r][colPos] = true;
				break;
			}
			else
				this.validMoves[r][colPos] = true;
		}
		
		//Horizontally right
		for(int c=colPos+1; c<=colPos+2; c++) {
			if(c > 5)
				break;
			else if(b.getSquare(rowPos, c).getPiece() !=null) {
				if(b.getSquare(rowPos, c).getPiece().getPlayer() != this.player)
					this.validMoves[rowPos][c] = true;
				break;
			}
			else
				this.validMoves[rowPos][c] = true;
		}
		
		//Horizontally left
		for(int c=colPos-1; c>=colPos-2; c--) {
			if(c < 0)
				break;
			else if(b.getSquare(rowPos, c).getPiece() != null) {
				if(b.getSquare(rowPos, c).getPiece().getPlayer() != this.player)
					this.validMoves[rowPos][c] = true;
				break;
			}
			else
				this.validMoves[rowPos][c] = true;
		}
	}

}

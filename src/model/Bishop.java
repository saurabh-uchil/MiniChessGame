package model;

public class Bishop extends Piece{
	
	public Bishop(String id, String img){
		super(id, img);
	}
	
	public Bishop(String id) {
		
	}
	
	public void setValidMoves(Board b){
		
		int rowPos = square.getRowValue();
		int colPos = square.getColumnValue();
		
		resetValidMoves();
		
		int newRow, newCol;
		
		//Bottom right
		for(int i = 1; i <= 2; i++) {
			newRow = rowPos + i;
			newCol = colPos + i;
			if(newRow > 5 || newRow < 0 || newCol > 5 || newCol < 0)
				break;
			else if(b.getSquare(newRow, newCol).getPiece()!= null) {
				if(b.getSquare(newRow, newCol).getPiece().getPlayer() != this.player)
					this.validMoves[newRow][newCol] = true;
				break;
			}
			else
				this.validMoves[newRow][newCol] = true;	
		}
		
		//Top right
		for(int i = 1; i <= 2; i++) {
			newRow = rowPos - i;
			newCol = colPos + i;
			if(newRow > 5 || newRow < 0 || newCol > 5 || newCol < 0)
				break;
			else if(b.getSquare(newRow, newCol).getPiece()!= null) {
				if(b.getSquare(newRow, newCol).getPiece().getPlayer() != this.player)
					this.validMoves[newRow][newCol] = true;
				break;
			}
			else
				this.validMoves[newRow][newCol] = true;	
		}
		
		//Bottom left
		for(int i = 1; i <= 2; i++) {
			newRow = rowPos + i;
			newCol = colPos - i;
			if(newRow > 5 || newRow < 0 || newCol > 5 || newCol < 0)
				break;
			else if(b.getSquare(newRow, newCol).getPiece()!= null) {
				if(b.getSquare(newRow, newCol).getPiece().getPlayer() != this.player)
					this.validMoves[newRow][newCol] = true;
				break;
			}
			else
				this.validMoves[newRow][newCol] = true;	
		}
		
		//Top left
		for(int i = 1; i <= 2; i++) {
			newRow = rowPos - i;
			newCol = colPos - i;
			if(newRow > 5 || newRow < 0 || newCol > 5 || newCol < 0)
				break;
			else if(b.getSquare(newRow, newCol).getPiece()!= null) {
				if(b.getSquare(newRow, newCol).getPiece().getPlayer() != this.player)
					this.validMoves[newRow][newCol] = true;
				break;
			}
			else
				this.validMoves[newRow][newCol] = true;	
		}
		
		
	}
}

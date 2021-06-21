package model;

public class Knight extends Piece {

	public Knight(String id, String img) {
		super(id, img);
	}
	
	public Knight(String id) {
		
	}

	public void setValidMoves(Board b) {
		int rowPos = square.getRowValue();
		int colPos = square.getColumnValue();
		
		resetValidMoves();

		// Moving vertically down
		for (int r = rowPos + 1; r <= rowPos + 2; r++) {
			if (r > 5)
				break;
			else {
				if (r == rowPos + 2) {
					//Vertical down right
					if(colPos + 1 <= 5) {
						if (b.getSquare(r, colPos + 1).getPiece() == null
								|| b.getSquare(r, colPos + 1).getPiece().getPlayer() != this.player)
							this.validMoves[r][colPos + 1] = true;
					}
					//Vertical down left
					if(colPos - 1 >= 0) {
						if (b.getSquare(r, colPos - 1).getPiece() == null
								|| b.getSquare(r, colPos - 1).getPiece().getPlayer() != this.player)
							this.validMoves[r][colPos - 1] = true;
					}
				}
			}
		}

		// Moving vertically up
		for (int r = rowPos - 1; r >= rowPos - 2; r--) {
			if (r < 0)
				break;
			else {
				if (r == rowPos - 2) {
					//Vertical up right
					if(colPos + 1 <= 5) {
						if (b.getSquare(r, colPos + 1).getPiece() == null
								|| b.getSquare(r, colPos + 1).getPiece().getPlayer() != this.player)
							this.validMoves[r][colPos + 1] = true;
					}
					//Vertical up left
					if(colPos - 1 >= 0) {
						if (b.getSquare(r, colPos - 1).getPiece() == null
								|| b.getSquare(r, colPos - 1).getPiece().getPlayer() != this.player)
							this.validMoves[r][colPos - 1] = true;
					}
				}
			}
		}

		// Moving horizontal to right
		for (int c = colPos + 1; c <= colPos + 2; c++) {
			if (c > 5)// || rowPos + 1 > 5 || rowPos - 1 < 0)
				break;
			else {
				if (c == colPos + 2) {
					//Horizontal right bottom
					if(rowPos + 1 <= 5) {
						if (b.getSquare(rowPos + 1, c).getPiece() == null
								|| b.getSquare(rowPos + 1, c).getPiece().getPlayer() != this.player)
							this.validMoves[rowPos + 1][c] = true;
					}
					//Horizontal right up
					if(rowPos - 1 >= 0) {
						if (b.getSquare(rowPos - 1, c).getPiece() == null
								|| b.getSquare(rowPos - 1, c).getPiece().getPlayer() != this.player)
							this.validMoves[rowPos - 1][c] = true;
					}
				}
			}
		}

		// Moving horizontal to left
		for (int c = colPos - 1; c >= colPos - 2; c--) {
			if (c < 0)
				break;
			else {
				if (c == colPos - 2) {
					//Horizontal left bottom
					if (rowPos + 1 <= 5) {
						if (b.getSquare(rowPos + 1, c).getPiece() == null
								|| b.getSquare(rowPos + 1, c).getPiece().getPlayer() != this.player)
							this.validMoves[rowPos + 1][c] = true;
					}
					//Horizontal left up
					if (rowPos - 1 >= 0) {
						if (b.getSquare(rowPos - 1, c).getPiece() == null
								|| b.getSquare(rowPos - 1, c).getPiece().getPlayer() != this.player)
							this.validMoves[rowPos - 1][c] = true;
					}
				}
			}
		}
		

	}
}

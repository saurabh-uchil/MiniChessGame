package model;

public class Board {
	private Square[][] squares = new Square[6][6];

	public Board() {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				this.squares[i][j] = new Square(i, j);
			}
		}
	}

	public Square getSquare(int x, int y) {
		return squares[x][y];
	}

	public boolean isValidDestination(Square sqr) {
		return true;
	}

	public String draw() {
		String board = "    0    1    2    3    4    5  ";
		for (int row = 0; row < squares.length; row++) {
			board += "\n  -------------------------------\n";
			board += row + " ";
			for (int column = 0; column < squares.length; column++) {
				board += "| " + getSymbol(squares[row][column].getPiece()) + " ";
			}
			board += "|";
		}
		board += "\n  -------------------------------\n";

		return board;

	}

	private String getSymbol(Piece p) {
		String symbol = "";

		if (p == null)
			symbol = "  ";
		else
			symbol = p.getId();
		return symbol;
	}

}

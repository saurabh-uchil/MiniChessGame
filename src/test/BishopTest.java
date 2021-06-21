package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Bishop;
import model.Board;
import model.Piece;
import model.Rook;

class BishopTest {
	Piece bishop; 
	Board board;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		bishop = new Bishop("B1");
		board = new Board();
		board.getSquare(0, 1).occupySquare(bishop);
		bishop.setValidMoves(board);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

		//Move diagonally by 2 squares
		@Test
		void bishop_MoveDiagonally() {
			assertTrue(bishop.move(board.getSquare(2, 3)));
		}
				
		//Move diagonally by 2 squares bypassing other piece
		@Test
		void bishop_MoveOverPiece() {
			Rook rookDummy = new Rook("R1");
			board.getSquare(1, 2).occupySquare(rookDummy);
			assertFalse(bishop.move(board.getSquare(2, 3)));
		}
		
		//Move in any other direction
		@Test
		void bishop_MoveOtherDirections() {
			assertFalse(bishop.move(board.getSquare(2, 1)));
		}
		
		//Move more than 2 squares
		@Test
		void bishop_MoveMoreThanTwoSquares() {
			assertFalse(bishop.move(board.getSquare(3, 4)));
		}

}

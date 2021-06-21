package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Board;
import model.Knight;
import model.Piece;
import model.Rook;

class KnightTest {
	Piece knight; 
	Board board;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		knight = new Knight("K1");
		board = new Board();
		board.getSquare(0, 2).occupySquare(knight);
		knight.setValidMoves(board);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

		//Move along L shape (2+1)
		@Test
		void knight_MoveLShape() {
			assertTrue(knight.move(board.getSquare(2, 1)));
		}
		
		//Move in any other direction
		@Test
		void knight_MoveOtherDirections() {
			assertFalse(knight.move(board.getSquare(1, 2)));
		}
		
		//Move over other pieces
		@Test
		void knight_MoveOverPiece() {
			Rook rookDummy = new Rook("R1");
			board.getSquare(2, 2).occupySquare(rookDummy);
			assertTrue(knight.move(board.getSquare(2, 3)));
		}
		


}

package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Board;
import model.Piece;
import model.Rook;

class RookTest {
	Piece rook; 
	Board board;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		board = new Board();
		rook = new Rook("R1");
		board.getSquare(0,0).occupySquare(rook);
		rook.setValidMoves(board);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	//Move vertically by 2 squares
	@Test
	void rook_MoveVertically() {
		assertTrue(rook.move(board.getSquare(2, 0)));
	}
	
	//Move horizontally by 2 squares
	@Test
	void rook_MoveHorizontally() {
		assertTrue(rook.move(board.getSquare(0, 2)));
	}
	
	//Move horizontally by 2 squares bypassing other piece
	@Test
	void rook_MoveOverPiece() {
		// Fail due to player number
		Rook rookDummy = new Rook("R2");
		board.getSquare(0, 1).occupySquare(rookDummy);
		assertFalse(rook.move(board.getSquare(0, 2)));
	}
	
	//Move in any other direction
	@Test
	void rook_MoveOtherDirections() {
		assertFalse(rook.move(board.getSquare(1, 2)));
	}
	
	//Move more than 2 squares
	@Test
	void rook_MoveMoreThanTwoSquares() {
		assertFalse(rook.move(board.getSquare(0, 4)));
	}
	
	

}

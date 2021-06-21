package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Board;
import model.Constants;
import model.Knight;
import model.Piece;
import model.Player;
import model.Rook;
import model.User;

class PlayerTest {
	Player p1, p2;
	Board b;
	Piece rook;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		b = new Board();
		rook = new Rook("R1");
		b.getSquare(0, 0).occupySquare(rook);
		
		//User user1 = new User("John","password");
		p1 = new Player("John",Constants.COLOR_WHITE);
		//User user2 = new User("Jack","password");
		p2 = new Player("Jack",Constants.COLOR_BLACK);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	//Check number of moves by player
//	@Test
//	void test_MoveCount() {
//		p1.addPiece(rook);
//		rook.move(b.getSquare(2,0));
//		rook.move(b.getSquare(2,2));
//		assertEquals(2,p1.getMovesCount());
//		
//	}
	
	//Check points owned by player after removing opponent piece
	@Test
	void test_PointCount() {
		p1.addPiece(rook);
		
		Piece knight = new Knight("k1");
		p2.addPiece(knight);
		b.getSquare(2, 0).occupySquare(knight);
		
		rook.move(b.getSquare(2,0));
		assertEquals(5,p1.getPoints());
	}

}

package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Piece;
import model.Rook;
import model.Square;

class SquareTest {
	Square sqr1, sqr2;
	Piece p1, p2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		sqr1 = new Square(0, 0);
		sqr2 = new Square(0, 1);
		p1 = new Rook("r1");
		p2 = new Rook("R1");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void Move_Test1() {
		sqr1.occupySquare(p1);
		assertEquals(p1,sqr1.getPiece());		
	}
	
	@Test
	void Move_Test2() {
		sqr1.occupySquare(p1);
		sqr1.occupySquare(p2);
		assertNotEquals(p1,sqr1.getPiece());		
	}
	
	@Test
	void Move_Test3() {
		sqr1.occupySquare(p1);
		sqr1.occupySquare(p2);
		assertFalse(p1.isAvailable());		
	}

}

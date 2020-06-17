package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import chess.Board;
import chess.King;
import chess.Piece;

class BoardTest {
	Board b = new Board();
	Piece[][] board = new Piece[8][8];
	Piece whiteKing;
	Piece blackKing;
	
	@Test
	void testBoard() {
		assertEquals(0, b.file('A'));
		assertEquals(0, b.rank('1'));
		assertEquals(false, b.isEnded);
		assertNotNull(b);
		
		board[0][4] = new King(0, 4, "White");
		board[7][4] = new King(7, 4, "Black");
		
		PrintStream oldOut = System.out;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(baos));
	    b.print();
	    System.setOut(oldOut);
	    String output = new String(baos.toByteArray());
	    
	    assertTrue(output.contains("- + - + K + - + 8"));
	    assertTrue(output.contains("+ - + - + - + - 7"));
	    assertTrue(output.contains("- + - + - + - + 6"));
	    assertTrue(output.contains("+ - + - + - + - 5"));
	    assertTrue(output.contains("- + - + - + - + 4"));
	    assertTrue(output.contains("+ - + - + - + - 3"));
	    assertTrue(output.contains("- + - + - + - + 2"));
	    assertTrue(output.contains("+ - + - k - + - 1"));
	    assertTrue(output.contains("A B C D E F G H"));
	}

}

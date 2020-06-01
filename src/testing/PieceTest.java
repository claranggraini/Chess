package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.Pawn;
import chess.Piece;

class PieceTest {
	Piece[][] board = new Piece[8][8];
	Piece whitePawn;
	
	@Test
	void testInput() {
		whitePawn = board[1][4] = new Pawn(1, 4, "White");
		
		//dapet semua data
		assertEquals(1, whitePawn.getRank());
		assertEquals(4, whitePawn.getFile());
		assertEquals('p', whitePawn.getId());
		assertEquals("White", whitePawn.getColor());
		assertEquals(1, Piece.getPieceList().size());
	}

}

package testing;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import chess.Piece;
import chess.Queen;

class QueenTest {
	Piece[][] board = new Piece[8][8];
	Piece whiteQueen;
	Piece whiteQueenFriend;
	Piece blackQueen;

	@Test
	void testQueenMove() {
		whiteQueen = board[4][4] = new Queen(4, 4, "White");
		blackQueen = board[2][4] = new Queen(2, 4, "Black");

		// bisa lurus kemanapun
		
		assertEquals(true, whiteQueen.valPiece(board, 2, 4));
		assertEquals(true, whiteQueen.valPiece(board, 4, 2));
		assertEquals(true, whiteQueen.valPiece(board, 6, 4));
		assertEquals(true, whiteQueen.valPiece(board, 4, 6));
		
		// bisa diagonal kemanapun
		assertEquals(true, whiteQueen.valPiece(board, 2, 2));
		assertEquals(true, whiteQueen.valPiece(board, 2, 6));
		assertEquals(true, whiteQueen.valPiece(board, 6, 6));
		assertEquals(true, whiteQueen.valPiece(board, 6, 2));

		whiteQueenFriend = board[5][4] = new Queen(5, 4, "White");

		// ada temen gabisa lewat
		assertEquals(false, whiteQueen.valPiece(board, 6, 4));
	}

}

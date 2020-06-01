package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.Knight;
import chess.Piece;

class KnightTest {
	Piece[][] board = new Piece[8][8];
	Piece whiteKnight;
	Piece whiteKnightFriend;
	Piece blackKnight;

	@Test
	void testKnightMove() {
		whiteKnight = board[3][3] = new Knight(3, 3, "White");
		blackKnight = board[2][1] = new Knight(2, 1, "Black");

		// bisa bentuk L kemanapun
		assertEquals(true, whiteKnight.valPiece(board, 2, 1));
		assertEquals(true, whiteKnight.valPiece(board, 1, 2));
		assertEquals(true, whiteKnight.valPiece(board, 1, 4));
		assertEquals(true, whiteKnight.valPiece(board, 2, 5));
		assertEquals(true, whiteKnight.valPiece(board, 4, 5));
		assertEquals(true, whiteKnight.valPiece(board, 5, 4));
		assertEquals(true, whiteKnight.valPiece(board, 5, 2));
		assertEquals(true, whiteKnight.valPiece(board, 4, 1));

		whiteKnightFriend = board[3][3] = new Knight(4, 3, "White");

		// ada temen bisa lewat
		assertEquals(true, whiteKnight.valPiece(board, 4, 1));
	}
	
}
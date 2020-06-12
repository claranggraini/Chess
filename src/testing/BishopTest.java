package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.Bishop;
import chess.Piece;

class BishopTest {
	Piece[][] board = new Piece[8][8];
	Piece whiteBishop;
	Piece whiteBishopFriend;
	Piece blackBishop;

	@Test
	void testBishopMove() {
		whiteBishop = board[4][4] = new Bishop(4, 4, "White");
		blackBishop = board[2][2] = new Bishop(2, 2, "Black");

		// bisa diagonal kemanapun
		assertEquals(true, whiteBishop.valPiece(board, 2, 2));
		assertEquals(true, whiteBishop.valPiece(board, 2, 6));
		assertEquals(true, whiteBishop.valPiece(board, 6, 6));
		assertEquals(true, whiteBishop.valPiece(board, 6, 2));

		whiteBishopFriend = board[3][3] = new Bishop(3, 3, "White");

		// ada temen gabisa lewat
		assertEquals(false, whiteBishop.valPiece(board, 2, 2));
		
		//can't do straight moves
		assertEquals(false, whiteBishop.valPiece(board, 2, 4));
		assertEquals(false, whiteBishop.valPiece(board, 4, 2));
		
		//out of bound
		assertEquals(false, blackBishop.valPiece(board,-1 , -1));
	}
	
}
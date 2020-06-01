package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.Rook;
import chess.Piece;

class RookTest {
	Piece[][] board = new Piece[8][8];
	Piece whiteRook;
	Piece whiteRookFriend;
	Piece blackRook;

	@Test
	void testRookMove() {
		whiteRook = board[4][4] = new Rook(4, 4, "White");
		blackRook = board[2][4] = new Rook(2, 4, "Black");

		// bisa lurus kemanapun
		assertEquals(true, whiteRook.valPiece(board, 2, 4));
		assertEquals(true, whiteRook.valPiece(board, 4, 2));
		assertEquals(true, whiteRook.valPiece(board, 6, 4));
		assertEquals(true, whiteRook.valPiece(board, 4, 6));

		whiteRookFriend = board[5][4] = new Rook(5, 4, "White");

		// ada temen gabisa lewat
		assertEquals(false, whiteRook.valPiece(board, 6, 4));
	}
	
	@Test
	void testRookHasMoved() {
		whiteRook = board[4][4] = new Rook(4, 4, "White");
		
		//belum jalan
		assertEquals(false, ((Rook) whiteRook).isHasMoved());
		
		try {
			whiteRook.checkPiece(board, 4, 2);
		} catch (Exception ignored) {
		}
		
		//udah jalan
		assertEquals(true, ((Rook) whiteRook).isHasMoved());
	}

}

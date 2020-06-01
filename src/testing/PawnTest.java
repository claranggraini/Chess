package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess.Pawn;
import chess.Piece;

class PawnTest {
	Piece[][] board = new Piece[8][8];
	Piece whitePawn;
	Piece whitePawnFriend;
	Piece blackPawn;

	@Test
	void testPawnMove() {
		whitePawn = board[1][4] = new Pawn(1, 4, "White");
		blackPawn = board[6][3] = new Pawn(6, 3, "Black");

		// pas jalan pertama, jalan sekali
		assertEquals(true, whitePawn.valPiece(board, 2, 4));
		assertEquals(true, blackPawn.valPiece(board, 5, 3));

		// pas jalan pertama, jalan dua kali
		assertEquals(true, whitePawn.valPiece(board, 3, 4));
		assertEquals(true, blackPawn.valPiece(board, 4, 3));

		// ga bisa mundur
		assertEquals(false, whitePawn.valPiece(board, 0, 4));
		assertEquals(false, blackPawn.valPiece(board, 7, 3));

		try {
			whitePawn.checkPiece(board, 3, 4);
			blackPawn.checkPiece(board, 4, 3);
		} catch (Exception ignored) {
		}

		// udah jalan, ga bisa jalan dua kali
		assertEquals(false, whitePawn.valPiece(board, 5, 4));
		assertEquals(false, blackPawn.valPiece(board, 2, 3));

		whitePawnFriend = board[4][4] = new Pawn(4, 4, "White");

		// ada piece gabisa lewat
		assertEquals(false, whitePawn.valPiece(board, 4, 4));

		// makan piece
		assertEquals(true, whitePawn.valPiece(board, 4, 3));
	}

	@Test
	void testPawnEnPassant() {
		whitePawn = board[4][4] = new Pawn(4, 4, "White");
		whitePawnFriend = board[1][6] = new Pawn(1, 6, "White");
		blackPawn = board[6][3] = new Pawn(6, 3, "Black");

		try {
			blackPawn.checkPiece(board, 4, 3);
		} catch (Exception ignored) {
		}

		// enpassant berhasil
		assertEquals(true, whitePawn.valPiece(board, 5, 3));

		try {
			whitePawnFriend.checkPiece(board, 2, 6);
		} catch (Exception ignored) {
		}

		// sudah ada yang jalan, enpassant tidak berhasil
		assertEquals(false, whitePawn.valPiece(board, 5, 3));
	}

	@Test
	void testPawnPromotion() {
		// bingung soalnya validatePromotion private :D
	}
	
}
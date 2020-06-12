package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess.Bishop;
import chess.Knight;
import chess.Pawn;
import chess.Piece;
import chess.Queen;
import chess.Rook;

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
		
		//out of bound
		assertEquals(false, whitePawn.valPiece(board, 8, 4));
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

		// enpassant berhasil
		try {
			blackPawn.checkPiece(board, 4, 3);
		} catch (Exception ignored) {
		}
		try {
			whitePawn.checkPiece(board, 5, 3);
		}catch(Exception ignored) {
			
		}
		assertEquals(5, whitePawn.getRank());
		assertEquals(3, whitePawn.getFile());
		assertEquals(null, board[4][3]);
	}
	
	@Test
	void testPawnEnPassant2() {
		whitePawn = board[4][4] = new Pawn(4, 4, "White");
		whitePawnFriend = board[1][6] = new Pawn(1, 6, "White");
		blackPawn = board[6][3] = new Pawn(6, 3, "Black");

		try {
			blackPawn.checkPiece(board, 4, 3);
		} catch (Exception ignored) {
		}

		try {
			whitePawnFriend.checkPiece(board, 2, 6);
		} catch (Exception ignored) {
		}

		// sudah ada yang jalan, enpassant tidak berhasil
		assertEquals(4, whitePawn.getRank());
		assertEquals(4, whitePawn.getFile());
		assertEquals(4, blackPawn.getRank());
		assertEquals(3, blackPawn.getFile());
	}
	
	@Test
	void testPawnEnPassant3() {
		whitePawn = board[4][4] = new Pawn(4, 4, "White");
		whitePawnFriend = board[1][6] = new Pawn(1, 6, "White");
		blackPawn = board[6][5] = new Pawn(6, 5, "Black");

		// enpassant to right
		try {
			blackPawn.checkPiece(board, 4, 5);
		} catch (Exception ignored) {
		}
		try {
			whitePawn.checkPiece(board, 5, 5);
		}catch(Exception ignored) {
			
		}
		assertEquals(5, whitePawn.getRank());
		assertEquals(5, whitePawn.getFile());
		assertEquals(null, board[4][5]);
		
	}
	
	@Test
	void testPawnEnPassant4() {
		whitePawn = board[3][4] = new Pawn(3, 4, "White");
		whitePawnFriend = board[1][6] = new Pawn(1, 6, "White");
		blackPawn = board[6][5] = new Pawn(6, 5, "Black");

		// failed to enpassant because theres no pawn passing through
		try {
			blackPawn.checkPiece(board, 5, 5);
		} catch (Exception ignored) {
		}
		try {
			whitePawn.checkPiece(board, 4, 5);
		}catch(Exception ignored) {
			
		}
		assertEquals(3, whitePawn.getRank());
		assertEquals(4, whitePawn.getFile());
		
	}

	@Test
	void testPawnPromoteToQueen() {
		whitePawn = board[6][2] = new Pawn(6, 2, "White");
		((Pawn)whitePawn).setToPromote('Q');
		
		try {
			whitePawn.checkPiece(board, 7, 2);
		}catch(Exception ignored) {
		}
		//Pawn successfully promoted to Queen
		assertEquals(board[7][2].getClass(), Queen.class);
	}
	
	@Test
	void testPawnPromoteToBishop() {
		whitePawn = board[6][2] = new Pawn(6, 2, "White");
		((Pawn)whitePawn).setToPromote('B');
		
		try {
			whitePawn.checkPiece(board, 7, 2);
		}catch(Exception ignored) {
		}
		//Pawn successfully promoted to Queen
		assertEquals(board[7][2].getClass(), Bishop.class);
	}
	
	@Test
	void testPawnPromoteToKnight() {
		whitePawn = board[6][2] = new Pawn(6, 2, "White");
		((Pawn)whitePawn).setToPromote('N');
		
		try {
			whitePawn.checkPiece(board, 7, 2);
		}catch(Exception ignored) {
		}
		//Pawn successfully promoted to Queen
		assertEquals(board[7][2].getClass(), Knight.class);
	}
	
	@Test
	void testPawnPromoteToRook() {
		whitePawn = board[6][2] = new Pawn(6, 2, "White");
		((Pawn)whitePawn).setToPromote('R');
		
		try {
			whitePawn.checkPiece(board, 7, 2);
		}catch(Exception ignored) {
		}
		//Pawn successfully promoted to Queen
		assertEquals(board[7][2].getClass(), Rook.class);
	}
	@Test
	void testPawnPromoteFailed() {
		whitePawn = board[6][2] = new Pawn(6, 2, "White");
		((Pawn)whitePawn).setToPromote(' ');
		
		try {
			whitePawn.checkPiece(board, 7, 2);
		}catch(Exception ignored) {
		}
		//Pawn successfully promoted to Queen
		assertEquals(6, whitePawn.getRank());
		assertEquals(2, whitePawn.getFile());
		assertEquals(null, board[7][2]);
	}
}
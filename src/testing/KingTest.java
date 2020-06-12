package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.Piece;
import chess.Rook;
import chess.Check;
import chess.King;
import chess.Pawn;

class KingTest {
	Piece[][] board = new Piece[8][8];
	Piece whiteKing;
	Piece blackPawn;
	Piece whiteRook;
	
	@Test
	void testKingMove() {
		whiteKing = board[4][4] = new King(4, 4, "White");
		blackPawn = board[3][4] = new Pawn(3, 4, "Black");
		
		// bisa lurus 1 kotak kemanapun
		assertEquals(true, whiteKing.valPiece(board, 3, 4));
		assertEquals(true, whiteKing.valPiece(board, 4, 3));
		assertEquals(true, whiteKing.valPiece(board, 3, 4));
		assertEquals(true, whiteKing.valPiece(board, 4, 3));
		
		// bisa diagonal 1 kotak kemanapun
		assertEquals(true, whiteKing.valPiece(board, 3, 3));
		assertEquals(true, whiteKing.valPiece(board, 3, 5));
		assertEquals(true, whiteKing.valPiece(board, 5, 5));
		assertEquals(true, whiteKing.valPiece(board, 5, 3));
	}
	
	@Test
	void testKingIsChecked() {
		whiteKing = board[4][4] = new King(4, 4, "White");
		blackPawn = board[6][3] = new Pawn(6, 3, "Black");
		Check checker = new Check(board, "White");
		
		//belum check
		assertEquals(false, checker.check());
		
		try {
			blackPawn.checkPiece(board, 5, 3);
		} catch (Exception ignored) {
		}
		
		//udah check
		assertEquals(true, checker.check());
	}
	
	@Test
	void testKingSideCastling() {
		whiteKing = board[0][4] = new King(0, 4, "White");
		whiteRook = board[0][7] = new Rook(0, 7, "White");
		
		assertEquals(true, ((King)whiteKing).castling(board, 6));
	}
	
	@Test
	void testQueenSideCastling() {
		whiteKing = board[0][4] = new King(0, 4, "White");
		whiteRook = board[0][0] = new Rook(0, 0, "White");
		
		assertEquals(true, ((King)whiteKing).castling(board, 2));
	}
	
	@Test
	void testUnableCastling() {
		whiteKing = board[0][4] = new King(0, 4, "White");
		whiteRook = board[0][0] = new Rook(0, 0, "White");
		blackPawn = board[1][3] = new Pawn(1, 3, "Black");
		
		//king is in check
		assertEquals(false, ((King)whiteKing).castling(board, 2));
		
	}
	
	@Test
	void testUnableCastling2() {
		whiteKing = board[0][4] = new King(0, 4, "White");
		whiteRook = board[0][0] = new Rook(0, 0, "White");
		blackPawn = board[1][2] = new Pawn(1, 2, "Black");
		
		//king was exposed
		assertEquals(false, ((King)whiteKing).castling(board, 2));
	}
	
	@Test
	void testUnableCastling3() {
		whiteKing = board[0][4] = new King(0, 4, "White");
		whiteRook = board[0][0] = new Rook(0, 0, "White");
		blackPawn = board[0][3] = new Pawn(1, 2, "Black");
		
		//king's route is blocked
		assertEquals(false, ((King)whiteKing).castling(board, 2));
	}

	@Test
	void testUnableCastling4() {
		whiteKing = board[0][4] = new King(0, 4, "White");
		whiteRook = board[0][0] = new Rook(0, 0, "White");
				
		try {
			whiteKing.checkPiece(board, 0, 3);
		} catch (Exception ignored) {
	
		}
		try {
			whiteKing.checkPiece(board, 0, 4);
		} catch (Exception ignored) {
		}
		
		//king has moved
		assertEquals(false, ((King)whiteKing).castling(board, 2));
	}
	
}

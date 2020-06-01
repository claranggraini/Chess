package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.Piece;
import chess.Check;
import chess.King;
import chess.Pawn;

class KingTest {
	Piece[][] board = new Piece[8][8];
	Piece whiteKing;
	Piece blackPawn;

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
	void testKingHasMoved() {
		// bingung soalnya hasMoved private :D
	}
	
	@Test
	void testKingIsChecked() {
		whiteKing = board[4][4] = new King(4, 4, "White");
		blackPawn = board[6][3] = new Pawn(6, 3, "Black");
		Check checker = new Check(board);
		
		//belum check
		assertEquals(false, checker.check("White"));
		
		try {
			blackPawn.checkPiece(board, 5, 3);
		} catch (Exception ignored) {
		}
		
		//udah check
		assertEquals(true, checker.check("White"));
	}
	
	@Test
	void testKingCastling() {
		// bingung soalnya castling private :D
	}

}

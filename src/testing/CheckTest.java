package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.Check;
import chess.King;
import chess.Pawn;
import chess.Piece;
import chess.Queen;

class CheckTest {
	Piece[][] board = new Piece[8][8];
	Piece whiteKing;
	Piece blackKing;
	Piece blackQueen;
	Piece whiteQueen;
	Piece whitePawn;
	Piece blackPawn;
	
	@Test
	void testCheck() { 
		whiteKing = board[5][7] = new King(5, 7, "White");
		blackQueen = board[0][7] = new Queen(0, 7, "Black");
		Check checker = new Check(board, "White");
		
		//white king check
		assertEquals(true, checker.check());		
	}
	
	@Test
	void testCheck2() { 
		whiteKing = board[5][7] = new King(5, 7, "White");
		blackQueen = board[0][5] = new Queen(0, 5, "Black");
		Check checker = new Check(board, "White");
		
		//white king is not in check		
		assertEquals(false, checker.check());
		
	}
	
	@Test
	void testCheck3() { 
		whiteKing = board[0][3] = new King(0, 3, "White");
		whitePawn = board[1][3] = new Pawn(1, 3, "White");
		whiteQueen = board[3][3] = new Queen(3, 3, "Black");
		blackPawn = board[2][2] = new Pawn(2, 2, "Black");
		Check checker = new Check(board, "White");
		
		//white pawn didn't move because white king was exposed
		checker.check();
		try {
			whitePawn.checkPiece(board, 2, 2);
		}catch(Exception ignored) {
			
		}
		try {
			checker.reCheck(whitePawn, 1, 3);
		}catch(Exception ignored) {
			
		}
		assertEquals(1, whitePawn.getRank());
		assertEquals(3, whitePawn.getFile());
	}
	
	@Test
	void testCheck4() { 
		whiteKing = board[0][3] = new King(0, 3, "White");
		whitePawn = board[1][2] = new Pawn(1, 2, "White");
		blackQueen = board[3][3] = new Queen(3, 3, "Black");
		Check checker = new Check(board, "White");
		
		//white pawn didn't move because white king is in check
		checker.check();
		try {
			whitePawn.checkPiece(board, 2, 2);
		}catch(Exception ignored) {
			
		}
		try {
			checker.reCheck(whitePawn, 1, 2);
		}catch(Exception ignored) {
			
		}
		assertEquals(1, whitePawn.getRank());
		assertEquals(2, whitePawn.getFile());
	}
	
	@Test
	void testStalemate() { 
		board[5][5] = new King(5, 5, "White");
		board[7][5] = new King(7, 5, "Black");
		board[6][5] = new Pawn(6, 5, "White");
		
		Check checker = new Check(board, "Black");
		
		//black king stalemate
		assertEquals(true, checker.gameOver());
		
	}	
	
	@Test
	void testGameOver() { 
		whiteKing = board[1][0] = new King(1, 0, "Black");
		blackKing = board[4][6] = new King(4, 6, "White");
		blackQueen = board[7][7] = new Queen(7, 7, "White");
		whitePawn = board[1][1] = new Pawn(1, 1, "Black");
		
		Check checker = new Check(board, "Black");
		
		//white king stalemate
		assertEquals(false, checker.gameOver());
		
	}

	@Test
	void testGameOver2() { 
		board[0][0] = new King(0, 0, "White");
		board[1][0] = new Pawn(1, 0, "White");
		board[4][6] = new King(4, 6, "Black");
		board[2][1] = new Queen(2, 1, "Black");
		
		Check checker = new Check(board, "White");
		
		//white pawn can attack black queen
		assertEquals(false, checker.gameOver());	
	}
	
	@Test
	void testGameOver3() { 
		board[0][0] = new King(0, 0, "White");
		board[0][2] = new Queen(0, 2, "White");
		board[4][6] = new King(4, 6, "Black");
		board[2][1] = new Queen(2, 1, "Black");
		
		Check checker = new Check(board, "White");
		
		//white queen can block black queen
		assertEquals(false, checker.gameOver());
	}
	
	@Test
	void testCheckmate() { 
		whiteKing = board[5][7] = new King(5, 7, "White");
		blackKing = board[5][5] = new King(5, 5, "Black");
		blackQueen = board[0][7] = new Queen(0, 7, "Black");
		Check checker = new Check(board, "White");
		
		//white king checkmate
		assertEquals(true, checker.gameOver());
		
	}	
}

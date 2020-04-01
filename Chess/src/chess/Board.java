package chess;

import java.util.Scanner;

public class Board {
	Scanner scan = new Scanner(System.in);
	private char board[][];
	private int turn;

	public Board() {
		board = new char[][] { { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, { 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' }, { 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' } };
		turn = -1;
	}

	public int file(char f){
        return f-'A';
    }
    
    public int rank(char r){
        return r-'1';
    }
	
	public void inputCoor() throws Exception {
		String color = turn == 1 ? "Black" : "White";
		System.out.print(color + " move: ");

		String coor = scan.next();
		char c1 = coor.charAt(0);
		char c2 = coor.charAt(1);
		char c3 = coor.charAt(2);
		char c4 = coor.charAt(3);
		char c5 = coor.charAt(4);

		int fromFile = file(c1);
		int fromRank = rank(c2);
        int toFile = file(c4);
		int toRank = rank(c5);
		
		if (coor.length() != 5) {
			throw new Exception("Invalid move: query must be 5 characters!");
		}
		if (c1 < 'A' || c1 > 'H') {
			throw new Exception("Invalid move: 1st character");
		}
		if (c2 < '1' || c2 > '8') {
			throw new Exception("Invalid move: 2nd character");
		}
		if (c3 != '-') {
			throw new Exception("Invalid move: 3rd character");
		}
		if (c4 < 'A' || c4 > 'H') {
			throw new Exception("Invalid move: 4th character");
		}
		if (c5 < '1' || c5 > '8') {
			throw new Exception("Invalid move: 5th character");
		}
		if (board[fromRank][fromFile] == ' ') {
			throw new Exception("Invalid move: the chosen piece doesn't exist");
		}
		if (c1 == c4 && c2 == c5) {
			throw new Exception("Invalid move: the designated coordinate is wrong");
		}
		if (pieceIsBlack(fromRank, fromFile) && color != "Black") {
			throw new Exception("Invalid move: wrong piece color");
		}
		if (pieceIsWhite(fromRank, fromFile) && color != "White") {
			throw new Exception("Invalid move: wrong piece color");
		}
		if ((pieceIsBlack(fromRank, fromFile) && pieceIsBlack(toRank, toFile)) || (pieceIsWhite(fromRank, fromFile) && pieceIsWhite(toRank, toFile))) {
			throw new Exception("Invalid move: can't eat your own piece");
		}
		
		if(board[fromRank][fromFile] == 'p' || board[fromRank][fromFile] == 'P') {
			Pawn p = new Pawn(color, board, fromRank, fromFile, toRank, toFile);
			p.checkPiece();
		}else if(board[fromRank][fromFile] == 'r' || board[fromRank][fromFile] == 'R') {
			Rook r = new Rook(color, board, fromRank, fromFile, toRank, toFile);
			r.checkPiece();
		}else if(board[fromRank][fromFile] == 'b' || board[fromRank][fromFile] == 'B') {
			Bishop bp = new Bishop(color, board, fromRank, fromFile, toRank, toFile);
			bp.checkPiece();
		}else if(board[fromRank][fromFile] == 'n' || board[fromRank][fromFile] == 'N') {
			Knight n = new Knight(color, board, fromRank, fromFile, toRank, toFile);
			n.checkPiece();
		}else if(board[fromRank][fromFile] == 'q' || board[fromRank][fromFile] == 'Q') {
			Queen q = new Queen(color, board, fromRank, fromFile, toRank, toFile);
			q.checkPiece();
		}else if(board[fromRank][fromFile] == 'k' || board[fromRank][fromFile] == 'K') {
			King k = new King(color, board, fromRank, fromFile, toRank, toFile);
			k.checkPiece();
		}
	
		turn *= -1;
	}

	public boolean pieceIsBlack(int Rank, int File) {
		if (Character.isUpperCase(board[Rank][File])) {
			return true;
		}
		return false;
	}
	
	public boolean pieceIsWhite(int Rank, int File) {
		if (Character.isLowerCase(board[Rank][File])) {
			return true;
		}
		return false;
	}

	public char[][] getBoard() {
		return board;
	}

}
package chess;

import java.util.Scanner;

public class Board {
	Scanner scan = new Scanner(System.in);
	private char board[][];
	private int turn;
	static Check check = new Check();
	
	private static boolean whiteKingMoved;
	private static boolean whiteKingsideRookMoved;
	private static boolean whiteQueensideRookMoved;
	private static boolean blackKingMoved;
	private static boolean blackKingsideRookMoved;
	private static boolean blackQueensideRookMoved;

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
			if(r.checkMove()) {
				if(fromRank == 0 && fromFile == 0) {
					whiteQueensideRookMoved = true;
				}
				else if(fromRank == 0 && fromFile == 7) {
					whiteKingsideRookMoved = true;
				}
				else if(fromRank == 7 && fromFile == 0) {
					blackQueensideRookMoved = true;
				}
				else if(fromRank == 7 && fromFile == 7) {
					blackKingsideRookMoved = true;
				}
			}
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
			if(k.checkMove()) {
				if(fromRank == 0 && fromFile == 4) {
					whiteKingMoved = true;
				}
				else if(fromRank == 7 && fromFile == 4) {
					blackKingMoved = true;
				}
			}
		}
		
		if(color.equals("White")) {
			if(check.isChecked("Black", board, 10, 10, 10, 10))
				System.out.println("BLACK CHECKED");
		}
		else {
			if(check.isChecked("White", board, 10, 10, 10, 10))
			System.out.println("WHITE CHECKED");
		}
		
		turn *= -1;
	}

	public boolean pieceIsBlack(int rank, int file) {
		if (Character.isUpperCase(board[rank][file])) {
			return true;
		}
		return false;
	}
	
	public boolean pieceIsWhite(int rank, int file) {
		if (Character.isLowerCase(board[rank][file])) {
			return true;
		}
		return false;
	}

	public char[][] getBoard() {
		return board;
	}
	
	public static boolean canKingsideCastling(String color, char[][] board, int toRank, int toFile) {
		if (color.equals("White")) {
			if(whiteKingMoved == true || whiteKingsideRookMoved == true) {
				return false;
			}
			else if(board[0][5] != ' ' || board[0][6] != ' ') {
				return false;
			}
			else if(check.isChecked(color, board, 10, 10, 10, 10)) {
				return false;
			}
			else if(check.isChecked(color, board, 0, 4, 0, 5)) {
				return false;
			}
			else if(check.isChecked(color, board, 0, 4, 0, 6)) {
				return false;
			}
			else {
				whiteKingMoved = true;
				whiteKingsideRookMoved = true;
			}
		}
		else if (color.equals("Black")) {
			if(blackKingMoved == true || blackKingsideRookMoved == true) {
				return false;
			}
			else if(board[7][5] != ' ' || board[7][6] != ' ') {
				return false;
			}
			else if(check.isChecked(color, board, 10, 10, 10, 10)) {
				return false;
			}
			else if(check.isChecked(color, board, 7, 4, 7, 5)) {
				return false;
			}
			else if(check.isChecked(color, board, 7, 4, 7, 6)) {
				return false;
			}
			else {
				blackKingMoved = true;
				blackKingsideRookMoved = true;
			}
		}
		return true;
	}
	
	public static boolean canQueensideCastling(String color, char[][] board, int toRank, int toFile) {
		if (color.equals("White")) {
			if(whiteKingMoved == true || whiteQueensideRookMoved == true) {
				return false;
			}
			else if(board[0][3] != ' ' || board[0][2] != ' ' || board[0][1] != ' ') {
				return false;
			}
			else if(check.isChecked(color, board, 10, 10, 10, 10)) {
				return false;
			}
			else if(check.isChecked(color, board, 0, 4, 0, 3)) {
				return false;
			}
			else if(check.isChecked(color, board, 0, 4, 0, 2)) {
				return false;
			}
			else {
				whiteKingMoved = true;
				whiteQueensideRookMoved = true;
			}
		}
		else if (color.equals("Black")) {
			if(blackKingMoved == true || blackQueensideRookMoved == true) {
				return false;
			}
			else if(board[7][3] != ' ' || board[7][2] != ' ' || board[7][1] != ' ') {
				return false;
			}
			else if(check.isChecked(color, board, 10, 10, 10, 10)) {
				return false;
			}
			else if(check.isChecked(color, board, 7, 4, 7, 3)) {
				return false;
			}
			else if(check.isChecked(color, board, 7, 4, 7, 2)) {
				return false;
			}
			else {
				blackKingMoved = true;
				blackQueensideRookMoved = true;
			}
		}
		return true;
	}

}
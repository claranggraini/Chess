package chess;

import java.util.Scanner;

public class Board {
	Scanner scan = new Scanner(System.in);
	private char board[][];
	private int turn;

	Piece piece = new Piece();

	public Board() {
		board = new char[][] { { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' }, { 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
				{ '+', '-', '+', '-', '+', '-', '+', '-' }, { '-', '+', '-', '+', '-', '+', '-', '+' },
				{ '+', '-', '+', '-', '+', '-', '+', '-' }, { '-', '+', '-', '+', '-', '+', '-', '+' },
				{ 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' }, { 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' } };
		turn = -1;
	}

	public void inputCoor() throws Exception {
		String color = turn == 1 ? "Black" : "White";
		System.out.print(color + " move: ");

		String coor = scan.nextLine();
		char c1 = coor.charAt(0);
		char c2 = coor.charAt(1);
		char c3 = coor.charAt(2);
		char c4 = coor.charAt(3);
		char c5 = coor.charAt(4);

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
		if (board[c2 - '1'][c1 - 'A'] == '+' || board[c2 - '1'][c1 - 'A'] == '-') {
			throw new Exception("Invalid move: the chosen piece doesn't exist");
		}
		if (c1 == c4 && c2 == c5) {
			throw new Exception("Invalid move: the designated coordinate is wrong");
		}
		if (pieceIsBlack(c1, c2) && color != "Black") {
			throw new Exception("Invalid move: wrong piece color");
		}
		if (pieceIsWhite(c1, c2) && color != "White") {
			throw new Exception("Invalid move: wrong piece color");
		}
		if ((pieceIsBlack(c1, c2) && pieceIsBlack(c4, c5)) || (pieceIsWhite(c1, c2) && pieceIsWhite(c4, c5))) {
			throw new Exception("Invalid move: can't eat your own piece");
		}

		piece.move(color, board, c1, c2);
		turn *= -1;
	}

	public boolean pieceIsBlack(char x, char y) {
		if (Character.isUpperCase(board[y - '1'][x - 'A'])) {
			return true;
		}
		return false;
	}
	
	public boolean pieceIsWhite(char x, char y) {
		if (Character.isLowerCase(board[y - '1'][x - 'A'])) {
			return true;
		}
		return false;
	}

	public char[][] getBoard() {
		return board;
	}

}
package chess;

import java.util.Scanner;

public class Board {

	Scanner scan = new Scanner(System.in);

	private Piece[][] board = new Piece[8][8];
	private int turn;
	boolean isEnded = false;

	public Board() {
		this.initBoard();
		turn = -1;
	}

	public void initBoard() {
		board[0][4] = new King(0, 4, "White");
		board[7][4] = new King(7, 4, "Black");

		board[0][0] = new Rook(0, 0, "White");
		board[0][1] = new Knight(0, 1, "White");
		board[0][2] = new Bishop(0, 2, "White");
		board[0][3] = new Queen(0, 3, "White");
		board[0][5] = new Bishop(0, 5, "White");
		board[0][6] = new Knight(0, 6, "White");
		board[0][7] = new Rook(0, 7, "White");

		board[7][0] = new Rook(7, 0, "Black");
		board[7][1] = new Knight(7, 1, "Black");
		board[7][2] = new Bishop(7, 2, "Black");
		board[7][3] = new Queen(7, 3, "Black");
		board[7][5] = new Bishop(7, 5, "Black");
		board[7][6] = new Knight(7, 6, "Black");
		board[7][7] = new Rook(7, 7, "Black");

		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(1, i, "White");
			board[6][i] = new Pawn(6, i, "Black");
		}
		
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}
		
	}

	public int file(char f) {
		return f - 'A';
	}

	public int rank(char r) {
		return r - '1';
	}

	public Piece[][] getBoard() {
		return board;
	}

	public void print() {
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) {
					if (j % 2 == 0 && i % 2 == 0 || j % 2 != 0 && i % 2 != 0) {
						System.out.print('+');
					} else {
						System.out.print('-');
					}
				} else {
					System.out.print(board[i][j].getId());
				}
				System.out.print(" ");
			}
			int info = i + 1;
			System.out.println(info);
		}
		System.out.println("A B C D E F G H");
	}

	public void inputCoor() throws Exception {
		Piece piece;
		String color = turn == 1 ? "Black" : "White";
		Check checker = new Check(board,color);
		
		if (checker.gameOver()) {
			isEnded = true;
		}

		System.out.print(color + " move: ");
		String coor = scan.next();
		
		isCoorOutOfBound(coor);
		int fromFile = file(coor.charAt(0));
		int fromRank = rank(coor.charAt(1));
		int toFile = file(coor.charAt(3));
		int toRank = rank(coor.charAt(4));
		
		piece = board[fromRank][fromFile];
		
		validateCoor(piece, color, coor, toFile, toRank);
		
		piece.checkPiece(board, toRank, toFile);
		
		checker.reCheck(piece, fromRank, fromFile);

		turn *= -1;
	}

	private void validateCoor(Piece piece, String color, String coor, int toFile, int toRank) throws Exception {
		if (piece == null) {
			throw new Exception("Please choose a piece");
		}
		if (coor.length() > 5 && !piece.getClass().equals(Pawn.class)) {
			throw new Exception("Invalid move: wrong input");
		}
		if(coor.length() > 5 && piece.getClass().equals(Pawn.class) && !((Pawn)piece).validatePromotion(toRank)) {
			throw new Exception("Invalid move: can't promote");
		}
		if (pieceIsBlack(piece) && !color.equals("Black") || pieceIsWhite(piece) && !color.equals("White")) {
			throw new Exception("Invalid move: wrong piece color");
		}
		if (board[toRank][toFile] != null) {
			Piece p = board[toRank][toFile];
			if (color.equals("Black") && pieceIsBlack(p) || color.equals("White") && pieceIsWhite(p)) {
				throw new Exception("Invalid move: can't eat your own piece");
			}
		}
		if (coor.length() == 6 && promoteQuery(coor.charAt(5))) {
			Pawn p = (Pawn) piece;
			p.setToPromote(coor.charAt(5));
		}
	}
	
	private void isCoorOutOfBound(String coor) throws Exception {
		if(coor.length()<5||coor.length()>6) {
			throw new Exception("Input must be [A-H][1-8]-[A-H][1-8]");
		}else {
			if (coor.charAt(0) < 'A' || coor.charAt(0) > 'H') {
				throw new Exception("Invalid move: 1st character");
			}
			if (coor.charAt(1) < '1' || coor.charAt(1) > '8') {
				throw new Exception("Invalid move: 2nd character");
			}
			if (coor.charAt(2) != '-') {
				throw new Exception("Invalid move: 3rd character");
			}
			if (coor.charAt(3) < 'A' || coor.charAt(3) > 'H') {
				throw new Exception("Invalid move: 4th character");
			}
			if (coor.charAt(4) < '1' || coor.charAt(4) > '8') {
				throw new Exception("Invalid move: 5th character");
			}
		}
	}

	private boolean pieceIsBlack(Piece p) {
		if (p.getColor().equals("Black")) {
			return true;
		}
		return false;
	}

	private boolean pieceIsWhite(Piece p) {
		if (p.getColor().equals("White")) {
			return true;
		}
		return false;
	}

	public void setBoard(Piece[][] board, int toRank, int toFile, Piece p) {
		this.board[toRank][toFile] = p;
	}

	private boolean promoteQuery(char toPromote) {
		return toPromote == 'Q' || toPromote == 'B' || toPromote == 'R' || toPromote == 'N';
	}
}
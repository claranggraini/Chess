package chess;

import java.util.Scanner;
import java.util.Vector;

public class Board {

	Scanner scan = new Scanner(System.in);
	
	private Piece[][] board = new Piece [8][8];
	private int turn;
	
	public Board() {
		this.initBoard();
		turn = -1;
	}

	public void initBoard() {
		
		board[0][4] = new King(0,4,"White");
		board[7][4] = new King(7,4,"Black");
		
		board[0][0] = new Rook(0,0,"White");
		board[0][1] = new Knight(0,1,"White");
		board[0][2] = new Bishop(0,2,"White");
		board[0][3] = new Queen(0,3,"White");	
		board[0][5] = new Bishop(0,5,"White");
		board[0][6] = new Knight(0,6,"White");
		board[0][7] = new Rook(0,7,"White");
		
		board[7][0] = new Rook(7,0,"Black");
		board[7][1] = new Knight(7,1,"Black");
		board[7][2] = new Bishop(7,2,"Black");
		board[7][3] = new Queen(7,3,"Black");
		board[7][5] = new Bishop(7,5,"Black");
		board[7][6] = new Knight(7,6,"Black");
		board[7][7] = new Rook(7,7,"Black");
		
		for(int i=0;i<8;i++) {
			board[1][i] = new Pawn(1,i,"White");
			board[6][i] = new Pawn(6,i,"Black");

		}
		for(int i=2;i<6;i++) {
			for(int j=0;j<8;j++) {
				board[i][j] = null;			
			}
		}
	
	}

	public int file(char f){
        return f-'A';
    }
    
    public int rank(char r){
        return r-'1';
    }

	public Piece[][] getBoard() {
		return board;
	}
    public void print() {
    	for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {	
				if(board[i][j] == null) {
					if(j%2==0 && i%2==0 || j%2!=0 && i%2!=0) {
						System.out.print('+');
					}else {
						System.out.print('-');
					}
				}else {
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
		Check checker = new Check(board);
		System.out.print(color + " move: ");

		String coor = scan.next();
		if(coor.length()<5) {
			throw new Exception("Invalid move: wrong input");
		}
		char c1 = coor.charAt(0);
		char c2 = coor.charAt(1);
		char c3 = coor.charAt(2);
		char c4 = coor.charAt(3);
		char c5 = coor.charAt(4);
	
		int fromFile = file(c1);
		int fromRank = rank(c2);
        int toFile = file(c4);
		int toRank = rank(c5);
		
		
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
		piece = board[fromRank][fromFile];
		if(coor.length()>5 && !piece.getClass().equals(Pawn.class)||coor.length()>6) {
			throw new Exception("Invalid move: wrong input");
		}
		if(piece==null) {
			throw new Exception("Please choose a piece");
		}if (pieceIsBlack(piece) && !color.equals("Black")) {
			throw new Exception("Invalid move: wrong piece color");
		}else if (pieceIsWhite(piece) && !color.equals("White")) {
			throw new Exception("Invalid move: wrong piece color");
		}if (board[toRank][toFile]!=null){
			Piece p = board[toRank][toFile];
			if(color.equals("Black") && pieceIsBlack(p) || color.equals("White") && pieceIsWhite(p)) {
				throw new Exception("Invalid move: can't eat your own piece");
			}
		}if(coor.length() == 6 && promoteQuery(coor.charAt(5))) {
			Pawn p = (Pawn)piece;
			p.setToPromote(coor.charAt(5));
		}
		checker.check(color);
		piece.checkPiece(board, toRank, toFile);
		checker.reCheck(piece, color, fromRank, fromFile);

		turn*=-1;
    }

    public boolean pieceIsBlack(Piece p) {
		if (p.getColor().equals("Black")) {
			return true;
		}
		return false;
	}
	
	public boolean pieceIsWhite(Piece p) {
		if (p.getColor().equals("White")) {
			return true;
		}
		return false;
	}

	public void setBoard(Piece[][] board, int toRank, int toFile, Piece p) {
		this.board[toRank][toFile] = p;
	}

	public boolean promoteQuery(char toPromote) {
		return toPromote =='Q' || toPromote =='B' || toPromote =='R' || toPromote =='N';
	}
	
}	

package chess;

import java.util.Scanner;

public class Board {
	Scanner scan = new Scanner(System.in);
	private char board[][];
	private int turn;
	
	Piece p = new Piece();
	
	public Board(){
		board = new char[][] {{'r','n','b','q','k','b','n','r'},
			{'p','p','p','p','p','p','p','p'},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' '},
			{'P','P','P','P','P','P','P','P'},
			{'R','N','B','Q','K','B','N','R'}};
		turn = 1;
	}
	
	public void initBoard() {
		System.out.println("      BLACK");
		System.out.println("-----------------");
		for(int i=7;i>=0;i--) {
			for(int j=0;j<8;j++) {
				System.out.print("|" + board[i][j]);
			}
			System.out.println("|");
			System.out.println("-----------------");
		}
		System.out.println("      WHITE");
		
	}
	
	public void inputCoor() throws Exception {		
		String color = turn == 1 ? "Black" : "White";
		System.out.println(color + " turn [1-8][A-H]-[1-8][A-H]: ");
		
		String coor = scan.nextLine();
		char c1 = coor.charAt(0);
		char c2 = coor.charAt(1);
		char c3 = coor.charAt(2);
		char c4 = coor.charAt(3);
		char c5 = coor.charAt(4);
		
		if(coor.length()!=5) {
			throw new Exception("Invalid move: query must be 5 characters!");
		}if(c1 < '1' || c1 > '8' ) {
			throw new Exception("Invalid move: 1st character");
		}if(c2 < 'A' || c2 > 'H' ) {
			throw new Exception("Invalid move: 2nd character");
		}if(c3 != '-') {
			throw new Exception("Invalid move: 3rd character");
		}if(c4 < '1' || c4 > '8') {
			throw new Exception("Invalid move: 4th character");
		}if(c5 < 'A' || c5 > 'H') {
			throw new Exception("Invalid move: 5th character");
		}if(board[c1-'1'][c2-'A'] == ' ') {
			throw new Exception("Invalid move: the chosen piece doesn't exist");
		}if(c1==c4 && c2==c5) {
			throw new Exception("Invalid move: the designated coordinate is wrong");
		}if(pieceIsBlack(c1, c2) && color != "Black") {
			throw new Exception("Invalid move: wrong piece color");
		}if(!pieceIsBlack(c1, c2) && color != "White") {
			throw new Exception("Invalid move: wrong piece color");
		}if(pieceIsBlack(c1, c2) && pieceIsBlack(c4, c5) || !pieceIsBlack(c1, c2) && !pieceIsBlack(c4, c5)) {
			throw new Exception("Invalid move: can't eat your own piece");
		}
		
		p.move(color, board, c1, c2);
		turn*=-1;
	}
	
	public boolean pieceIsBlack(char x, char y) {
		if(Character.isUpperCase(board[x-'1'][y-'A'])) {
			return true;
			
		}
		return false;
	}



}

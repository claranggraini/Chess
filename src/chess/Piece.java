package chess;

public class Piece {
	
	protected String color;
	
	public Piece() {
		
	}
	
	protected void move(String color, char board[][], char x, char y) {
		if(board[x-'1'][y-'A'] == 'P') {
			System.out.println(color + " This is a pawn");
		}
	}
	
}

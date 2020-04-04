package chess;

public class Pawn extends Piece{

	Check check = new Check();
	
	public Pawn(String color, char[][] board, int fromRank, int fromFile, int toRank, int toFile) {
		super(color, board, fromRank, fromFile, toRank, toFile);
		
	}
	
	@Override
	void checkPiece() throws Exception {
		if(color.equals("White")) {
			if(board[toRank][toFile] == ' ' && valWhite()) {
				move();
			}else if(board[toRank][toFile] != ' '){
				if(Math.abs(fromFile-toFile) == 1 && fromRank-toRank == -1) {
					eat();
				}else {
					throw new Exception("Move is invalid");
				}
			}else {
				throw new Exception("Move is invalid");
			}
		}else if(color.equals("Black")) {
			if(board[toRank][toFile] == ' ' && valBlack()) {
				move();
			}else if(board[toRank][toFile] != ' '){
				if(Math.abs(fromFile-toFile) == 1 && fromRank-toRank == 1) {
					eat();
				}else {
					throw new Exception("Move is invalid");
				}
			}else {
				throw new Exception("Move is invalid");
			}
		}
		
	}


	public void move() {
		if(color.equals("White")) {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'p';
		}else {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'P';
		}
	}

	public void eat() {
		move();
	}
	
	public boolean valWhite() {
		if(check.isChecked(color, board, fromRank, fromFile, toRank, toFile))
			return false;
		if(board[fromRank+1][fromFile] == ' ' && fromFile == toFile) {
			if(fromRank-toRank == -1) return true;
			else if(fromRank-toRank == -2 && fromRank == 1) return true;
		}
		return false;
	}

	public boolean valBlack() {
		if(check.isChecked(color, board, fromRank, fromFile, toRank, toFile))
			return false;
		if(board[fromRank-1][fromFile] == ' ' && fromFile == toFile) {
			if(fromRank-toRank == 1) return true;
			else if(fromRank-toRank == 2 && fromRank == 6) return true;
		}
		return false;
	}
}

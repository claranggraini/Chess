package chess;

public class Bishop extends Piece {

	Check check = new Check();
	
	public Bishop(String color, char[][] board, int fromRank, int fromFile, int toRank, int toFile) {
		super(color, board, fromRank, fromFile, toRank, toFile);
		
	}
	@Override
	void checkPiece() throws Exception {
		if(board[toRank][toFile] == ' ' && valBishop()) {
			move();
		}else if(board[toRank][toFile] != ' ' && valBishop()) {
			eat();
		}else {
			throw new Exception("Move is invalid");
		}
	}
	
	public boolean valBishop() {
		int row, col;
		if(check.isChecked(color, board, fromRank, fromFile, toRank, toFile))
			return false;
		if(fromRank == toRank || fromFile == toFile) return false;	
		if(Math.abs(toRank - fromRank) != Math.abs(toFile - fromFile)) return false;
		if(fromRank < toRank){
			row = 1;
		}else{
			row = -1;
		}			
		if(fromFile < toFile){
			col = 1;
		}else{
			col = -1;
		}
		
		int y = fromFile + col;
		for(int x = fromRank + row; x != toRank; x += row){
			if(board[x][y] != ' '){
				return false;
			}
			y += col;
		}	
		return true;			
	}
	@Override
	public void move() {
		if(color.equals("White")) {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'b';
		}else {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'B';
		}
	}
	@Override
	public void eat() {
		System.out.println("BISHOP HAS EAT");
		move();
	}
	
}

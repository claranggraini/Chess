package chess;

public class Rook extends Piece{

	Check check = new Check();
	int flag = 0;
	
	public Rook(String color, char[][] board, int fromRank, int fromFile, int toRank, int toFile) {
		super(color, board, fromRank, fromFile, toRank, toFile);
		
	}

	@Override
	void checkPiece() throws Exception {
		if(board[toRank][toFile] == ' ' && valRook()) {
			move();
		}else if(board[toRank][toFile] != ' ' && valRook()) {
			eat();
		}else {
			throw new Exception("Move is invalid");
		}
	}
	
	public boolean valRook(){
		if(check.isChecked(color, board, fromRank, fromFile, toRank, toFile))
			return false;
		if(fromRank == toRank) {
			if(toFile > fromFile) {
				for(int col = fromFile; col < toFile; col++) {
					if(board[toRank][col]!=' ' && col !=fromFile) return false;
				}
			}else if (toFile < fromFile) {
				for(int col = fromFile; col > toFile; col--) {
					if(board[toRank][col]!=' ' && col !=fromFile) return false;
				}
			}
		}else if(fromFile == toFile) {
			if(toRank < fromRank) {
				for(int row = fromRank; row > toRank; row--) {
					if(board[row][toFile] != ' ' && row!=fromRank) return false;
				}
			}else if(toRank > fromRank) {
				for(int row = fromRank; row < toRank; row++) {
					if(board[row][toFile] != ' ' && row!=fromRank) return false;
				}
			}
		}else if(fromRank!=toRank && fromFile!=toFile) return false;
		return true;
	}
	
	@Override
	public void move() {
		flag = 1;
		if(color.equals("White")) {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'r';
		}else {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'R';
		}
	}
	
	@Override
	public void eat() {
		System.out.println("ROOK HAS EAT");
		move();
	}
	
	public boolean checkMove() {
		if(flag == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

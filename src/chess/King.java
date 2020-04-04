package chess;

public class King extends Piece {

	Check check = new Check();
	int flag = 0;
	
	public King(String color, char[][] board, int fromRank, int fromFile, int toRank, int toFile) {
		super(color, board, fromRank, fromFile, toRank, toFile);

	}

	@Override
	void checkPiece() throws Exception {
		if((toRank == 0 || toRank == 7) && toFile == 6
				&& Board.canKingsideCastling(color, board, toRank, toFile)) {
			kingsideCastling();
		} else if((toRank == 0 || toRank == 7) && toFile == 2
				&& Board.canQueensideCastling(color, board, toRank, toFile)) {
			queensideCastling();
		} else if (board[toRank][toFile] == ' ' && valKing()) {
			move();
		} else if (board[toRank][toFile] != ' ' && valKing()) {
			eat();
		} else {
			throw new Exception("Move is invalid");
		}
	}

	public boolean valKing() {
		if(check.isChecked(color, board, fromRank, fromFile, toRank, toFile))
			return false;
		if (Math.abs(toRank - fromRank) == 1 && Math.abs(toFile - fromFile) == 1)
			return true;
		else if (Math.abs(toRank - fromRank) == 1 && fromFile == toFile)
			return true;
		else if (Math.abs(toFile - fromFile) == 1 && fromRank == toRank)
			return true;
		return false;
	}

	@Override
	public void move() {
		flag = 1;
		if (color.equals("White")) {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'k';
		} else {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'K';
		}
	}

	@Override
	public void eat() {
		System.out.println("KING HAS EAT");
		move();
	}
	
	public void kingsideCastling() {
		flag = 1;
		System.out.println("CASTLING");
		if (color.equals("White")) {
			board[0][4] = ' ';
			board[0][5] = 'r';
			board[0][6] = 'k';
			board[0][7] = ' ';
		} else {
			board[7][4] = ' ';
			board[7][5] = 'R';
			board[7][6] = 'K';
			board[7][7] = ' ';
		}
	}
	
	public void queensideCastling() {
		System.out.println("CASTLING");
		if (color.equals("White")) {
			board[0][4] = ' ';
			board[0][3] = 'r';
			board[0][2] = 'k';
			board[0][0] = ' ';
		} else {
			board[7][4] = ' ';
			board[7][3] = 'R';
			board[7][2] = 'K';
			board[7][0] = ' ';
		}
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

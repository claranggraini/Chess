package chess;

public class King extends Piece{

	public King(String color, char[][] board, int fromRank, int fromFile, int toRank, int toFile) {
		super(color, board, fromRank, fromFile, toRank, toFile);
		
	}

	@Override
	void checkPiece() throws Exception {
		if(board[toRank][toFile] == ' ' && valKing()) {
			move();
		}else if(board[toRank][toFile] != ' ' && valKing()) {
			eat();
		}else {
			throw new Exception("Move is invalid");
		} 
	}
	
	public boolean valKing() {
		if(Math.abs(toRank-fromRank)==1 && Math.abs(toFile-fromFile)==1) return true;
		else if(Math.abs(toRank-fromRank)==1 && fromFile == toFile) return true;
		else if(Math.abs(toFile-fromFile)==1 && fromRank == toRank) return true;
		return false;
	}
	
	@Override
	public void move() {
		if(color.equals("White")) {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'k';
		}else {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'K';
		}
	}
	
	@Override
	public void eat() {	
		ChessPieceList cpl = new ChessPieceList(color, board[toRank][toFile]);
		cpl.addToPromoteList();
		System.out.println("KING HAS EAT");
		move();
	}
	
}

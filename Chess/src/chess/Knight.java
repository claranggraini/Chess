package chess;

public class Knight extends Piece{

	public Knight(String color, char[][] board, int fromRank, int fromFile, int toRank, int toFile) {
		super(color, board, fromRank, fromFile, toRank, toFile);
		
	}
	@Override
	void checkPiece() throws Exception {
		if(board[toRank][toFile] == ' ' && valKnight()) {
			move();
		}else if(board[toRank][toFile] != ' ' && valKnight()){
			eat();
		}else {
			throw new Exception("Move is invalid");
		}
		
	}
	
	public boolean valKnight() {
		if(Math.abs(toRank-fromRank) == 1 && Math.abs(toFile-fromFile) == 2) return true;
		else if(Math.abs(toRank-fromRank) == 2 && Math.abs(toFile - fromFile) == 1) return true;
		return false;		
	}
	@Override
	public void move() {
		if(color.equals("White")) {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'n';
		}else {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'N';
		}
	}
	@Override
	public void eat() {
		ChessPieceList cpl = new ChessPieceList(color, board[toRank][toFile]);
		cpl.addToPromoteList();
		System.out.println("KNIGHT HAS EAT");
		move();
	}
	
}

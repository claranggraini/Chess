package chess;

public class Queen extends Piece {

	public Queen(String color, char[][] board, int fromRank, int fromFile, int toRank, int toFile) {
		super(color, board, fromRank, fromFile, toRank, toFile);
		
	}
	
	Rook r = new Rook(color, board, fromRank, fromFile, toRank, toFile);
	Bishop bp = new Bishop(color, board, fromRank, fromFile, toRank, toFile);
	
	@Override
	void checkPiece() throws Exception {
		if(board[toRank][toFile] == ' ' && (r.valRook()||bp.valBishop())) {
			move();
		}else if(board[toRank][toFile] != ' ' && (r.valRook()||bp.valBishop())) {
			eat();
		}else {
			throw new Exception("Move is invalid");
		} 
	}
	
	@Override
	public void move() {
		if(color.equals("White")) {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'q';
		}else {
			board[fromRank][fromFile] = ' ';
			board[toRank][toFile] = 'Q';
		}
	}
	
	@Override
	public void eat() {
		ChessPieceList cpl = new ChessPieceList(color, board[toRank][toFile]);
		cpl.addToPromoteList();
		System.out.println("QUEEN HAS EAT");
		move();
	}
}

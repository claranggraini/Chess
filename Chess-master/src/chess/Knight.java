package chess;

public class Knight extends Piece{

	public Knight(int rank, int file, String color) {
		super(rank, file, color);
		if(color.equals("White")) {
			this.id = 'n';
		}else {
			this.id = 'N';
		}
		pieceList.add(this);
	}

	@Override
	public void checkPiece(Piece[][] board, int toRank, int toFile) throws Exception {
		if(valPiece(board, toRank, toFile)) {
			move(board, toRank, toFile);
		}else {
			throw new Exception("Move is invalid");
		}
		
	}


	@Override
	public boolean valPiece(Piece[][]board, int toRank, int toFile) {
		if(Math.abs(toRank-rank) == 1 && Math.abs(toFile-file) == 2) return true;
		else if(Math.abs(toRank-rank) == 2 && Math.abs(toFile - file) == 1) return true;
		return false;		
	}
	
}

package chess;

public class Queen extends Piece{

	public Queen(int rank, int file, String color) {
		super(rank, file, color);
		if(color.equals("White")) {
			this.id = 'q';
		}else {
			this.id = 'Q';
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
	public boolean valPiece(Piece[][] board, int toRank, int toFile) {
		Rook r = new Rook(rank,file,color);
		pieceList.remove(pieceList.size()-1);
		Bishop bp = new Bishop(rank,file,color);
		pieceList.remove(pieceList.size()-1);
		if(r.valPiece(board, toRank, toFile) || bp.valPiece(board, toRank, toFile)) {
			
			return true;
		}
		return false;
	}
	
	
	

	
}

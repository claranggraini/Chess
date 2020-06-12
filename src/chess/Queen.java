package chess;

public class Queen extends Piece {

	public Queen(int rank, int file, String color) {
		super(rank, file, color);
		if (color.equals("White")) {
			this.id = 'q';
		} else {
			this.id = 'Q';
		}
	}

	@Override
	public boolean valPiece(Piece[][] board, int toRank, int toFile) {
		if(outOfBound(toRank, toFile)) return false;
		Rook r = new Rook(rank, file, color);
		Bishop bp = new Bishop(rank, file, color);
		if (r.valPiece(board, toRank, toFile) || bp.valPiece(board, toRank, toFile)) {
			return true;
		}
		return false;
	}
}
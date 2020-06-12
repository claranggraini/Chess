package chess;

public class Rook extends Piece {

	private boolean hasMoved;

	public Rook(int rank, int file, String color) {
		super(rank, file, color);
		if (color.equals("White")) {
			this.id = 'r';
		} else {
			this.id = 'R';
		}
		this.hasMoved = false;
	}

	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	@Override
	public void checkPiece(Piece[][] board, int toRank, int toFile) throws Exception {
		if (valPiece(board, toRank, toFile)) {
			move(board, toRank, toFile);
			lastMove = this;
			hasMoved = true;
		} else {
			throw new Exception("Move is invalid");
		}
	}

	@Override
	public boolean valPiece(Piece[][] board, int toRank, int toFile) {
		if(outOfBound(toRank, toFile)) return false;
		if (file != toFile && rank != toRank) return false;

		int x = rank < toRank || file < toFile ? 1 : -1;

		if (rank == toRank) {
			for (int i = file + x; i != toFile; i += x) {
				if (board[rank][i] != null) return false;
			}
		} else if (file == toFile) {
			for (int i = rank + x; i != toRank; i += x) {
				if (board[i][file] != null) return false;
			}
		}

		return true;
	}

}
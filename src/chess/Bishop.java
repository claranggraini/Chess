package chess;

public class Bishop extends Piece {

	public Bishop(int rank, int file, String color) {
		super(rank, file, color);
		if (color.equals("White")) {
			this.id = 'b';
		} else {
			this.id = 'B';
		}
	}

	@Override
	public boolean valPiece(Piece[][] board, int toRank, int toFile) {
		if(outOfBound(toRank, toFile)) return false;
		if (Math.abs(toRank - rank) != Math.abs(toFile - file)) return false;

		int row = rank < toRank ? 1 : -1;
		int col = file < toFile ? 1 : -1;

		int y = file + col;
		for (int x = rank + row; x != toRank; x += row) {
			if (board[x][y] != null) {
				return false;
			}
			y += col;
		}
		return true;
	}
	
}
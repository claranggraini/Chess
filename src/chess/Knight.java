package chess;

public class Knight extends Piece {

	public Knight(int rank, int file, String color) {
		super(rank, file, color);
		if (color.equals("White")) {
			this.id = 'n';
		} else {
			this.id = 'N';
		}
	}

	@Override
	public boolean valPiece(Piece[][] board, int toRank, int toFile) {
		if(outOfBound(toRank, toFile)) return false;
		if (Math.abs(toRank - rank) == 1 && Math.abs(toFile - file) == 2) return true;
		else if (Math.abs(toRank - rank) == 2 && Math.abs(toFile - file) == 1) return true;
		
		return false;
	}
	
}

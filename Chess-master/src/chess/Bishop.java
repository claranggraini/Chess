package chess;

public class Bishop extends Piece{

	public Bishop(int rank, int file, String color) {
		super(rank, file, color);
		if(color.equals("White")) {
			this.id = 'b';
		}else {
			this.id = 'B';
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
		int row, col;
		if(rank == toRank || file == toFile) return false;	
		if(Math.abs(toRank - rank) != Math.abs(toFile - file)) return false;
		if(rank < toRank){
			row = 1;
		}else{
			row = -1;
		}			
		if(file < toFile){
			col = 1;
		}else{
			col = -1;
		}
		int y = file + col;
		for(int x = rank + row; x != toRank; x += row){
			if(board[x][y] != null){
				return false;
			}
			y += col;
			
		}
		return true;			
	}
	
	
}

package chess;

public class King extends Piece {

	private boolean isCheck;
	private boolean hasMoved;
	private Check checker;

	public King(int rank, int file, String color) {
		super(rank, file, color);
		if (color.equals("White")) {
			this.id = 'k';
		} else {
			this.id = 'K';
		}
		this.isCheck = false;
		this.hasMoved = false;
		pieceList.add(this);
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	@Override
	public void checkPiece(Piece[][] board, int toRank, int toFile) throws Exception {
		if (Math.abs(toFile - file) == 2 && castling(board, toFile)) {
			System.out.println(color + " CASTLING");
		} else if (valPiece(board, toRank, toFile)) {
			move(board, toRank, toFile);
			hasMoved = true;
		} else {
			throw new Exception("Move is invalid");
		}
	}

	@Override
	public boolean valPiece(Piece[][] board, int toRank, int toFile) {
		if (Math.abs(toRank - rank) == 1 && Math.abs(toFile - file) == 1)
			return true;
		else if (Math.abs(toRank - rank) == 1 && file == toFile)
			return true;
		else if (Math.abs(toFile - file) == 1 && rank == toRank)
			return true;
		return false;
	}

	private boolean castling(Piece[][] board, int toFile) {
		checker = new Check(board);
		int x = toFile < file ? -2 : 1;
		if (isCheck || !board[rank][toFile + x].getClass().equals(Rook.class))
			return false;
		if (hasMoved || ((Rook) board[rank][toFile + x]).isHasMoved())
			return false;
		x = x == -2 ? -1 : 1;
		if (x == -1 && board[rank][toFile + x] != null)
			return false;
		for (int temp = file + x; file != toFile; temp += x) {
			if (board[this.rank][temp] != null)
				return false;
			move(board, rank, temp);
			if (checker.check(color)) {
				isCheck = false;
				move(board, rank, temp - x);
				return false;
			} else if (file == toFile) {
				if (x == 1) {
					((Rook) board[rank][toFile + x]).move(board, rank, temp - x);
				} else {
					((Rook) board[rank][toFile - 2]).move(board, rank, temp - x);
				}
			}
		}
		return true;
	}

}

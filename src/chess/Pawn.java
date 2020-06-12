package chess;

public class Pawn extends Piece {

	private boolean isDoublePushed;
	private char toPromote;

	public char getToPromote() {
		return toPromote;
	}

	public void setToPromote(char toPromote) {
		this.toPromote = toPromote;
	}

	public Pawn(int rank, int file, String color) {
		super(rank, file, color);
		if (color.equals("White")) {
			this.id = 'p';
		} else {
			this.id = 'P';
		}
		this.isDoublePushed = false;
		this.toPromote = ' ';
	}

//	public boolean isDoublePushed() {
//		return isDoublePushed;
//	}
//
//	public void setDoublePushed(boolean isDoublePushed) {
//		this.isDoublePushed = isDoublePushed;
//	}

	@Override
	public void checkPiece(Piece[][] board, int toRank, int toFile) throws Exception {
		if (toRank - rank > 0 && !color.equals("White")) {
			throw new Exception("Move is invalid");
		} else if (toRank - rank < 0 && !color.equals("Black")) {
			throw new Exception("Move is invalid");
		} else if (valPiece(board, toRank, toFile)) {
			move(board, toRank, toFile);
			lastMove = this;
			if (validatePromotion(toRank)) {
				promote(board, toRank, toFile);
			}
		} else {
			throw new Exception("Move is invalid");
		}
	}

	public boolean valPiece(Piece[][] board, int toRank, int toFile) {
		int i = color.equals("White") ? 1 : -1;
		
		if(outOfBound(toRank, toFile)) return false;
		if (file == toFile && board[toRank][toFile] == null) {
			return simpleMove(board, toRank);
		} else if (Math.abs(file - toFile) == 1 && toRank - rank == i && board[toRank][toFile] != null) {
			return true;
		} else if (Math.abs(file - toFile) == 1 && toRank - rank == i && board[toRank][toFile] == null) {
			return enPassant(board, toFile);
		}
		return false;
	}

	private boolean simpleMove(Piece[][] board, int toRank) {
		int i = color.equals("White") ? 1 : -1;
		int oriRank = color.equals("White") ? 1 : 6;

		if (validatePromotion(toRank) && toPromote == ' ')
			return false;
		if (Math.abs(toRank - rank) == 2 && board[rank + i][file] == null && rank == oriRank) {
			isDoublePushed = true;
			return true;
		} else if (toRank - rank == i) {
			isDoublePushed = false;
			return true;
		}
		return false;
	}

	private boolean enPassant(Piece[][] board, int toFile) {
		int i = toFile < file ? -1 : 1;

		if (lastMove == null || board[rank][file + i] == null)
			return false;
		if (board[rank][file + i].getClass().equals(Pawn.class) && lastMove.equals(board[rank][file + i])) {
			Pawn p = (Pawn) board[rank][file + i];
			if (p.isDoublePushed == true) {
				board[rank][file + i] = null;
				return true;
			}
		}
		return false;
	}

	private void promote(Piece[][] board, int toRank, int toFile) {
		if (toPromote == 'Q') {
			Queen q = new Queen(toRank, toFile, color);
			board[toRank][toFile] = q;
		} else if (toPromote == 'B') {
			Bishop bp = new Bishop(toRank, toFile, color);
			board[toRank][toFile] = bp;
		} else if (toPromote == 'R') {
			Rook r = new Rook(toRank, toFile, color);
			board[toRank][toFile] = r;
		} else if (toPromote == 'N') {
			Knight n = new Knight(toRank, toFile, color);
			board[toRank][toFile] = n;
		}
	}

	public boolean validatePromotion(int toRank) {
		if (color.equals("White") && toRank == 7) return true;
		else if (color.equals("Black") && toRank == 0) return true;
		
		return false;
	}

}

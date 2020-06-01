package chess;

import java.util.Vector;

public class Check {

	private Piece[][] board;
	private static Vector<Piece> pieceList = Piece.getPieceList();
	private Vector<Piece> attackerList = new Vector<Piece>();
	private boolean[][] attackedSquare = new boolean[8][8];

	public Check(Piece[][] board) {
		this.board = board;
	}

	public boolean check(String color) {
		Piece piece;
		King king = getKing(color);
		for (int i = 0; i < pieceList.size(); i++) {
			if (!pieceList.get(i).getColor().equals(color)) {
				piece = pieceList.get(i);
				if (piece.valPiece(board, king.getRank(), king.getFile())) {
					king.setCheck(true);
					return true;
				}
			}
		}
		king.setCheck(false);
		return false;
	}

	private King getKing(String color) {
		if (color.equals("White")) {
			return (King) pieceList.get(0);
		}
		return (King) pieceList.get(1);
	}

	public void reCheck(Piece piece, String color, int fromRank, int fromFile) throws Exception {
		King king = getKing(color);
		if (!king.isCheck() && check(color)) {
			putsBack(piece, fromRank, fromFile);
			king.setCheck(false);
			throw new Exception("Invalid move: King was exposed");
		} else if (king.isCheck() && check(color)) {
			putsBack(piece, fromRank, fromFile);
			throw new Exception("Invalid move: King is in check");
		}
	}

	private void putsBack(Piece piece, int fromRank, int fromFile) {
		int rank = piece.getRank();
		int file = piece.getFile();
		board[fromRank][fromFile] = piece;
		board[rank][file] = piece.getEatenPiece();
		piece.setRank(fromRank);
		piece.setFile(fromFile);
	}

	public boolean gameOver(String color) {
		if (isCheckmate(color)) {
			if (color == "White")
				System.out.println("BLACK WIN");
			else
				System.out.println("WHITE WIN");
			return true;
		} else if (isStalematePosition(color)) {
			System.out.println("DRAW");
			return true;
		}
		return false;
	}

	private boolean isCheckmate(String color) {
		if (!check(color)) {
			return false;
		}

		countAttacker(color);

		if (attackerList.size() == 1) {
			return singleCheck(color);
		} else {
			return doubleCheck(color);
		}
	}

	private void countAttacker(String color) {
		Piece piece;
		King king = getKing(color);

		attackerList.removeAll(attackerList);
		for (int i = 0; i < pieceList.size(); i++) {
			if (!pieceList.get(i).getColor().equals(color)) {
				piece = pieceList.get(i);
				if (piece.valPiece(board, king.getRank(), king.getFile())) {
					attackerList.add(piece);
				}
			}
		}
	}

	private boolean singleCheck(String color) {
		if (canEatAttacker(color)) {
			return false;
		}

		if (canBlock(color)) {
			return false;
		}

		return doubleCheck(color);
	}

	private boolean canEatAttacker(String color) {
		Piece piece;
		Piece attacker = attackerList.get(0);

		for (int i = 0; i < pieceList.size(); i++) {
			if (pieceList.get(i).getColor().equals(color)) {
				piece = pieceList.get(i);
				if (piece.valPiece(board, attacker.getRank(), attacker.getFile())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canBlock(String color) {
		Piece piece;
		King king = getKing(color);
		Piece attacker = attackerList.get(0);

		int betweenRank = king.getRank() - attacker.getRank();
		int betweenFile = king.getFile() - attacker.getFile();
		do {
			if (betweenRank < 0) {
				betweenRank++;
			}
			if (betweenRank > 0) {
				betweenRank--;
			}
			if (betweenFile < 0) {
				betweenFile++;
			}
			if (betweenFile > 0) {
				betweenFile--;
			}

			for (int i = 0; i < pieceList.size(); i++) {
				if (pieceList.get(i).getColor().equals(color)) {
					piece = pieceList.get(i);
					if (piece.valPiece(board, betweenRank, betweenFile)) {
						return true;
					}
				}
			}
		} while (betweenRank != 0 && betweenFile != 0);

		return false;
	}

	private boolean doubleCheck(String color) {
		King king = getKing(color);

		listAttackedSquare(color);

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				try {
					if (attackedSquare[king.getRank() + i][king.getFile() + j] == false) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException ignored) {
				}
			}
		}

		System.out.println("double check");
		return true;
	}

	private boolean isStalematePosition(String color) {
		if (check(color)) {
			return false;
		}
		if (hasLegalMove(color)) {
			return false;
		}
		return true;
	}

	public boolean hasLegalMove(String color) {
		Piece piece;

		listAttackedSquare(color);

		for (int i = 0; i < pieceList.size(); i++) {
			if (pieceList.get(i).getColor().equals(color)) {
				piece = pieceList.get(i);
				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < 8; k++) {
						if (piece.getRank() == j && piece.getFile() == k) {
							continue;
						}
						if (!(piece instanceof King) && piece.valPiece(board, j, k)) {
							try {
								if(!board[j][k].getColor().equals(color)) {
									System.out.println(piece + " " + j + " " + k);
									return true;
								}
							} catch (Exception ignored) {
							}
						}
						if (piece.valPiece(board, j, k) && attackedSquare[j][k] == false) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private void listAttackedSquare(String color) {
		Piece attacker;
		King king = getKing(color);

		resetAttackedSquare();

		for (int i = 0; i < pieceList.size(); i++) {
			if (!pieceList.get(i).getColor().equals(color)) {
				attacker = pieceList.get(i);
				if (king.valPiece(board, attacker.getRank(), attacker.getFile())) {
					continue;
				}
				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < 8; k++) {
						if (attacker.valPiece(board, j, k)) {
							attackedSquare[j][k] = true;
						}
					}
				}
			}
		}
	}

	private void resetAttackedSquare() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				attackedSquare[i][j] = false;
			}
		}
	}
}

package chess;

public class Check {

	public boolean isChecked(String color, char board[][], int fromRank, int fromFile, int toRank, int toFile) {
		int rank = 0;
		int file = 0;
		boolean flag = false;
		char[][] tempBoard = new char[12][12];

		for (int i = 0; i <= 11; i++) {
			for (int j = 0; j <= 11; j++) {
				tempBoard[i][j] = ' ';
			}
		}

		for (int i = 9; i >= 2; i--) {
			for (int j = 9; j >= 2; j--) {
				tempBoard[i][j] = board[i - 2][j - 2];
			}
		}

		if (fromRank != 10) {
			tempBoard[toRank + 2][toFile + 2] = tempBoard[fromRank + 2][fromFile + 2];
			tempBoard[fromRank + 2][fromFile + 2] = ' ';
		}
		
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (tempBoard[i][j] == 'k' && color.equals("White")) {
					rank = i;
					file = j;
					flag = true;
					break;
				}
				if (tempBoard[i][j] == 'K' && color.equals("Black")) {
					rank = i;
					file = j;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		/* Check straight lines */
		for (int i = rank + 1; i <= 11; i++) { // up
			if (color.equals("White")) {
				if (tempBoard[i][file] == 'R' || tempBoard[i][file] == 'Q')
					return true;
				else if (isPiece(i, file, tempBoard))
					break;
			} else {
				if (tempBoard[i][file] == 'r' || tempBoard[i][file] == 'q')
					return true;
				else if (isPiece(i, file, tempBoard))
					break;
			}
		}

		for (int i = rank - 1; i >= 0; i--) { // down
			if (color.equals("White")) {
				if (tempBoard[i][file] == 'R' || tempBoard[i][file] == 'Q')
					return true;
				else if (isPiece(i, file, tempBoard))
					break;
			} else {
				if (tempBoard[i][file] == 'r' || tempBoard[i][file] == 'q')
					return true;
				else if (isPiece(i, file, tempBoard))
					break;
			}
		}

		for (int i = file + 1; i <= 11; i++) { // right
			if (color.equals("White")) {
				if (tempBoard[rank][i] == 'R' || tempBoard[rank][i] == 'Q')
					return true;
				else if (isPiece(rank, i, tempBoard))
					break;
			} else {
				if (tempBoard[rank][i] == 'r' || tempBoard[rank][i] == 'q')
					return true;
				else if (isPiece(rank, i, tempBoard))
					break;
			}
		}

		for (int i = file - 1; i >= 0; i--) { // left
			if (color.equals("White")) {
				if (tempBoard[rank][i] == 'R' || tempBoard[rank][i] == 'Q')
					return true;
				else if (isPiece(rank, i, tempBoard))
					break;
			} else {
				if (tempBoard[rank][i] == 'r' || tempBoard[rank][i] == 'q')
					return true;
				else if (isPiece(rank, i, tempBoard))
					break;
			}
		}

		/* Check diagonals */
		for (int i = rank + 1, j = file + 1; i <= 11 && j <= 11; i++, j++) { // right-up
			if (color.equals("White")) {
				if (tempBoard[i][j] == 'B' || tempBoard[i][j] == 'Q')
					return true;
				else if (isPiece(i, j, tempBoard))
					break;
			} else {
				if (tempBoard[i][j] == 'b' || tempBoard[i][j] == 'q')
					return true;
				else if (isPiece(i, j, tempBoard))
					break;
			}
		}

		for (int i = rank + 1, j = file - 1; i <= 11 && j >= 0; i++, j--) { // left-up
			if (color.equals("White")) {
				if (tempBoard[i][j] == 'B' || tempBoard[i][j] == 'Q')
					return true;
				else if (isPiece(i, j, tempBoard))
					break;
			} else {
				if (tempBoard[i][j] == 'b' || tempBoard[i][j] == 'q')
					return true;
				else if (isPiece(i, j, tempBoard))
					break;
			}
		}

		for (int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) { // left-down
			if (color.equals("White")) {
				if (tempBoard[i][j] == 'B' || tempBoard[i][j] == 'Q')
					return true;
				else if (isPiece(i, j, tempBoard))
					break;
			} else {
				if (tempBoard[i][j] == 'b' || tempBoard[i][j] == 'q')
					return true;
				else if (isPiece(i, j, tempBoard))
					break;
			}
		}

		for (int i = rank - 1, j = file + 1; i >= 0 && j <= 11; i--, j++) { // right-down
			if (color.equals("White")) {
				if (tempBoard[i][j] == 'B' || tempBoard[i][j] == 'Q')
					return true;
				else if (isPiece(i, j, tempBoard))
					break;
			} else {
				if (tempBoard[i][j] == 'b' || tempBoard[i][j] == 'q')
					return true;
				else if (isPiece(i, j, tempBoard))
					break;
			}
		}

		/* Check pawns */
		if (color.equals("White")) {
			if (tempBoard[rank + 1][file + 1] == 'P' || tempBoard[rank + 1][file - 1] == 'P')
				return true;
		} else {
			if (tempBoard[rank - 1][file + 1] == 'p' || tempBoard[rank - 1][file - 1] == 'p')
				return true;
		}

		/* Check king */
		if (color.equals("White")) {
			if (tempBoard[rank - 1][file + 1] == 'K' || tempBoard[rank][file + 1] == 'K'
					|| tempBoard[rank + 1][file + 1] == 'K' || tempBoard[rank + 1][file] == 'K'
					|| tempBoard[rank + 1][file - 1] == 'K' || tempBoard[rank][file - 1] == 'K'
					|| tempBoard[rank - 1][file - 1] == 'K' || tempBoard[rank - 1][file] == 'K')
				return true;
		} else {
			if (tempBoard[rank - 1][file + 1] == 'k' || tempBoard[rank][file + 1] == 'k'
					|| tempBoard[rank + 1][file + 1] == 'k' || tempBoard[rank + 1][file] == 'k'
					|| tempBoard[rank + 1][file - 1] == 'k' || tempBoard[rank][file - 1] == 'k'
					|| tempBoard[rank - 1][file - 1] == 'k' || tempBoard[rank - 1][file] == 'k')
				return true;
		}

		/* Check knights */
		if (color.equals("White")) {
			if (tempBoard[rank - 2][file + 1] == 'N' || tempBoard[rank - 1][file + 2] == 'N'
					|| tempBoard[rank + 1][file + 2] == 'N' || tempBoard[rank + 2][file + 1] == 'N'
					|| tempBoard[rank + 2][file - 1] == 'N' || tempBoard[rank + 1][file - 2] == 'N'
					|| tempBoard[rank - 1][file - 2] == 'N' || tempBoard[rank - 2][file - 1] == 'N')
				return true;
		} else {
			if (tempBoard[rank - 2][file + 1] == 'n' || tempBoard[rank - 1][file + 2] == 'n'
					|| tempBoard[rank + 1][file + 2] == 'n' || tempBoard[rank + 2][file + 1] == 'n'
					|| tempBoard[rank + 2][file - 1] == 'n' || tempBoard[rank + 1][file - 2] == 'n'
					|| tempBoard[rank - 1][file - 2] == 'n' || tempBoard[rank - 2][file - 1] == 'n')
				return true;
		}

		return false;
	}

	public boolean isPiece(int rank, int file, char board[][]) {
		if (Character.isAlphabetic(board[rank][file])) {
			return true;
		}
		return false;
	}
}

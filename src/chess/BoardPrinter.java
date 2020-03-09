package chess;

public class BoardPrinter {

	private Board board;

	public BoardPrinter(Board board) {
		super();
		this.board = board;
	}

	public void print() {
		char[][] boardPrint = board.getBoard();
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				System.out.print(boardPrint[i][j]);
				System.out.print(" ");
			}
			int info = i + 1;
			System.out.println(info);
		}
		System.out.println("A B C D E F G H");
	}
}

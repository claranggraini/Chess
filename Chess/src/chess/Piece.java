package chess;

abstract class Piece {

	protected String color;
	protected int fromRank;
	protected int fromFile;
	protected int toRank;
	protected int toFile;
	protected char board[][];
	

	public Piece(String color, char board[][] ,int fromRank, int fromFile, int toRank, int toFile) {
		this.color = color;
		this.fromRank = fromRank;
		this.fromFile = fromFile;
		this.toRank = toRank;
		this.toFile = toFile;
		this.board = board;
	}

	abstract void checkPiece() throws Exception;

	abstract void move();
	abstract void eat();
	
}

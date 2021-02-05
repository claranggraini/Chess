package chess;

import java.util.Vector;

public abstract class Piece {

	protected int rank;
	protected int file;
	protected char id;
	protected String color;
	protected static Vector <Piece> pieceList = new Vector <Piece>();
	protected static Piece lastMove = null;
	protected Piece eatenPiece;	
	public Piece(int rank, int file, String color) {
		super();
		this.rank = rank;
		this.file = file;
		this.color = color;
		this.eatenPiece = null;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getFile() {
		return file;
	}

	public void setFile(int file) {
		this.file = file;
	}

	public char getId() {
		return id;
	}

	public void setId(char id) {
		this.id = id;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static Vector<Piece> getPieceList() {
		return pieceList;
	}

	public static void setPieceList(Vector<Piece> pieceList) {
		Piece.pieceList = pieceList;
	}

	public Piece getEatenPiece() {
		return eatenPiece;
	}

	public void setEatenPiece(Piece eatenPiece) {
		this.eatenPiece = eatenPiece;
	}

	public abstract void checkPiece(Piece[][] board, int toRank, int toFile) throws Exception;
	protected void move(Piece[][] board, int toRank, int toFile) {
		board[rank][file] = null;
		rank = toRank;
		file = toFile;
		if(Piece.lastMove!=null && !Piece.lastMove.equals(this)) eatenPiece = null;
		if(board[rank][file]!=null) {
			eatenPiece = board[rank][file];
			pieceList.remove(board[rank][file]);
		}
		board[rank][file] = this;
		lastMove = this;
	}
	public abstract boolean valPiece(Piece[][]board, int toRank, int toFile);
}

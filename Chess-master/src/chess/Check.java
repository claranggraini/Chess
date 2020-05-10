package chess;

import java.util.Vector;

public class Check {

	
	private Piece [][] board;
	private static Vector<Piece> pieceList = Piece.getPieceList();
	public Check(Piece [][]board) {
		this.board = board;
	} 
	
	public boolean check(String color) {
		Piece piece;
		King king = getKing(color);
		for(int i=0;i<pieceList.size();i++) {	
			if(!pieceList.get(i).getColor().equals(color)) {	
				piece = pieceList.get(i);
				if(piece.valPiece(board, king.getRank(), king.getFile())) {
					king.setCheck(true);
					return true;
				}
			}
		}
		king.setCheck(false);
		return false;
	}

	public King getKing(String color) {
		if(color.equals("White")) {
			return (King)pieceList.get(0);
		}
		return(King)pieceList.get(1);
	}

	public void reCheck(Piece piece, String color, int fromRank, int fromFile) throws Exception {
		King king = getKing(color);
		if(!king.isCheck() && check(color)) {
			putsBack(piece,fromRank, fromFile);
			king.setCheck(false);
			throw new Exception("Invalid move: King was exposed");
		}else if(king.isCheck() && check(color)){
			putsBack(piece,fromRank, fromFile);
			throw new Exception("Invalid move: King is in check");		
		}
	}
	
	public void putsBack(Piece piece,int fromRank, int fromFile) {
		int rank = piece.getRank();
		int file = piece.getFile();
		board[fromRank][fromFile] = piece;
		board[rank][file] = piece.getEatenPiece();
		piece.setRank(fromRank);
		piece.setFile(fromFile);
	}
}

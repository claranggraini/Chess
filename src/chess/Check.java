package chess;

import java.util.Vector;

public class Check {

	private Piece[][] board;
	private Vector<Piece> pieceList = new Vector <Piece>();
	private Vector<Piece>attackerList = new Vector <Piece>();
	private Boolean attackedSquare[][] = new Boolean[8][8];
	private String color;
	private King king;
	private boolean checkmate;
	private boolean stalemate;
	
	public Check(Piece[][] board, String color) {
		this.board = board;
		this.color = color;
		this.pieceList = listPiecesOnBoard(board);
		resetAttackedSquare();
		this.checkmate = false;
		this.stalemate = false;
	}
	

	public Vector<Piece> listPiecesOnBoard(Piece board[][]) {
		Vector <Piece> pieceList = new Vector<Piece>();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(board[i][j] != null) {
					pieceList.add(board[i][j]);
					if(board[i][j].getClass().equals(King.class) && board[i][j].getColor().equals(color) ) {
						king = (King)board[i][j];
					}
				}
			}
		}
		return pieceList;
	}
	
	public boolean check() {
		Piece piece;
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

	public void reCheck(Piece piece, int fromRank, int fromFile) throws Exception {
		if (!king.isCheck() && check()) {
			putsBack(piece, fromRank, fromFile);
			king.setCheck(false);
			throw new Exception("Invalid move: King was exposed");
		} else if (king.isCheck() && check()) {
			putsBack(piece, fromRank, fromFile);
			throw new Exception("Invalid move: King is in check");
		}
	}

	private void putsBack(Piece piece, int fromRank, int fromFile) {
		int rank = piece.getRank();
		int file = piece.getFile();
		board[fromRank][fromFile] = piece;
		if(piece.getEatenPiece()!=null) {
			board[rank][file] = piece.getEatenPiece();
			pieceList.add(board[rank][file]);
		}else {
			board[rank][file] = null;
		}
		piece.setRank(fromRank);
		piece.setFile(fromFile);
	}
	
	private boolean hasLegalMove() {
		int fromRank = king.getRank();
		int fromFile = king.getFile();
		boolean noThreats = true;
		
		if(king.castling(board, fromFile-2)|| king.castling(board, fromFile+2))return true;
	
		for(int i=fromRank-1;i<=fromRank+1;i++) {
			for(int j=fromFile-1;j<=fromFile+1;j++) {
				
				if(king.outOfBound(i, j)) continue;
				if(board[i][j]==null || !board[i][j].getColor().equals(color)) {
					if(board[i][j]!=null) pieceList.remove(board[i][j]);				
					
					for(int p = 0; p<pieceList.size();p++) {
						Piece piece = pieceList.get(p);
						if(!piece.getColor().equals(color)) {
							if(piece.valPiece(board, i, j)){
								noThreats = false;		
								attackedSquare[i][j]=true;
								if(!attackerList.contains(piece)) attackerList.add(piece);
							}	
						}
						if(p == pieceList.size()-1 && noThreats) {
							attackedSquare = null;
							return true;
						}
					}
				}
			}
		}
		if(noThreats)return true;
		return false;
	}
	
	private boolean canAttack() {
		if(attackerList.isEmpty())return false;
		for(int i=0;i<attackerList.size();i++) {
			Piece attacker = attackerList.get(i);
			
			if(attacker !=null) {
				for(int p=0;p<pieceList.size();p++) {
					if(pieceList.get(p).getColor().equals(color)) {
						Piece piece = pieceList.get(p);
						int fromRank = piece.getRank();
						int fromFile = piece.getFile();
						if(piece.valPiece(board, attacker.getRank(), attacker.getFile())) {
							piece.move(board, attacker.getRank(), attacker.getFile());
							
							if(!check()) {
								putsBack(piece, fromRank, fromFile);
								return true;
							}
							putsBack(piece, fromRank, fromFile);
						}
					}
				}
			}
		}	
		return false;
	}
	
	private boolean canBlock() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				for(int p=0;p<pieceList.size();p++) {
					if(pieceList.get(p).getColor().equals(color)) {
						Piece piece = pieceList.get(p);
						if(attackedSquare[i][j] == true && !piece.getClass().equals(King.class)&&piece.valPiece(board, i, j) ) {
							int fromRank = piece.getRank();
							int fromFile = piece.getFile();
							piece.move(board, i, j);
							if(!check()) {
								putsBack(piece, fromRank, fromFile);
								return true;
							}
							putsBack(piece, fromRank, fromFile);
						}
					}			
				}
			}
		}
		return false;
	}
	
	private void gameStatus() {
		check();
		
		if(king.isCheck() && !hasLegalMove() && !canBlock() && !canAttack()) {
			checkmate = true;			
		}else if(!king.isCheck() && !hasLegalMove() &&!canBlock() && !canAttack()) {
			
			stalemate = true;
		}
		attackerList.clear();
	}
	
	private void resetAttackedSquare() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				attackedSquare[i][j] = false;
			}
		}
	}
	
	public boolean gameOver() {
		gameStatus();
		if (checkmate) {
			if (color.equals("White"))
				System.out.println("BLACK WIN");
			else
				System.out.println("WHITE WIN");
			return true;
		} else if (stalemate) {
			System.out.println("DRAW");
			return true;
		}
		return false;
	}
}
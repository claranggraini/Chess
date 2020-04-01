package chess;

import java.util.Vector;

public class ChessPieceList {

	Vector<Object> whiteVec = new Vector<>();
	Vector<Object> blackVec = new Vector<>();
	private String color;
	private char id;
	
	public ChessPieceList(String color, char id) {
		this.color = color;
		this.id = id;
	}
	
	public void addToPromoteList() {
		if(id!='p' || id!='P') {
			if(color.equals("White")) {
				whiteVec.add(id);
			}else if(color.equals("Black")) {
				blackVec.add(id);
			}
		}	
	}
	

}

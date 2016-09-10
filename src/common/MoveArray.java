package common;

import java.util.ArrayList;

import standard.StandardCoordinate;

public class MoveArray {

	private ArrayList<Move> moveArray = new ArrayList<Move>();
	
	public MoveArray(ArrayList<Move> moves) {
		moveArray = moves;
	}
	
	public ArrayList<ChessCoordinate> getToList() {
		ArrayList<ChessCoordinate> toList = new ArrayList<ChessCoordinate>();
		
		for (Move m: moveArray) {
			toList.add(m.to);
		}
		
		return toList;
	}
	
	public static ArrayList<Move> removeKingMoves(ChessPlayerColor kingColor, ChessCoordinate kingLoc, ArrayList<Move> moves, boolean canKingMove) {
		ArrayList<Move> newMoves = new ArrayList<Move>();
		
		for (Move m: moves) {
			StandardCoordinate c = new StandardCoordinate(m.from);
			if (!c.equals(kingLoc)) {
				newMoves.add(m);
			}
		}
		
		return newMoves;
	}
	
}

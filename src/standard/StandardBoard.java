package standard;

import java.util.HashMap;

import common.ChessCoordinate;

public class StandardBoard {

	private HashMap<ChessCoordinate, StandardPiece> board;
	
	public StandardBoard() {
		board = new HashMap<ChessCoordinate, StandardPiece>();
	}
	
	public void movePiece(StandardPiece piece, ChessCoordinate to) {
		board.put(to, piece);
	}
	
	public StandardPiece getPiece(ChessCoordinate from) {
		return board.get(from);
	}
	
}

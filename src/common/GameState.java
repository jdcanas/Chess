package common;

public class GameState {
	private ChessPlayerColor movesFirst;
	private ChessPlayerColor movesSecond;
	private ChessPlayerColor currTurn;
	private int turn;
	
	public GameState(ChessPlayerColor movesFirst) {
		this.movesFirst = movesFirst;
		this.movesSecond = getOppositeColor(movesFirst);
		this.currTurn = movesFirst;
		this.turn = 0;
	}
	
	public GameState(ChessPlayerColor movesFirst, ChessPlayerColor currTurn, int turn) {
		this.movesFirst = movesFirst;
		this.movesSecond = getOppositeColor(movesFirst);
		this.currTurn = currTurn;
		this.turn = turn;
	}

	public ChessPlayerColor getCurrTurn() {
		return currTurn;
	}
	
	public void updateStateFromSingleMove() {
		if (currTurn == movesFirst) {
			currTurn = movesSecond;
		} else {
			currTurn = movesFirst;
			turn++;
		}
	}
	
	public ChessPlayerColor getOppositeColor(ChessPlayerColor color) {
		ChessPlayerColor oppositeColor;
		if (color == ChessPlayerColor.BLACK) {
			oppositeColor = ChessPlayerColor.WHITE;
		} else {
			oppositeColor = ChessPlayerColor.BLACK;
		}
		
		return oppositeColor;
	}
	
}

package common;

public class GameState {
	private ChessPlayerColor movesFirst;
	private ChessPlayerColor movesSecond;
	private ChessPlayerColor currPlayer;
	private int turn;
	
	public GameState(ChessPlayerColor movesFirst) {
		this.movesFirst = movesFirst;
		this.movesSecond = ChessPlayerColor.getOppositeColor(movesFirst);
		this.currPlayer = movesFirst;
		this.turn = 0;
	}
	
	public GameState(ChessPlayerColor movesFirst, ChessPlayerColor currTurn, int turn) {
		this.movesFirst = movesFirst;
		this.movesSecond = ChessPlayerColor.getOppositeColor(movesFirst);
		this.currPlayer = currTurn;
		this.turn = turn;
	}

	public ChessPlayerColor getCurrPlayer() {
		return currPlayer;
	}
	
	public void updateStateFromSingleMove() {
		if (currPlayer == movesFirst) {
			currPlayer = movesSecond;
		} else {
			currPlayer = movesFirst;
			turn++;
		}
	}
	
	public int getTurn() {
		return turn;
	}
	
}

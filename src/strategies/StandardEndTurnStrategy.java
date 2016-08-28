package strategies;

import common.MoveResult;
import standard.StandardBoard;

public class StandardEndTurnStrategy {
	
	StandardBoard board;
	
	public StandardEndTurnStrategy(StandardBoard board) {
		this.board = board;
	}

	public MoveResult getResult() {
		return MoveResult.OK;
	}
	
}

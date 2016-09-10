package strategies;

import common.ChessException;
import common.ChessPlayerColor;
import common.MoveResult;
import standard.StandardBoard;
import validation.CheckValidator;

public class StandardEndTurnStrategy {
	
	StandardBoard board;
	
	public StandardEndTurnStrategy(StandardBoard board) {
		this.board = board;
	}

	public MoveResult getResult(ChessPlayerColor color) throws ChessException {
		MoveResult result = MoveResult.OK;
		
		CheckValidator checkValidator = new CheckValidator(board);
		if (checkValidator.isKingInCheck(color)) {
			result = resolveCheckColor(color);
		}
		
		return result;
	}
	
	private MoveResult resolveCheckColor(ChessPlayerColor color) {
		if (color == ChessPlayerColor.WHITE) {
			return MoveResult.WHITE_IN_CHECK;
		} else {
			return MoveResult.BLACK_IN_CHECK;
		}
	}
	
}

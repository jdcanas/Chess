package strategies;

import java.util.ArrayList;

import common.ChessException;
import common.ChessGameType;
import common.ChessPlayerColor;
import common.Move;
import common.MoveResult;
import standard.StandardBoard;
import utilities.ValidMoveGenerator;
import utilities.ValidMoveGeneratorController;
import validation.CheckValidator;

public class StandardEndTurnStrategy {
	
	StandardBoard board;
	CheckValidator checkValidator;
	MoveResult previousResult;
	
	public StandardEndTurnStrategy(StandardBoard board) {
		this.board = board;
	}

	public MoveResult getResult(ChessPlayerColor color, MoveResult previousResult) throws ChessException {
		MoveResult result = MoveResult.OK;
		this.previousResult = previousResult;
		ValidMoveGeneratorController.getInstance().refresh(ChessGameType.STANDARD_CHESS, board, ValidMoveGenerator.VALIDATE_CHECK);
		
		checkValidator = new CheckValidator(board);
		if (checkValidator.isKingInCheck(color)) {
			result = resolveCheckColor(color);
		}
		
		MoveResult possibleDrawOrCheckMate = isCheckMateOrDraw(color);
		
		result = possibleDrawOrCheckMate == MoveResult.OK ? result : possibleDrawOrCheckMate;
		
		return result;
	}
	
	private MoveResult isCheckMateOrDraw(ChessPlayerColor color) throws ChessException {
		MoveResult result = MoveResult.OK;
		ChessPlayerColor oppositeColor = ChessPlayerColor.getOppositeColor(color);
		
		boolean canKingMove = checkValidator.checkIfKingCanMove(board.getKingLocation(oppositeColor));
		boolean isKingInCheck = checkValidator.isKingInCheck(oppositeColor);
	
		MoveResult thisPlayerCheck = MoveResult.getCheckResultForColor(oppositeColor);
		
		ValidMoveGeneratorController.getInstance().refresh(ChessGameType.STANDARD_CHESS, board, ValidMoveGenerator.VALIDATE_CHECK);
		ArrayList<Move> validMoves = ValidMoveGeneratorController.getInstance().removeKingMoves(oppositeColor, board.getKingLocation(oppositeColor), previousResult, canKingMove);
		
		if (isKingInCheck && !canKingMove && validMoves.isEmpty()) {
			result = MoveResult.getWinResultForColor(color); 
		} else if (validMoves.isEmpty() && !isKingInCheck) { 
			result = MoveResult.DRAW; 
		}
		return result;
	}
	
	
	private boolean isCheckMateOrDraw() {
		// TODO Auto-generated method stub
		return false;
	}

	private MoveResult resolveCheckColor(ChessPlayerColor color) {
		if (color == ChessPlayerColor.WHITE) {
			return MoveResult.WHITE_IN_CHECK;
		} else {
			return MoveResult.BLACK_IN_CHECK;
		}
	}
	
}

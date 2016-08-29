package standard.validation;

import java.util.HashMap;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGameType;
import common.ChessPieceType;
import factories.MovementValidationStrategyFactory;
import standard.StandardBoard;
import standard.StandardCoordinate;
import standard.StandardPiece;
import strategies.MovementValidationStrategy;

public class PreTurnValidator implements Validator {
	MovementValidationStrategy pieceValidationStrategy;
    
	public void validate(ChessCoordinate from, ChessCoordinate to, StandardBoard board, StandardPiece piece) throws ChessException {
		pieceValidationStrategy = 
				MovementValidationStrategyFactory.getInstance().getStrategy(ChessGameType.STANDARD_CHESS, piece.getType());
		
		
		validate(from, to, board);
	}

	@Override
	public void validate(ChessCoordinate from, ChessCoordinate to, StandardBoard board) throws ChessException {
	
	}
}
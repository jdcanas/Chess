package strategies.standardpiece;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.validation.MovementValidationException;
import strategies.StandardMovementValidationStrategy;
import utilities.CoordinateUtilities;

public class KingMovementStrategy extends StandardMovementValidationStrategy {

	public KingMovementStrategy(ChessCoordinate to, ChessCoordinate from, StandardBoard board) {
		super(to, from, board);	
	}

	@Override
	public ArrayList<ChessCoordinate> getPath() {
		return new ArrayList<ChessCoordinate>();
	}

	@Override
	public void validateDirection() throws ChessException {
		//If the king is moving somewhere other than an adjacent square
		if (!CoordinateUtilities.getAdjacentCoordinates(from).contains(to)) {
			throw new MovementValidationException(formatErrorMsg(ChessPieceType.KING, from));
		}
	}


}

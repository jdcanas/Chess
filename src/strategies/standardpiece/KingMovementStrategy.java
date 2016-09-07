package strategies.standardpiece;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import strategies.StandardMovementValidationStrategy;
import utilities.CoordinateUtilities;
import validation.exception.MovementValidationException;

public class KingMovementStrategy extends StandardMovementValidationStrategy {

	public KingMovementStrategy() {
		super();	
	}

	@Override
	public ArrayList<ChessCoordinate> getPath() {
		return new ArrayList<ChessCoordinate>();
	}

	@Override
	public void validateDirection() throws ChessException {
		//If the king is moving somewhere other than an adjacent square
		if (!CoordinateUtilities.getAdjacentCoordinates(from).contains(to)) {
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.KING, from));
		}
	}


}

package strategies.standardpiece;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import strategies.StandardMovementValidationStrategy;
import utilities.CoordinateUtilities;
import validation.exception.MovementValidationException;

public class StandardRookMovementStrategy extends StandardMovementValidationStrategy {

	@Override
	public void validateDirection() throws ChessException {
		int dX, dY;
		
		dX = Math.abs(to.getX() - from.getX());
		dY = Math.abs(to.getY() - from.getY());
		
		if (dX != 0 && dY != 0) {
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.ROOK, from)); 
		}
	}

	@Override
	public ArrayList<ChessCoordinate> getPath() {
		return CoordinateUtilities.getHorizontalPath(to, from);
	}

}

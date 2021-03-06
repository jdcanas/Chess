package strategies.standardpiece;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.StandardCoordinate;
import strategies.StandardMovementValidationStrategy;
import utilities.CoordinateUtilities;
import validation.exception.MovementValidationException;

public class StandardBishopMovementStrategy extends StandardMovementValidationStrategy {
	
	public StandardBishopMovementStrategy() {
		super();	
	}

	public void validateDirection() throws MovementValidationException {
		int toX = to.getX(), fromX = from.getX(), dX;
		int toY = to.getY(), fromY = from.getY(), dY;
		
		dX = Math.abs(toX - fromX);
		dY = Math.abs(toY - fromY);
		
		if (dX != dY) {
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.BISHOP, from));
		}
	}
	
	public ArrayList<ChessCoordinate> getPath() {
		return CoordinateUtilities.getDiagonalPath(to, from);
	}

}

package strategies.standardpiece;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import strategies.StandardMovementValidationStrategy;
import validation.exception.MovementValidationException;

public class StandardKnightMovementStrategy extends StandardMovementValidationStrategy {

	@Override
	public void validateDirection() throws ChessException {
		int toX = to.getX(), fromX = from.getX(), dX;
		int toY = to.getY(), fromY = from.getY(), dY;
		
		dX = Math.abs(toX - fromX);
		dY = Math.abs(toY - fromY);
		
		if (dX == 2) {
			if (dY != 1) {
				throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.KNIGHT, from));
			}
		} else if (dY == 2) {
			if (dX != 1) {
				throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.KNIGHT, from));
			}
		} else {
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.KNIGHT, from));
		}
		
	}

	@Override
	public ArrayList<ChessCoordinate> getPath() {
		ArrayList<ChessCoordinate> path = new ArrayList<ChessCoordinate>();
		return path;
	}

}

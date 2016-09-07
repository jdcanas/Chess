package strategies.standardpiece;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGameType;
import common.ChessPieceType;
import factories.MovementValidationStrategyFactory;
import strategies.StandardMovementValidationStrategy;
import validation.exception.MovementValidationException;

public class StandardQueenMovementStrategy extends StandardMovementValidationStrategy {

	StandardBishopMovementStrategy diagonalStrategy = (StandardBishopMovementStrategy) MovementValidationStrategyFactory.getInstance().getStrategy(ChessGameType.STANDARD_CHESS, ChessPieceType.BISHOP);
	StandardRookMovementStrategy horizontalStrategy = (StandardRookMovementStrategy) MovementValidationStrategyFactory.getInstance().getStrategy(ChessGameType.STANDARD_CHESS, ChessPieceType.ROOK);
	StandardMovementValidationStrategy movementStrategy;
	boolean isDiagonal = false;
	
	@Override
	public void validateDirection() throws ChessException {		
		int dX, dY;
		
		dX = Math.abs(to.getX() - from.getX());
		dY = Math.abs(to.getY() - from.getY());
		
		if (dX == dY) {
			isDiagonal = true;
		}
		
		if (isDiagonal) {
			movementStrategy = diagonalStrategy;
		} else {
			movementStrategy = horizontalStrategy;
		}
		
		try {
			movementStrategy.validate(to, from, board);
		} catch (MovementValidationException e) {
			throw new MovementValidationException(e.getMessage().replace("Rook", "Queen").replace("Bishop", "Queen"));			
		}
	}

	@Override
	public ArrayList<ChessCoordinate> getPath() {
		ArrayList<ChessCoordinate> path;
		if (isDiagonal) {
			path = diagonalStrategy.getPath();
		} else {
			path = horizontalStrategy.getPath();
		}
		return path;
	}

}

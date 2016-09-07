package strategies.standardpiece;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;
import utilities.CoordinateUtilities;
import validation.exception.MovementValidationException;

public class StandardPawnMovementStrategy extends StandardMovementValidationStrategy {
	public static final int WHITE_PAWN_STARTING_ROW = 1;
	public static final int BLACK_PAWN_STARTING_ROW = 6;

	int dX, dY;
	boolean isDiagonal = false;

	@Override
	public void validateDirection() throws ChessException {
		StandardPiece pawn = board.getPiece(from);

		dX = Math.abs(to.getX() - from.getX());
		dY = Math.abs(to.getY() - from.getY());

		if (dX == dY) {
			isDiagonal = true;
		}

		if (isDiagonal) {
			validateDiagonalPawn();	
		} else {
			validateHorizontalPawn(pawn);
		}
	}

	@Override
	public ArrayList<ChessCoordinate> getPath() {
		ArrayList<ChessCoordinate> path = new ArrayList<ChessCoordinate>();

		if (isDiagonal) {
			path = CoordinateUtilities.getDiagonalPath(to, from);
		} else {
			path = CoordinateUtilities.getHorizontalPath(to, from);
		}

		return path;
	}

	private void validateDiagonalPawn() throws ChessException {
		if (board.getPiece(to) == null) {
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.PAWN, from));
		}
		if (!(dX == 1 && dY == 1)) {
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.PAWN, from));					
		}
	}

	private void validateHorizontalPawn(StandardPiece pawn) throws ChessException {
		int startingRow = pawn.getColor() == ChessPlayerColor.WHITE ? 
				WHITE_PAWN_STARTING_ROW : BLACK_PAWN_STARTING_ROW;

		if (board.getPiece(to) != null) {
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.PAWN, from));
		}
		int allowedHorizontalDistance = from.getY() == startingRow ? 2 : 1;
		if (dY > allowedHorizontalDistance || dX != 0) { 
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.PAWN, from));
		}
	}

}

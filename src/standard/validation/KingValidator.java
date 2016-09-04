package standard.validation;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGameType;
import common.ChessPlayerColor;
import factories.MovementValidationStrategyFactory;
import standard.StandardBoard;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;

public class KingValidator {

	public static final String MOVE_PUTS_KING_IN_CHECK = "The move you attempted to make would leave your King in check.";

	public void validate(ChessCoordinate from, ChessCoordinate to, StandardBoard board) throws ChessException {
		StandardMovementValidationStrategy currPieceCheckValidator;
		StandardPiece currPiece;
		ChessPlayerColor currPlayerColor = board.getPiece(from).getColor();
		ChessCoordinate simulatedTo, simulatedFrom, beforeMoveOccursKingLocation, adjustedKingLocation;

		ArrayList<ChessCoordinate> pieceLocations = board.getPieceLocations();
		for (ChessCoordinate currCoordinate: pieceLocations) {
			currPiece = board.getPiece(currCoordinate);

			beforeMoveOccursKingLocation = board.getKingLocation(currPlayerColor);
			adjustedKingLocation = beforeMoveOccursKingLocation.equals(from) ? to : beforeMoveOccursKingLocation;

			simulatedTo = adjustedKingLocation;
			simulatedFrom = currCoordinate;

			if(currPiece.getColor() != currPlayerColor) {
				currPieceCheckValidator = MovementValidationStrategyFactory.getInstance()
						.getStrategy(ChessGameType.STANDARD_CHESS, currPiece.getType(), simulatedTo, simulatedFrom, board);


				attemptMove(currPieceCheckValidator);
			}
		}

	}

	private void attemptMove(StandardMovementValidationStrategy validator) throws ChessException {
		try {
			validator.validate(validator.isKingValidationStrategy());
			throw new MovePutsKingInCheckException(MOVE_PUTS_KING_IN_CHECK);
		} catch (MovementValidationException e) {
			if (e.getMessage().equals(MOVE_PUTS_KING_IN_CHECK)) {
				throw e;
			} else {
			//this means that the move does not actually put the king in check
			}
		}
	}

}

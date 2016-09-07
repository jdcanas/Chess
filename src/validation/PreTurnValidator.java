package validation;

import java.util.HashMap;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGameType;
import common.ChessPieceType;
import common.ChessPlayerColor;
import factories.MovementValidationStrategyFactory;
import standard.StandardBoard;
import standard.StandardCoordinate;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;
import utilities.CoordinateUtilities;
import validation.exception.PreTurnValidationException;

public class PreTurnValidator implements Validator {
	public static final String PIECE_IS_NOT_THERE = "The piece you tried to move is not at the specified location.";
	public static final String PIECE_NOT_YOURS = "The piece you tried to move is not yours";
	public static final String SAME_TO_FROM = "The desintation and origin are the same.";
	public static final String YOUR_PIECE_IS_THERE = "The location you are moving to has one of your pieces.";
	
	StandardMovementValidationStrategy pieceValidationStrategy;
	boolean isMovingIntoCheckValid;
    
	public void validate(ChessCoordinate from, ChessCoordinate to, StandardBoard board, ChessPlayerColor color, ChessPieceType piece, boolean isMovingIntoCheckValid) throws ChessException {
		this.isMovingIntoCheckValid = isMovingIntoCheckValid;
 		pieceValidationStrategy = 
				MovementValidationStrategyFactory.getInstance().getStrategy(ChessGameType.STANDARD_CHESS, piece);
	
		//Check if the piece the player specified is present at the specified location
		if (board.getPiece(from) == null ||
				!piece.equals(board.getPiece(from).getType())) {
			throw new PreTurnValidationException(PIECE_IS_NOT_THERE);
		}
		
		//Checks if the piece belongs to the player
		if (!color.equals(board.getPiece(from).getColor())) {
			throw new PreTurnValidationException(PIECE_NOT_YOURS);
		}
		
		validate(from, to, board);
	}

	@Override
	public void validate(ChessCoordinate from, ChessCoordinate to, StandardBoard board) throws ChessException {
		//If the two coordinates are the same (i.e. no movement actually specified)
		if (to.equals(from)) {
			throw new PreTurnValidationException(SAME_TO_FROM);
		}
		
		if (!CoordinateUtilities.getValidCoordinates().contains(from)) {
			throw new PreTurnValidationException(to.toString() + " is not a valid coordinate.");
		}
		
		if (!CoordinateUtilities.getValidCoordinates().contains(to)) {
			throw new PreTurnValidationException(from.toString() + " is not a valid coordinate.");
		}
		
		//Checks if one of your pieces is at the destination location
		if (isPieceAtLoc(from, board) && isPieceAtLoc(to, board) &&
				board.getPiece(from).getColor().equals(board.getPiece(to).getColor())) {
			throw new PreTurnValidationException(YOUR_PIECE_IS_THERE);
		}
		
		pieceValidationStrategy.validate(to, from, board, isMovingIntoCheckValid);
	}
	
	private boolean isPieceAtLoc(ChessCoordinate loc, StandardBoard board) {
		return board.getPiece(loc) != null;
	}
}
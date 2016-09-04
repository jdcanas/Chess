package strategies;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.StandardCoordinate;
import validation.KingValidator;
import validation.Validator;
import validation.exception.MovementValidationException;

public abstract class StandardMovementValidationStrategy {
	protected ChessCoordinate to;
	protected ChessCoordinate from;
	protected StandardBoard board;
	
	public static final String MOVEMENT_TYPE_ERROR_MSG = "You attempted to move the %s at %s incorrectly.";
	
	public StandardMovementValidationStrategy(ChessCoordinate to, 
			ChessCoordinate from, StandardBoard board) {
		this.to = to;
		this.from = from;
		this.board = board;
	}
	
	public void validate() throws ChessException {
		baseValidate();
		validateYourKingInNotCheck();
	}

	//Used to prevent infinite loops when checking for check and it is time to check the opposing King
	public void validate(boolean kingValidationStrategy) throws ChessException {
		baseValidate();
	}
	
	private void baseValidate() throws ChessException {
		validateDirection();
		validatePathForPiecesInWay();
	}
	
	public abstract void validateDirection() throws ChessException;
	
	public static String formatErrorMsg(ChessPieceType type,  ChessCoordinate from) {
		String formattedError = String.format(MOVEMENT_TYPE_ERROR_MSG, type.toString(), from.toString());
		return formattedError;
	}
	
	public void validatePathForPiecesInWay() throws MovementValidationException {
		ArrayList<ChessCoordinate> path = getPath();
		for (ChessCoordinate c: path) {
			if (board.getPiece(c) != null) { //TODO does this print correctly?
				throw new MovementValidationException("You attempted to move from " + from.toString() +  " to " + to.toString() + " but there was a piece in the way at " + c.toString());
			}
		}
	}

	public abstract ArrayList<ChessCoordinate> getPath();
	
	public boolean isKingValidationStrategy() {
		return board.getPiece(from).getType() == ChessPieceType.KING;
	}

	private void validateYourKingInNotCheck() throws ChessException {
		
		KingValidator kingValidator = new KingValidator();
		kingValidator.validate(from, to, board);
	}
}

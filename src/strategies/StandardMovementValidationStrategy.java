package strategies;

import java.util.ArrayList;
import java.util.List;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.StandardCoordinate;
import standard.StandardPiece;
import validation.KingValidator;
import validation.Validator;
import validation.exception.MovePutsKingInCheckException;
import validation.exception.MovementValidationException;

public abstract class StandardMovementValidationStrategy implements Validator {
	protected ChessCoordinate to;
	protected ChessCoordinate from;
	protected StandardBoard board;
	boolean isMovingIntoCheckValid;
	
	public static final String MOVEMENT_TYPE_ERROR_MSG = "You attempted to move the %s at %s incorrectly.";
	public static final String PIECE_IN_WAY = "You attempted to move from %s to %s but there was a piece in the way at %s";
	
	public abstract void validateDirection() throws ChessException;
	public abstract ArrayList<ChessCoordinate> getPath();
	
	public StandardMovementValidationStrategy() {
		
	}

	@Override
	public void validate(ChessCoordinate to, ChessCoordinate from, StandardBoard board) throws ChessException {
		this.to = to;
		this.from = from;
		this.board = board;
		this.isMovingIntoCheckValid = false;
		baseValidate();
	}

	public void validate(ChessCoordinate to, ChessCoordinate from, StandardBoard board,
			boolean isMovingIntoCheckValid) throws ChessException {
		this.isMovingIntoCheckValid = isMovingIntoCheckValid;
		validate(to, from, board); 
		
		if (ChessPieceType.KING == board.getPiece(from).getType()) {
			validateKingNotInCheckFromKing(to, from, board);
		} else if (!isMovingIntoCheckValid) {
			validateYourKingInNotCheck();
		}	
	}
	
	private void baseValidate() throws ChessException {
		validateDirection();
		validatePathForPiecesInWay();
	}
	
	public void validatePathForPiecesInWay() throws MovementValidationException {
		ArrayList<ChessCoordinate> path = getPath();
		for (ChessCoordinate c: path) {
			if (board.getPiece(c) != null) { //TODO does this print correctly?
				throw new MovementValidationException(formatPieceInWayErrorMsg(from, to, c));
			}
		}
	}
	
	public boolean isKingValidationStrategy(StandardPiece piece) {
		return piece.getType() == ChessPieceType.KING;
	}
	
	private void validateYourKingInNotCheck() throws ChessException {	 
		KingValidator kingValidator = new KingValidator();
		kingValidator.validate(to, from, board);
	}
	
	private void validateKingNotInCheckFromKing(ChessCoordinate to, ChessCoordinate from, StandardBoard board) throws ChessException {
		ArrayList<ChessCoordinate> possibleKingLoc = new StandardCoordinate(to).getAdjacencyList();
		
		for (ChessCoordinate c: possibleKingLoc) {
			if (board.getPiece(c) != null && !c.equals(from) && //assure there is a piece at the location and the piece isn't the king in question
					board.getPiece(c).getType() == ChessPieceType.KING) {
				throw new MovePutsKingInCheckException(KingValidator.MOVE_PUTS_KING_IN_CHECK);
			}
		}
		
	}
	
	public static String formatMovementErrorMsg(ChessPieceType type,  ChessCoordinate from) {
		String formattedError = String.format(MOVEMENT_TYPE_ERROR_MSG, type.toString(), from.toString());
		return formattedError;
	}
	
	public static String formatPieceInWayErrorMsg(ChessCoordinate from, ChessCoordinate to, ChessCoordinate pieceInWayLoc) {
		String formattedError = String.format(PIECE_IN_WAY, from, to, pieceInWayLoc);
		return formattedError;
	}
}

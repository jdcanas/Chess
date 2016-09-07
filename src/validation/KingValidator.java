package validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGameType;
import common.ChessPieceType;
import common.ChessPlayerColor;
import factories.MovementValidationStrategyFactory;
import standard.StandardBoard;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;
import utilities.ValidMoveGenerator;
import validation.exception.MovePutsKingInCheckException;
import validation.exception.MovementValidationException;

public class KingValidator {
	ValidMoveGenerator moveGenerator;

	public static final String MOVE_PUTS_KING_IN_CHECK = "The move you attempted to make would leave your King in check.";

	protected ChessCoordinate to;
	protected ChessCoordinate from;
	protected StandardBoard board;
	public ChessCoordinate kingLoc;

	public void validate(ChessCoordinate to, ChessCoordinate from, StandardBoard board) throws ChessException {
		this.to = to;
		this.from = from;
		StandardPiece piece = new StandardPiece(board.getPiece(from).getColor(), board.getPiece(from).getType());
		HashMap<ChessCoordinate, StandardPiece> underlyingBoard;
		
		underlyingBoard = (HashMap<ChessCoordinate, StandardPiece>) board.getUnderlyingBoard().clone();
		
		StandardBoard nextTurnBoard = new StandardBoard(underlyingBoard);
		nextTurnBoard.movePiece(piece, to, from);
		
		kingLoc = nextTurnBoard.getKingLocation(piece.getColor());
		
		Set<ChessCoordinate> totalLocationsUnderAttack = new HashSet<ChessCoordinate>();
		ArrayList<ChessCoordinate> newLocationsUnderAttack;

		ArrayList<ChessCoordinate> pieceLocations = nextTurnBoard.getPieceLocations();
		for (ChessCoordinate currCoordinate: pieceLocations) {
			newLocationsUnderAttack = getValidMoves(currCoordinate, nextTurnBoard, nextTurnBoard.getPiece(to).getColor());
			totalLocationsUnderAttack.addAll(newLocationsUnderAttack);
		}

		if (totalLocationsUnderAttack.contains(kingLoc)) {
			throw new MovePutsKingInCheckException(MOVE_PUTS_KING_IN_CHECK);
		}

	}

	public ArrayList<ChessCoordinate> getValidMoves(ChessCoordinate pieceLoc, StandardBoard board, ChessPlayerColor color) {
		ArrayList<ChessCoordinate> validMoves = new ArrayList<ChessCoordinate>();

		if (color == board.getPiece(pieceLoc).getColor()) {
			return validMoves;
		}

		StandardPiece piece = new StandardPiece(board.getPiece(pieceLoc).getColor(), board.getPiece(pieceLoc).getType());

		moveGenerator = new ValidMoveGenerator(ValidMoveGenerator.DONT_VALIDATE_CHECK);

		validMoves = moveGenerator.getValidMoves(ChessGameType.STANDARD_CHESS, piece.getColor(), piece.getType(), pieceLoc, board, true);

		return validMoves;
	}



	//		StandardMovementValidationStrategy currPieceCheckValidator;
	//		StandardPiece currPiece;
	//		ChessPlayerColor currPlayerColor = board.getPiece(from).getColor();
	//		ChessCoordinate simulatedTo, simulatedFrom, beforeMoveOccursKingLocation, adjustedKingLocation;
	//
	//		ArrayList<ChessCoordinate> pieceLocations = board.getPieceLocations();
	//		for (ChessCoordinate currCoordinate: pieceLocations) {
	//			currPiece = board.getPiece(currCoordinate);
	//
	//			beforeMoveOccursKingLocation = board.getKingLocation(currPlayerColor);
	//			adjustedKingLocation = beforeMoveOccursKingLocation.equals(from) ? to : beforeMoveOccursKingLocation;
	//
	//			simulatedTo = adjustedKingLocation;
	//			simulatedFrom = currCoordinate;
	//
	//			if(currPiece.getColor() != currPlayerColor) {
	//				currPieceCheckValidator = MovementValidationStrategyFactory.getInstance()
	//						.getStrategy(ChessGameType.STANDARD_CHESS, currPiece.getType());//, simulatedTo, simulatedFrom, board);
	//
	//
	//				attemptMove(currPieceCheckValidator);
	//			}
	//		}
	//
	//	}
	//
	//	private void attemptMove(StandardMovementValidationStrategy validator) throws ChessException {
	//		try {
	//			validator.validate(validator.isKingValidationStrategy(board.getPiece(from)), to, from, board);
	//		} catch (MovementValidationException e) {
	//			if (e.getMessage().equals(MOVE_PUTS_KING_IN_CHECK)) {
	//				throw e;
	//			} else {
	//			//this means that the move does not actually put the king in check
	//			}
	//		}
	//	}

}

package utilities;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGameType;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.GameState;
import common.Move;
import factories.MovementValidationStrategyFactory;
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardCoordinate;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;
import validation.CheckValidator;
import validation.PreTurnValidator;
import validation.exception.MovePutsKingInCheckException;
import validation.exception.MovementValidationException;

public class ValidMoveGenerator {
	private ArrayList<Move> validMoves;
	
	public static final boolean VALIDATE_CHECK = false;
	public static final boolean DONT_VALIDATE_CHECK = true;
	
	private ChessPlayerColor color;
	private ChessPieceType piece;
	private ChessCoordinate from;
	private StandardBoard board;
	private PreTurnValidator moveValidator;
	private boolean isMovingIntoCheckValid;
	
	public ValidMoveGenerator(ChessGameType gameType, ChessPlayerColor color, ChessPieceType piece, ChessCoordinate from, StandardBoard board, boolean isMovingIntoCheckValid) {
		this.color = color;
		this.piece = piece;
		this.from = from;
		this.board = board;
		moveValidator = new PreTurnValidator();
		this.isMovingIntoCheckValid = isMovingIntoCheckValid;
		
		validMoves = generateValidMoves(isMovingIntoCheckValid);
	}
	
	public ValidMoveGenerator(boolean isMovingIntoCheckValid) {
		this.isMovingIntoCheckValid = isMovingIntoCheckValid;
	}

	public ArrayList<Move> getValidMoves() {
		return validMoves;
	}
	
	public ArrayList<Move> getValidMoves(ChessGameType gameType, ChessPlayerColor color, ChessPieceType piece, ChessCoordinate from, StandardBoard board, boolean isMovingIntoCheckValid) {
		this.color = color;
		this.piece = piece;
		this.from = from;
		this.board = board;
		moveValidator = new PreTurnValidator();
		this.isMovingIntoCheckValid = isMovingIntoCheckValid;
		
		return generateValidMoves(isMovingIntoCheckValid);
	}

	private ArrayList<Move> generateValidMoves(boolean isMovingIntoCheckValid) {
		ArrayList<Move> movesFound = new ArrayList<Move>();
		
		ArrayList<ChessCoordinate> allCoordinates = CoordinateUtilities.getValidCoordinates();
		
		for (ChessCoordinate currCoordinate: allCoordinates) {

			//StandardChessGame game = makeSimulatedGame(board, color);
			try {
    			//game.makeMove(piece, from, currCoordinate);
				moveValidator.validate(from, currCoordinate, board, color, board.getPiece(from).getType(), isMovingIntoCheckValid);
				movesFound.add(new Move(currCoordinate, from, piece));
			} catch (ChessException e) {
				
			}
		}
		
		return movesFound;
	}
	
	public static StandardChessGame makeSimulatedGame(StandardBoard board, ChessPlayerColor currTurn) {
		GameState state = new GameState(currTurn);
		StandardChessGame game = new StandardChessGame(new StandardBoard(board.getUnderlyingBoard()), state);
		return game;
	}

	
//	public void validate(ChessCoordinate from, ChessCoordinate to, StandardBoard board) throws ChessException {
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
//						.getStrategy(ChessGameType.STANDARD_CHESS, currPiece.getType(), simulatedTo, simulatedFrom, board);
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
//			validator.validate(validator.isKingValidationStrategy());
//			throw new MovePutsKingInCheckException(MOVE_PUTS_KING_IN_CHECK);
//		} catch (MovementValidationException e) {
//			if (e.getMessage().equals(MOVE_PUTS_KING_IN_CHECK)) {
//				throw e;
//			} else {
//			//this means that the move does not actually put the king in check
//			}
//		}
//	}
	
}

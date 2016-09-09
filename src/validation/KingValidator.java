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
import common.Move;
import factories.MovementValidationStrategyFactory;
import standard.StandardBoard;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;
import utilities.ValidMoveGenerator;
import validation.exception.BoardOutOfBoundsException;
import validation.exception.KingNotFoundException;
import validation.exception.MovePutsKingInCheckException;
import validation.exception.MovementValidationException;

public class KingValidator {
	ValidMoveGenerator moveGenerator;

	public static final String MOVE_PUTS_KING_IN_CHECK = "The move you attempted to make would leave your King in check.";
	protected StandardBoard board;
	public ChessPlayerColor color;
	boolean isWhiteKingInCheck; boolean isBlackKingInCheck;
	public ChessCoordinate whiteKingLoc, blackKingLoc, kingLoc;
	private Set<ChessCoordinate> locationsBlackThreatens, locationsWhiteThreatens;
	public Set<Move> moves;
	
	public KingValidator(StandardBoard board) throws ChessException {
		this.board = board;
		whiteKingLoc = board.getKingLocation(ChessPlayerColor.WHITE);
		blackKingLoc = board.getKingLocation(ChessPlayerColor.BLACK);
		
		locationsBlackThreatens = getLocationsUnderAttackForPlayer(ChessPlayerColor.WHITE);
		locationsWhiteThreatens = getLocationsUnderAttackForPlayer(ChessPlayerColor.BLACK);
	}
	
	public void refreshLocations() throws ChessException {
		locationsBlackThreatens = getLocationsUnderAttackForPlayer(ChessPlayerColor.WHITE);
		locationsWhiteThreatens = getLocationsUnderAttackForPlayer(ChessPlayerColor.BLACK);
	}
	
	
	public boolean isKingInCheck(ChessPlayerColor color) {
		if (color == ChessPlayerColor.WHITE) {
			return locationsBlackThreatens.contains(whiteKingLoc);
		} else {
			return locationsWhiteThreatens.contains(blackKingLoc);
		}
	}	
	
	private Set<ChessCoordinate> getLocationsUnderAttackForPlayer(ChessPlayerColor color) throws ChessException {
		this.color = color;
		kingLoc = color == ChessPlayerColor.WHITE ? whiteKingLoc : blackKingLoc;
		
		Set<Move> totalLocationsUnderAttack = new HashSet<Move>();
		ArrayList<Move> newLocationsUnderAttack;
		

		ArrayList<ChessCoordinate> pieceLocations = board.getPieceLocations();
		for (ChessCoordinate currCoordinate: pieceLocations) {
			newLocationsUnderAttack = getValidMoves(currCoordinate, board, color);
	
			totalLocationsUnderAttack.addAll(newLocationsUnderAttack);
		}
		
		for (Move m: totalLocationsUnderAttack) {
			if (kingLoc == m.to) {
				System.out.println("Move is attacking king location: " + (kingLoc == m.to) + " piece: " +  m.piece.getPrintableName());
			}
		}
		
		moves = totalLocationsUnderAttack;
		
		return movesToCoordinates(totalLocationsUnderAttack);

	}
	
	public Set<ChessCoordinate> movesToCoordinates(Set<Move> moves) {
		Set<ChessCoordinate> coordinates = new HashSet<ChessCoordinate>();
		
		for (Move m: moves) {
			coordinates.add(m.to);
		}
		return coordinates;
	}
	
	private ArrayList<Move> getValidMoves(ChessCoordinate pieceLoc, StandardBoard board, ChessPlayerColor color) {
		ArrayList<Move> validMoves = new ArrayList<Move>();
		this.board = board;
		this.color = color;

		if (color == board.getPiece(pieceLoc).getColor()) {
			return validMoves;
		}

		StandardPiece piece = new StandardPiece(board.getPiece(pieceLoc).getColor(), board.getPiece(pieceLoc).getType());

		moveGenerator = new ValidMoveGenerator(ValidMoveGenerator.DONT_VALIDATE_CHECK);

		validMoves = moveGenerator.getValidMoves(ChessGameType.STANDARD_CHESS, piece.getColor(), piece.getType(), pieceLoc, board, ValidMoveGenerator.DONT_VALIDATE_CHECK);

		return validMoves;
	}
	
	public void setupNextTurnBoard(ChessCoordinate to, ChessCoordinate from, StandardPiece piece, StandardBoard board) throws BoardOutOfBoundsException {
		this.board = makeNextTurnBoard(to, from, piece, board);
	}
	
	public static StandardBoard makeNextTurnBoard(ChessCoordinate to, ChessCoordinate from, StandardPiece piece, StandardBoard board) throws BoardOutOfBoundsException {
		HashMap<ChessCoordinate, StandardPiece> underlyingBoard;
		
		underlyingBoard = (HashMap<ChessCoordinate, StandardPiece>) board.getUnderlyingBoard().clone();
		
		StandardBoard nextTurnBoard = new StandardBoard(underlyingBoard);
		nextTurnBoard.movePiece(piece, to, from);
		return nextTurnBoard;
	}
}

package standard;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGame;
import common.ChessGameType;
import common.ChessPiece;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.GameState;
import common.Move;
import common.MoveArray;
import common.MoveResult;
import factories.MovementValidationStrategyFactory;
import strategies.StandardEndTurnStrategy;
import utilities.CoordinateUtilities;
import utilities.ValidMoveGenerator;
import utilities.ValidMoveGeneratorController;
import validation.CheckValidator;
import validation.EndTurnValidator;
import validation.PreTurnValidator;
import validation.exception.EndTurnValidationException;
import validation.exception.KingNotFoundException;
import validation.exception.MoveMadeAfterGameClosedException;

public class StandardChessGame implements ChessGame {
	StandardBoard board;
	GameState turnState;
	
	ArrayList<Move> validMoves;

	PreTurnValidator preTurnValidator;
	EndTurnValidator endTurnValidator;

	StandardEndTurnStrategy gameStatusResolver;
	MoveResult previousResult = MoveResult.OK;
	MoveResult newResult;
	private boolean gameClosed = false;

	public StandardChessGame(ChessPlayerColor movesFirst) {
		board = new StandardBoard();
		turnState = new GameState(movesFirst);

		buildGame();
	}

	/**
	 * This constructor should be used for testing purposes only. The other is used for creating new games.
	 * @param startingBoard
	 * @param state
	 */
	public StandardChessGame(StandardBoard startingBoard, GameState state) {
		board = startingBoard;
		this.turnState = state;

		buildGame();
	}

	private void buildGame() {
		preTurnValidator = new PreTurnValidator();
		endTurnValidator = new EndTurnValidator();

		gameStatusResolver = new StandardEndTurnStrategy(board);
	}

	public MoveResult makeMove(ChessPieceType pieceType, ChessCoordinate from, ChessCoordinate to)
			throws ChessException {
		StandardPiece piece = new StandardPiece(turnState.getCurrPlayer(), pieceType);
		if (gameClosed) {
			throw new MoveMadeAfterGameClosedException("The game has ended.");
		}
		
		MoveResult result;
//		if (isCheckMateOrDraw()) {
//			this.gameClosed  = true;
//			return this.newResult;
//		}

		preTurnValidator.validate(from, to, board, turnState.getCurrPlayer(), pieceType, ValidMoveGenerator.VALIDATE_CHECK);

		board.movePiece(piece, to, from);
		
		result = gameStatusResolver.getResult(turnState.getCurrPlayer(), previousResult);
		previousResult = result;
		//iF A CHECKMATE OR DRAW RETURNS, SET THE GAMESTATE TO CLOSED AND END THE GAME

		turnState.updateStateFromSingleMove();

		return result;
	}
	
	public boolean isGameClosed() {
		return gameClosed;
	}
	
	public ChessPlayerColor getTurn() {
		return turnState.getCurrPlayer();
	}
	
//	private boolean isCheckMateOrDraw() throws ChessException {
//		ChessPlayerColor currColor = turnState.getCurrPlayer();
//		
//		CheckValidator checkValidator = new CheckValidator(board);
//		boolean canKingMove = checkValidator.checkIfKingCanMove(board.getKingLocation(currColor));
//		
//		boolean resultChanged = false;
//	
//		MoveResult thisPlayerCheck = MoveResult.getCheckResultForColor(currColor);
//
//		ValidMoveGeneratorController.getInstance().removeKingMoves(currColor, board.getKingLocation(currColor), (ArrayList<Move>) validMoves.clone(), previousResult, canKingMove);
//		validMoves = ValidMoveGeneratorController.getInstance().getMovesForColor(turnState.getCurrPlayer(), board, ValidMoveGenerator.VALIDATE_CHECK);
////		
////		ArrayList<Move> movesWithBlockagesRemoved = validMoves;
////		if (previousResult == thisPlayerCheck || !canKingMove) {
////			movesWithBlockagesRemoved = MoveArray.removeKingMoves(currColor, board.getKingLocation(currColor), (ArrayList<Move>) validMoves.clone(), canKingMove);
////		}
//		
//		if (validMoves.isEmpty() && previousResult == MoveResult.OK ) {
//			this.newResult = MoveResult.DRAW;
//			resultChanged = true;
//		} else if (validMoves.isEmpty()) { 
//			this.newResult = MoveResult.getWinResultForColor(ChessPlayerColor.getOppositeColor(currColor)); 
//			resultChanged = true;
//		}
////		
////		if (previousResult == thisPlayerCheck) {
////			if (validMoves.isEmpty()) {
////				this.newResult = MoveResult.getWinResultForColor(ChessPlayerColor.getOppositeColor(currColor)); 
////				resultChanged = true;
////			}
////		} else if (validMoves.isEmpty() && previousResult == MoveResult.OK ) {
////			this.newResult = MoveResult.DRAW;
////			resultChanged = true;
////		}
//		return resultChanged;
//	}
	
	public static boolean canKingMove(ChessPlayerColor currColor, StandardBoard board) throws ChessException {
		return canKingMove(currColor, board, new CheckValidator(board));
	}
	
	public static boolean canKingMove(ChessPlayerColor currColor, StandardBoard board, CheckValidator checkValidator) throws KingNotFoundException {
		return checkValidator.checkIfKingCanMove(board.getKingLocation(currColor));
	}

	public ArrayList<Move> getMovesForColor(ChessPlayerColor color) {
		ArrayList<ChessCoordinate> pieceLocs = getPiecesForColor(color);
		ArrayList<Move> moves = new ArrayList<Move>();
		
		ValidMoveGenerator validMoves = new ValidMoveGenerator(ChessGameType.STANDARD_CHESS, turnState.getCurrPlayer(), null, null, board, ValidMoveGenerator.VALIDATE_CHECK);
	
		for (ChessCoordinate c: pieceLocs) {
			validMoves.refresh(ChessGameType.STANDARD_CHESS, color, board.getPiece(c).getType(), c, board, ValidMoveGenerator.VALIDATE_CHECK);
			moves.addAll(validMoves.getValidMoves());
			
		}
		
		return moves;
	}
	
	public ArrayList<ChessCoordinate> getPiecesForColor(ChessPlayerColor color) {
		ArrayList<ChessCoordinate> newPieceLocs = new ArrayList<ChessCoordinate>();
		
		ArrayList<ChessCoordinate> pieceLocs = board.getPieceLocations();
		
		for (ChessCoordinate c: pieceLocs) {
			if (board.getPiece(c).getColor() == color) {
				newPieceLocs.add(c);
			}
		}
		
		return newPieceLocs;
	}

	@Override
	public ChessPiece getPieceAt(ChessCoordinate where) {
		return board.getPiece(where);
	}

	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
	
	public void setPreviousResult(MoveResult result) {
		this.previousResult = result;
	}

	public StandardBoard getBoard() {
		return board;
	}
}

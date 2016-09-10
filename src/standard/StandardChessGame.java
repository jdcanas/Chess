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
import validation.CheckValidator;
import validation.EndTurnValidator;
import validation.PreTurnValidator;
import validation.exception.EndTurnValidationException;
import validation.exception.KingNotFoundException;

public class StandardChessGame implements ChessGame {
	StandardBoard board;
	GameState turnState;
	
	ValidMoveGenerator validMoves;

	PreTurnValidator preTurnValidator;
	EndTurnValidator endTurnValidator;

	StandardEndTurnStrategy gameStatusResolver;
	MoveResult previousResult = MoveResult.OK;
	MoveResult newResult;

	public StandardChessGame(ChessPlayerColor movesFirst) {
		board = new StandardBoard();
		turnState = new GameState(movesFirst);
		validMoves = new ValidMoveGenerator(ValidMoveGenerator.VALIDATE_CHECK);

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
	
		MoveResult result;
		if (isCheckMateOrDraw()) {
			return this.newResult;
		}

		preTurnValidator.validate(from, to, board, turnState.getCurrPlayer(), pieceType, ValidMoveGenerator.VALIDATE_CHECK);

		board.movePiece(piece, to, from);

		endTurnValidator.validate(from, to, board);
		
		result = gameStatusResolver.getResult(turnState.getCurrPlayer());

		turnState.updateStateFromSingleMove();

		return result;
	}
	
	private boolean isCheckMateOrDraw() throws ChessException {
		ChessPlayerColor currColor = turnState.getCurrPlayer();
		
		CheckValidator checkValidator = new CheckValidator(board);
		boolean canKingMove = checkValidator.checkIfKingCanMove(board.getKingLocation(currColor));
		
		
		MoveResult result;
		boolean resultChanged = false;
		
		ArrayList<Move> moves = getMovesForColor(currColor);
		
		MoveResult thisPlayerCheck = MoveResult.getCheckResultForColor(currColor);
		
		if (previousResult == thisPlayerCheck || !canKingMove) {
			moves = MoveArray.removeKingMoves(currColor, board.getKingLocation(currColor), moves);
		}
		
		if (previousResult == thisPlayerCheck) {
			if (moves.isEmpty()) {
				this.newResult = MoveResult.getWinResultForColor(ChessPlayerColor.getOppositeColor(currColor)); 
				resultChanged = true;
			}
		} else if (moves.isEmpty() && previousResult == MoveResult.OK) {
			this.newResult = MoveResult.DRAW;
			resultChanged = true;
		}
		return resultChanged;
	}

	public ArrayList<Move> getMovesForColor(ChessPlayerColor color) {
		ArrayList<ChessCoordinate> pieceLocs = getPiecesForColor(color);
		ArrayList<Move> moves = new ArrayList<Move>();
		
		ValidMoveGenerator validMoves = new ValidMoveGenerator(ValidMoveGenerator.VALIDATE_CHECK);
	
		for (ChessCoordinate c: pieceLocs) {
			moves.addAll(validMoves.getValidMoves(ChessGameType.STANDARD_CHESS, turnState.getCurrPlayer(), board.getPiece(c).getType(), c, board, ValidMoveGenerator.VALIDATE_CHECK));
			
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
}

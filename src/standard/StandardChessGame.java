package standard;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGame;
import common.ChessPiece;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.GameState;
import common.MoveResult;
import strategies.StandardEndTurnStrategy;
import utilities.CoordinateUtilities;
import validation.EndTurnValidator;
import validation.PreTurnValidator;
import validation.exception.EndTurnValidationException;

public class StandardChessGame implements ChessGame {
	StandardBoard board;
	GameState turnState;

	PreTurnValidator preTurnValidator;
	EndTurnValidator endTurnValidator;

	StandardEndTurnStrategy gameStatusResolver;

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

		preTurnValidator.validate(from, to, board, turnState.getCurrTurn(), pieceType);

		board.movePiece(new StandardPiece(turnState.getCurrTurn(), pieceType), to, from);

		endTurnValidator.validate(from, to, board);

		turnState.updateStateFromSingleMove();

		return gameStatusResolver.getResult();
	}

	@Override
	public ChessPiece getPieceAt(ChessCoordinate where) {
		return board.getPiece(where);
	}

	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
}

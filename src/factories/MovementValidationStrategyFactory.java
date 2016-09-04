package factories;

import common.ChessCoordinate;
import common.ChessGameType;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.StandardCoordinate;
import strategies.StandardMovementValidationStrategy;
import strategies.standardpiece.StandardBishopMovementStrategy;
import strategies.standardpiece.KingMovementStrategy;
import strategies.standardpiece.StandardKnightMovementStrategy;
import strategies.standardpiece.StandardPawnMovementStrategy;
import strategies.standardpiece.StandardQueenMovementStrategy;
import strategies.standardpiece.StandardRookMovementStrategy;

public class MovementValidationStrategyFactory {

	private static final MovementValidationStrategyFactory instance = new MovementValidationStrategyFactory();

	private MovementValidationStrategyFactory() {}

	public static MovementValidationStrategyFactory getInstance() {
		return instance;
	}

	public StandardMovementValidationStrategy getStrategy(ChessGameType gameType, ChessPieceType piece, ChessCoordinate to, ChessCoordinate from, StandardBoard board) {
		StandardMovementValidationStrategy strategy = null;

		switch (gameType) {
		case STANDARD_CHESS:
			strategy = getStandardStrategy(piece, to, from, board);
			break;
		}

		return strategy;
	}

	private StandardMovementValidationStrategy getStandardStrategy(ChessPieceType piece, ChessCoordinate to, ChessCoordinate from, StandardBoard board) {

		StandardMovementValidationStrategy strategy = null;

		switch (piece) {
		case PAWN:
			strategy = new StandardPawnMovementStrategy();
			break;
		case KNIGHT:
			strategy = new StandardKnightMovementStrategy();
			break;
		case BISHOP:
			strategy = new StandardBishopMovementStrategy(to, from, board);
			break;
		case ROOK:
			strategy = new StandardRookMovementStrategy();
			break;
		case QUEEN:
			strategy = new StandardQueenMovementStrategy();
			break;
		case KING:
			strategy = new KingMovementStrategy(to, from, board);
			break;
		}

		return strategy;
	}



}

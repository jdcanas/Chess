package factories;

import common.ChessGameType;
import common.ChessPieceType;
import strategies.MovementValidationStrategy;
import strategies.standardpiece.StandardBishopMovementStrategy;
import strategies.standardpiece.StandardKingMovementStrategy;
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

	public MovementValidationStrategy getStrategy(ChessGameType gameType, ChessPieceType piece) {
		MovementValidationStrategy strategy = null;

		switch (gameType) {
		case STANDARD_CHESS:
			strategy = getStandardStrategy(piece);
			break;
		}

		return strategy;
	}

	private MovementValidationStrategy getStandardStrategy(ChessPieceType piece) {

		MovementValidationStrategy strategy = null;

		switch (piece) {
		case PAWN:
			strategy = new StandardPawnMovementStrategy();
			break;
		case KNIGHT:
			strategy = new StandardKnightMovementStrategy();
			break;
		case BISHOP:
			strategy = new StandardBishopMovementStrategy();
			break;
		case ROOK:
			strategy = new StandardRookMovementStrategy();
			break;
		case QUEEN:
			strategy = new StandardQueenMovementStrategy();
			break;
		case KING:
			strategy = new StandardKingMovementStrategy();
			break;
		}

		return strategy;
	}



}

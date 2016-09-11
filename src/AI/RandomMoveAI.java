package AI;

import java.util.ArrayList;
import java.util.Random;

import AI.exception.MoveMadeForWrongColor;
import common.ChessException;
import common.ChessPlayerColor;
import common.Move;
import standard.StandardChessGame;
import utilities.ValidMoveGenerator;
import utilities.ValidMoveGeneratorController;
import validation.exception.MoveMadeAfterGameClosedException;

public class RandomMoveAI extends AbstractAI {
	
	public RandomMoveAI(StandardChessGame game, ChessPlayerColor color) {
		super(game, color);
	}

	@Override
	public Move getAIMove() throws ChessException {
		validateMove();
		
		ArrayList<Move> validMoves = ValidMoveGeneratorController.getInstance().getMovesForColor(color, game.getBoard(), ValidMoveGenerator.VALIDATE_CHECK);
		return validMoves.get(getRandInt(validMoves.size()));   
	}
	
	public void validateMove() throws ChessException {
		if (game.isGameClosed()) {
			throw new MoveMadeAfterGameClosedException("This game is closed");
		}
		if (game.getTurn() != color) {
			throw new MoveMadeForWrongColor("This AI is playing as " + color);
		}
	}
	
	public static int getRandInt(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}
	
}

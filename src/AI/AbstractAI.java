package AI;

import common.ChessException;
import common.ChessPlayerColor;
import common.Move;
import standard.StandardChessGame;

public abstract class AbstractAI {

	StandardChessGame game;
	ChessPlayerColor color;

	public AbstractAI(StandardChessGame game, ChessPlayerColor color) {
		this.game = game;
		this.color = color;
	}
	
	public abstract Move getAIMove() throws ChessException;
	
}

package common;

import standard.StandardChessGame;

public class ChessGameFactory {

	private static final ChessGameFactory INSTANCE = new ChessGameFactory();
	
	private ChessGameFactory() {}
	
	public static ChessGameFactory getInstance() {
		return INSTANCE;
	}
	
	public ChessGame makeChessGame(ChessGameType gameType, ChessPlayerColor movesFirst) {
		ChessGame game = new StandardChessGame(movesFirst);
		
		switch (gameType) {
			case STANDARD_CHESS:
				game = new StandardChessGame(movesFirst);
				break;
		}
		
		return game;
	}
	
}

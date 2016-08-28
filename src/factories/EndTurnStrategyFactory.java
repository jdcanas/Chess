package factories;

import common.ChessGameType;
import standard.StandardBoard;
import strategies.StandardEndTurnStrategy;

public class EndTurnStrategyFactory {

	private static EndTurnStrategyFactory instance = new EndTurnStrategyFactory();
	
	private EndTurnStrategyFactory() {}
	
	public StandardEndTurnStrategy getStrategy(ChessGameType gameType, StandardBoard board) {
		
		StandardEndTurnStrategy strategy = new StandardEndTurnStrategy(board);
		
		switch (gameType) {
		case STANDARD_CHESS:
			strategy = new StandardEndTurnStrategy(board);
		}
		
		return strategy;
		
	}
	
}

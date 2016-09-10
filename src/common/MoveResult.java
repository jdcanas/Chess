package common;

public enum MoveResult
{
	OK, WHITE_WINS, BLACK_WINS, DRAW, WHITE_IN_CHECK, BLACK_IN_CHECK;
	
	public static MoveResult getCheckResultForColor(ChessPlayerColor color) {
		if (color == ChessPlayerColor.BLACK) {
			return BLACK_IN_CHECK;
		} else {
			return WHITE_IN_CHECK;
		}
	}
	
	public static MoveResult getWinResultForColor(ChessPlayerColor color) {
		if (color == ChessPlayerColor.BLACK) {
			return BLACK_WINS;
		} else {
			return WHITE_WINS;
		}
	}
}

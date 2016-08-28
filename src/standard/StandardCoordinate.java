package standard;

import common.ChessCoordinate;

public class StandardCoordinate implements ChessCoordinate {

	private int x = 0;
	private int y = 0;
	
	public StandardCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static StandardCoordinate make(int x, int y) {
		return new StandardCoordinate(x, y);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	
}

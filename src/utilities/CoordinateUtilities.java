package utilities;

import java.util.ArrayList;
import java.util.List;

import common.ChessCoordinate;
import standard.StandardCoordinate;

public class CoordinateUtilities {
	//when finding paths we must ignore the space the piece lands on because
	//pieces can be taken and landing on your own piece is checked elsewhere
	public static final int IGNORE_FINAL_SPACE_OFFSET = 1;

	public static ArrayList<ChessCoordinate> getDiagonalPath(ChessCoordinate to, ChessCoordinate from) {
		ArrayList<ChessCoordinate> path = new ArrayList<ChessCoordinate>();
		int xDistance, yDistance;
		int xDirection, yDirection;
		int xNew, yNew;
		
		xDistance = to.getX() - from.getX();
		yDistance = to.getY() - from.getY();
		
		
		//Gets the sign (aka direction with respect to the coordinate plane of the board)
		xDirection = getSign(xDistance);
		yDirection = getSign(yDistance);
		
		xDistance = Math.abs(xDistance);
	
		for (int i = 1; i < xDistance; i++) {
			xNew = from.getX() + (i * xDirection);
			yNew = from.getY() + (i * yDirection);
			path.add(new StandardCoordinate(xNew,yNew));
		}
		
		return path;
	}


	public static ArrayList<ChessCoordinate> getHorizontalPath(ChessCoordinate to, ChessCoordinate from) {
		ArrayList<ChessCoordinate> path = new ArrayList<ChessCoordinate>();
		int xDistance, yDistance, distance;
		int xNew, yNew, xIncrease, yIncrease;
		int xDirection, yDirection;
		
		xDistance = to.getX() - from.getX();
		yDistance = to.getY() - from.getY();
		
		xDirection = getSign(xDistance);
		yDirection = getSign(yDistance);
		
		distance = xDistance != 0 ? xDistance : yDistance;
		distance = Math.abs(distance);
		
		for (int i = 1; i < distance; i++) {
			xIncrease = xDistance != 0 ? i : 0;
			yIncrease = yDistance != 0 ? i : 0;
			
			xNew = from.getX() + xIncrease * xDirection;
			yNew = from.getY() + yIncrease * yDirection;
			path.add(new StandardCoordinate(xNew, yNew));
		}
		
		return path;
	}	
	
	public static ArrayList<ChessCoordinate> getValidCoordinates() {
		ArrayList<ChessCoordinate> coordinates = new ArrayList<ChessCoordinate>();
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				coordinates.add(new StandardCoordinate(x, y));
			}
		}
		
		return coordinates;
	}
	
	public static ArrayList<ChessCoordinate> getAdjacentCoordinates(ChessCoordinate center) {
		ArrayList<ChessCoordinate> coordinates = new ArrayList<ChessCoordinate>();
		StandardCoordinate newCoordinate;
		int newX, newY;

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				newX = center.getX() + x;
				newY = center.getY() + y;
				newCoordinate = StandardCoordinate.make(newX, newY);
				
				if (getValidCoordinates().contains(newCoordinate) &&
						!(x == 0 && y == 0)) {
					coordinates.add(newCoordinate);
				}
			}
		}
		
		return coordinates;
	}
	
	private static int getSign(int num) {
		int sign = (int) Math.signum(num);
		sign = sign == 0 ? 1 : sign;
		
		return sign;
	}
	
	private static int getOppositeSign(int num) {
		return getSign(num) == 1 ? -1 : 1;
	}
}

package standard;

import java.util.ArrayList;
import java.util.List;

import common.ChessCoordinate;

public class StandardCoordinate implements ChessCoordinate {

	private int x = 0;
	private int y = 0;

	
	public StandardCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public StandardCoordinate(ChessCoordinate where) {
		this.x = where.getX();
		this.y = where.getY();
	}
	
	
	public static StandardCoordinate make(int x, int y) {
		return new StandardCoordinate(x, y);
	}
	
	public static StandardCoordinate make(char x, int y) {
		int convertedX = Character.toLowerCase(x) - 97;
		return new StandardCoordinate(convertedX, y);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the list of coordinates adjacent to the given coordinate
	 * @return the list of adjacent coordinates
	 */
	public List<ChessCoordinate> getAdjacencyList() {
		int dx, dy;
		List<ChessCoordinate> adjacencyList = new ArrayList<ChessCoordinate>();

		for (dx = -1; dx <= 1; dx++) {
			for (dy = -1; dy <= 1; dy++) {
				if (dx == dy) {
					continue;
				} else {
					adjacencyList.add(StandardCoordinate.make(x + dx, y + dy));
				}
			}
		}

		return adjacencyList;
	}
	
	/**
	 * Checks if a coordinate is adjacent to another coordinate
	 * @param where coordinate to check
	 * @return true if the two coordinate are adjacent
	 */
	public boolean isAdjacentTo(StandardCoordinate where) {
		return where.getAdjacencyList().contains(this);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardCoordinate other = (StandardCoordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}

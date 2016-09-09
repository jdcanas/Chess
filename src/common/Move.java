package common;

public class Move {
	public ChessCoordinate to;
	public ChessCoordinate from;
	public ChessPieceType piece;

	public Move(ChessCoordinate to, ChessCoordinate from, ChessPieceType piece) {
		this.to = to;
		this.from = from;
		this.piece = piece;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		Move other = (Move) obj;
		if (piece != other.piece)
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
	
	public String toString() {
		return "To: " + to.toString() + "From " + from.toString() + " Piece: " + piece.getPrintableName();
	}
}

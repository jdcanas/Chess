package standard;

import common.ChessPiece;
import common.ChessPieceType;
import common.ChessPlayerColor;

public class StandardPiece implements ChessPiece {

	private ChessPlayerColor playerColor;
	private ChessPieceType pieceType;
	
	public StandardPiece(ChessPlayerColor playerColor, ChessPieceType pieceType) {
		this.playerColor = playerColor;
		this.pieceType = pieceType;
	}

	@Override
	public ChessPlayerColor getColor() {
		return playerColor;
	}

	@Override
	public ChessPieceType getType() {
		return pieceType;
	}
	
	public String toString() {
		return playerColor.getSymbol() + "_" + pieceType.getSymbol();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardPiece other = (StandardPiece) obj;
		if (pieceType != other.pieceType)
			return false;
		if (playerColor != other.playerColor)
			return false;
		return true;
	}
 
}

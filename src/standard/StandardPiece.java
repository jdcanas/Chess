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

}

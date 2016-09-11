package common;

import java.util.ArrayList;

public enum ChessPieceType
{
	PAWN("pawn", "P"), 
	KNIGHT("knight", "H"), 
	BISHOP("bishop", "B"), 
	ROOK("rook", "R"), 
	QUEEN("queen", "Q"), 
	KING("king", "K");

	private final String printableName;
	private final String symbol;

	/**
	 * The constructor for each enumerable item sets up the state so that
	 * the symbol for each item and the printable name are set up.
	 * 
	 * @param printableName the value returned from toString
	 * @param symbol a one character string that can be used when printing the board.
	 */
	private ChessPieceType(String printableName, String symbol)
	{
		this.printableName = printableName;
		this.symbol = symbol;
	}

	public static ChessPieceType stringToPieceType(String piece) throws ChessException {
		piece = piece.toLowerCase();
		
		if (piece.equals(PAWN.getPrintableName().toLowerCase())) {
			return PAWN;
		} else if (piece.equals(KNIGHT.getPrintableName().toLowerCase())) {
			return KNIGHT;
		} else if (piece.equals(BISHOP.getPrintableName().toLowerCase())) {
			return BISHOP;
		} else if (piece.equals(ROOK.getPrintableName().toLowerCase())) {
			return ROOK;
		} else if (piece.equals(QUEEN.getPrintableName().toLowerCase())) {
			return QUEEN;
		} else if (piece.equals(KING.getPrintableName().toLowerCase())) {
			return KING;
		} else {
			throw new ChessException("Piece does not exist " + piece);
		}

	}

	public static ArrayList<String> pieceNames() {
		ChessPieceType[] pieceArr = ChessPieceType.values();

		ArrayList<String> pieces = new ArrayList<String>();
		for (ChessPieceType piece : pieceArr) {
			pieces.add(piece.toString().toLowerCase());
		}
		return pieces;
	}

	/**
	 * @return the printableName
	 */
	public String getPrintableName()
	{
		return printableName;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol()
	{
		return symbol;
	}

	@Override
	public String toString()
	{
		return printableName;
	}
}

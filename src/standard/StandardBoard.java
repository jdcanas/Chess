package standard;

import java.util.HashMap;

import common.ChessCoordinate;
import common.ChessPieceType;
import common.ChessPlayerColor;

public class StandardBoard {
	
	public static final int BOARD_SIZE_X = 8;
	public static final int BOARDS_IZE_Y = 8;

	private HashMap<ChessCoordinate, StandardPiece> board;
	
	public StandardBoard() {
		board = initStandardBoard();
	}
	
	public void movePiece(StandardPiece piece, ChessCoordinate to) {
		board.put(to, piece);
	}
	
	public StandardPiece getPiece(ChessCoordinate from) {
		return board.get(from);
	}
	
	public static HashMap<ChessCoordinate, StandardPiece> initStandardBoard() {
		HashMap<ChessCoordinate, StandardPiece> newBoard = new HashMap<ChessCoordinate, StandardPiece>();
		
		StandardPiece whitePawn = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.PAWN);
		StandardPiece blackPawn = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.PAWN);
		StandardPiece whiteKnight = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KNIGHT);
		StandardPiece blackKnight = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KNIGHT);
		StandardPiece whiteBishop = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.BISHOP);
		StandardPiece blackBishop = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.BISHOP);
		StandardPiece whiteRook = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.ROOK);
		StandardPiece blackRook = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.ROOK);
		StandardPiece whiteQueen = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.QUEEN);
		StandardPiece blackQueen = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.QUEEN);
		StandardPiece whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		StandardPiece blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		
		for (int i = 0; i < BOARD_SIZE_X; i++) {
			newBoard.put(StandardCoordinate.make(i,1), whitePawn);
		}
		
		for (int i = 0; i < BOARD_SIZE_X; i++) {
			newBoard.put(StandardCoordinate.make(i,6), blackPawn);
		}
		
		newBoard.put(StandardCoordinate.make(1,0), whiteKnight);
		newBoard.put(StandardCoordinate.make(6,0), whiteKnight);
		newBoard.put(StandardCoordinate.make(1,7), blackKnight);
		newBoard.put(StandardCoordinate.make(6,7), whiteKnight);
		
		newBoard.put(StandardCoordinate.make(2,0), whiteBishop);
		newBoard.put(StandardCoordinate.make(5,0), whiteBishop);
		newBoard.put(StandardCoordinate.make(2,7), blackBishop);
		newBoard.put(StandardCoordinate.make(5,7), blackBishop);
		
		newBoard.put(StandardCoordinate.make(0,0), whiteRook);
		newBoard.put(StandardCoordinate.make(7,0), whiteRook);
		newBoard.put(StandardCoordinate.make(0,7), blackRook);
		newBoard.put(StandardCoordinate.make(7,7), blackRook);
		
		newBoard.put(StandardCoordinate.make(3,0), whiteQueen);
		newBoard.put(StandardCoordinate.make(3,7), blackQueen);
		
		newBoard.put(StandardCoordinate.make(4,0), whiteKing);
		newBoard.put(StandardCoordinate.make(4,7), blackKing);
	
		return newBoard;
	}
	
}

package standard;

import java.util.ArrayList;
import java.util.HashMap;

import common.ChessCoordinate;
import common.ChessPieceType;
import common.ChessPlayerColor;
import utilities.CoordinateUtilities;
import validation.exception.BoardOutOfBoundsException;
import validation.exception.KingNotFoundException;

public class StandardBoard {

	private ArrayList<ChessCoordinate> pieceLocList = new ArrayList<ChessCoordinate>();

	public static final int BOARD_SIZE_X = 8;
	public static final int BOARD_SIZE_Y = 8;

	private HashMap<ChessCoordinate, StandardPiece> board;

	public StandardBoard() {
		board = initStandardBoard();
		pieceLocList = buildPieceLocList(board);
	}

	public StandardBoard(HashMap<ChessCoordinate, StandardPiece> startingBoard) {
		this.board = startingBoard; 
		pieceLocList = buildPieceLocList(board);
	}

	public void movePiece(StandardPiece piece, ChessCoordinate to, ChessCoordinate from) throws BoardOutOfBoundsException {
		if (!CoordinateUtilities.getValidCoordinates().contains(to)) {
			throw new BoardOutOfBoundsException("The coordinate " + to.toString() + " is out of bounds");
		}

		board.put(to, piece);
		pieceLocList.add(to);

		board.remove(from, piece);
		pieceLocList.remove(to);
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

	public ArrayList<ChessCoordinate> buildPieceLocList(HashMap<ChessCoordinate, StandardPiece> currBoardData) {
		ArrayList<ChessCoordinate> startingLocs = new ArrayList<ChessCoordinate>();

		for (ChessCoordinate c: currBoardData.keySet()) {
			startingLocs.add(c);
		}
		return startingLocs;
	}

	public ArrayList<ChessCoordinate> getPieceLocations() {
		return pieceLocList;
	}

	public ChessCoordinate getKingLocation(ChessPlayerColor currPlayerColor) throws KingNotFoundException {
		StandardPiece currPiece;
		ChessCoordinate kingLoc = null;

		for (ChessCoordinate c: pieceLocList) {
			currPiece = board.get(c);
			if (currPiece.getType() == ChessPieceType.KING && currPiece.getColor() == currPlayerColor) {
				kingLoc = c;
				break;
			}
		}

		if (kingLoc == null) {
			throw new KingNotFoundException("Was unable to find the king\n" + getPrintableBoard());
		}
		
		return kingLoc;
	}

	public String getPrintableBoard() {
		String printableBoard = "----A-----B-----C-----D-----E-----F-----G-----H---";
		String endingRow    = "\n -------------------------------------------------";

		String emptySquare    = "     |";
		String squareWithPiece  = " %s |";
		StandardPiece currPiece;
		StandardCoordinate currCoordinate;

		for (int y = 7; y >= 0; y--) {
			printableBoard += "\n" + Integer.toString(y + 1) + "|"; //Begin a new row

			for (int x = 7; x >= 0; x--) {
				currCoordinate = new StandardCoordinate(x, y);
				currPiece = board.get(currCoordinate); 

				if (currPiece != null) {
					printableBoard += String.format(squareWithPiece, currPiece.toString());
				} else {
					printableBoard += emptySquare;
				}
			}
		}

		printableBoard += endingRow;

		return printableBoard;
	}

}

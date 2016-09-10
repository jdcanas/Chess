package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGameType;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.GameState;
import common.Move;
import common.MoveArray;
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardCoordinate;
import standard.StandardPiece;
import utilities.CoordinateUtilities;
import utilities.ValidMoveGenerator;
import validation.CheckValidator;

public class UtilityTest {
	HashMap<ChessCoordinate, StandardPiece> emptyBoard;
	StandardPiece whiteKing;
	StandardPiece blackKing;
	StandardCoordinate to, from = StandardCoordinate.make('c', 7);
	StandardChessGame game;
	StandardBoard board;
	GameState state;
	
	@Before
	public void setUp() throws Exception {
		board = new StandardBoard(StandardBoard.initStandardBoard());
		state = new GameState(ChessPlayerColor.WHITE);
		game = new StandardChessGame(board, state);
		emptyBoard = new HashMap<ChessCoordinate, StandardPiece>();                          
		blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
	}

	@Test
	public void testLetterCoordinate() {
		StandardCoordinate c = StandardCoordinate.make('a',0);
		emptyBoard.put(from, whiteKing);
		
		assertEquals(0,c.getX());
	}
	
	@Test
	public void testGetAdjacentCoordinates() {
		emptyBoard.put(from, whiteKing);
		
		ChessCoordinate center = StandardCoordinate.make('e',4);
		ArrayList<ChessCoordinate> adjacencyList = CoordinateUtilities.getAdjacentCoordinates(center);
		
		
		assertEquals(8, adjacencyList.size());
	}
	
	@Test
	public void testGetHorizontalPath() {
		emptyBoard.put(from, whiteKing);
		ChessCoordinate from = StandardCoordinate.make('a', 7);
		ChessCoordinate to = StandardCoordinate.make('a', 0);
		
		ArrayList<ChessCoordinate> horizontalPath = CoordinateUtilities.getHorizontalPath(to, from);
		
		assertEquals(6, horizontalPath.size());
	}
	
	@Test
	public void testGetHorizontalPath_2() {
		emptyBoard.put(from, whiteKing);
		ChessCoordinate from = StandardCoordinate.make('a', 7);
		ChessCoordinate to = StandardCoordinate.make('a', 5);
		
		ArrayList<ChessCoordinate> horizontalPath = CoordinateUtilities.getHorizontalPath(to, from);

		assertEquals(1, horizontalPath.size());
		assertTrue(horizontalPath.contains(StandardCoordinate.make('a', 6)));
	}
	
	@Test
	public void testGetDiagonalPath_2() {
		emptyBoard.put(from, whiteKing);
		ChessCoordinate from = StandardCoordinate.make('a', 7);
		ChessCoordinate to = StandardCoordinate.make('c', 5);
		
		ArrayList<ChessCoordinate> diagonalPath = CoordinateUtilities.getDiagonalPath(to, from);

		assertEquals(1, diagonalPath.size());

		assertTrue(diagonalPath.contains(StandardCoordinate.make('b', 6)));
	}
	
	@Test
	public void testGetDiagonalPath_3() {
		emptyBoard.put(from, whiteKing);
		ChessCoordinate from = StandardCoordinate.make('c', 5);
		ChessCoordinate to = StandardCoordinate.make('a', 7);
		
		ArrayList<ChessCoordinate> diagonalPath = CoordinateUtilities.getDiagonalPath(to, from);

		assertEquals(1, diagonalPath.size());
		assertTrue(diagonalPath.contains(StandardCoordinate.make('b', 6)));
	}
	
	@Test
	public void testValidMoveGenerator() throws ChessException {
		emptyBoard.put(from, whiteKing);
		ArrayList<ChessCoordinate> knownValidMoves = CoordinateUtilities.getAdjacentCoordinates(from);
		knownValidMoves.remove(StandardCoordinate.make('b', 7));
		knownValidMoves.remove(StandardCoordinate.make('b', 6));		
		
		from = StandardCoordinate.make('c', 7);
		board = new StandardBoard(emptyBoard);

		ValidMoveGenerator moveGenerator = new ValidMoveGenerator(
				ChessGameType.STANDARD_CHESS, ChessPlayerColor.WHITE, ChessPieceType.KING, from, board, false);
		
		assertEquals(whiteKing, board.getPiece(from));
		assertEquals(2, board.getPieceLocations().size());
		assertEquals(3, moveGenerator.getValidMoves().size());
		
	}
	
	@Test
	public void testValidMoveGenerator_King_Check_Validator() throws ChessException {
		from = StandardCoordinate.make('b', 7);
		emptyBoard.put(from, whiteKing);
		board = new StandardBoard(emptyBoard);
		game = new StandardChessGame(board, state);
		
		ValidMoveGenerator moveGenerator = new ValidMoveGenerator(
				ChessGameType.STANDARD_CHESS, ChessPlayerColor.WHITE, ChessPieceType.KING, from, board, ValidMoveGenerator.DONT_VALIDATE_CHECK);
		
		assertEquals(whiteKing, board.getPiece(from));
		assertEquals(2, board.getPieceLocations().size());

		assertEquals(5, moveGenerator.getValidMoves().size());
		
	}
	
	@Test
	public void testValidMoveGenerator_PAWN() throws ChessException {
		StandardPiece whitePawn = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.PAWN);
		from = StandardCoordinate.make('a', 1);
		board = new StandardBoard(StandardBoard.initStandardBoard());

		ValidMoveGenerator moveGenerator = new ValidMoveGenerator(
				ChessGameType.STANDARD_CHESS, ChessPlayerColor.WHITE, ChessPieceType.PAWN, from, board, ValidMoveGenerator.DONT_VALIDATE_CHECK);
		
		
		
		assertEquals(whitePawn, board.getPiece(from));
		assertEquals(2, moveGenerator.getValidMoves().size());
		
		MoveArray moves = new MoveArray(moveGenerator.getValidMoves());
		ArrayList<ChessCoordinate> moveCoordinates = moves.getToList();
		
		assertTrue(moveCoordinates.contains(StandardCoordinate.make('a', 2)));
		assertTrue(moveCoordinates.contains(StandardCoordinate.make('a', 3)));
	}

}

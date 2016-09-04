package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.GameState;
import common.MoveResult;
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardCoordinate;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;
import validation.exception.MovementValidationException;

public class BishopMovementTest {
	
	HashMap<ChessCoordinate, StandardPiece> emptyBoard;
	StandardPiece whiteBishop, whiteKing;
	StandardPiece blackBishop, blackKing;
	StandardCoordinate to, from;
	StandardChessGame game;
	StandardBoard board;
	GameState state;

	@Before
	public void setUp() throws Exception {
		board = new StandardBoard(StandardBoard.initStandardBoard());
		state = new GameState(ChessPlayerColor.WHITE);
		game = new StandardChessGame(board, state);
		emptyBoard = new HashMap<ChessCoordinate, StandardPiece>();                                                            
		whiteBishop = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.BISHOP);
		blackBishop = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.BISHOP);
		blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(StandardCoordinate.make('c', 7), whiteKing);
	}

	@Test
	public void testInvalidBishopDirection_1() throws ChessException {
		from = StandardCoordinate.make('c', 0);
		to = StandardCoordinate.make('c', 2);
		
		try {
			game.makeMove(ChessPieceType.BISHOP, from, to);
			fail();
		} catch (MovementValidationException e) {
			assertEquals(StandardMovementValidationStrategy.formatErrorMsg(ChessPieceType.BISHOP, from), 
					e.getMessage());
		}
	}
	
	@Test
	public void testInvalidBishopDirection_2() throws ChessException {
		from = StandardCoordinate.make('c', 0);
		to = StandardCoordinate.make('b', 2);
		
		try {
			game.makeMove(ChessPieceType.BISHOP, from, to);
			fail();
		} catch (MovementValidationException e) {
			assertEquals(StandardMovementValidationStrategy.formatErrorMsg(ChessPieceType.BISHOP, from), 
					e.getMessage());
		}
	}
	
	@Test
	public void testValidBishopMovement_1() throws ChessException {
		from = StandardCoordinate.make('e', 4);
		to = StandardCoordinate.make('a', 0);
		emptyBoard.put(from, whiteBishop);
		
		board = new StandardBoard(emptyBoard);
		
		StandardChessGame game = new StandardChessGame(board, state);
		assertTrue(game.makeMove(ChessPieceType.BISHOP, from, to) == MoveResult.OK);
		assertEquals(whiteBishop, game.getPieceAt(to));
	}
	
	@Test
	public void testValidBishopMovement_2() throws ChessException {
		from = StandardCoordinate.make('c', 0);
		to = StandardCoordinate.make('h', 5);
		emptyBoard.put(from, whiteBishop);
		
		board = new StandardBoard(emptyBoard);
		
		StandardChessGame game = new StandardChessGame(board, state);
		assertTrue(game.makeMove(ChessPieceType.BISHOP, from, to) == MoveResult.OK);
		assertEquals(whiteBishop, game.getPieceAt(to));
	}
	
	@Test
	public void testValidBishopMovement_3() throws ChessException {
		from = StandardCoordinate.make('e', 4);
		to = StandardCoordinate.make('h', 1);
		emptyBoard.put(from, whiteBishop);
		
		board = new StandardBoard(emptyBoard);
		
		StandardChessGame game = new StandardChessGame(board, state);
		assertTrue(game.makeMove(ChessPieceType.BISHOP, from, to) == MoveResult.OK);
		assertEquals(whiteBishop, game.getPieceAt(to));
	}

}

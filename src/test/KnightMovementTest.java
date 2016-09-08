package test;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.GameState;
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardCoordinate;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;
import validation.PreTurnValidator;
import validation.exception.MovementValidationException;
import validation.exception.PreTurnValidationException;

public class KnightMovementTest {
	StandardPiece whiteKnight;
	StandardCoordinate to, from = StandardCoordinate.make('b', 0);
	StandardChessGame game;
	StandardBoard board;
	GameState state;

	@Before
	public void setUp() throws Exception {
		board = new StandardBoard(StandardBoard.initStandardBoard());
		state = new GameState(ChessPlayerColor.WHITE);
		game = new StandardChessGame(board, state);                                                           
		whiteKnight = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KNIGHT);
	}
	
	@Test
	public void testBasicValidKnightMovement() throws ChessException {
		to = StandardCoordinate.make('a', 2);
		
		assertEquals(whiteKnight, board.getPiece(from));
		game.makeMove(ChessPieceType.KNIGHT, from, to);
		assertEquals(whiteKnight, board.getPiece(to));
	}
	
	@Test
	public void testBasicValidKnightMovement_2() throws ChessException {
		to = StandardCoordinate.make('c', 2);
		
		assertEquals(whiteKnight, board.getPiece(from));
		game.makeMove(ChessPieceType.KNIGHT, from, to);
		assertEquals(whiteKnight, board.getPiece(to));
	}
	
	@Test
	public void testInvalidKnightMovement_2() throws ChessException {
		to = StandardCoordinate.make('b', 2);
		
		assertEquals(whiteKnight, board.getPiece(from));
		try {
			game.makeMove(ChessPieceType.KNIGHT, from, to);
			fail();
		} catch (MovementValidationException e) {
			assertEquals(StandardMovementValidationStrategy.formatMovementErrorMsg(ChessPieceType.KNIGHT, from),
					e.getMessage());
		}
	}
	
	@Test
	public void testInvalidKnightMovement_1() throws ChessException {
		to = StandardCoordinate.make('d', 3);
		
		assertEquals(whiteKnight, board.getPiece(from));
		try {
			game.makeMove(ChessPieceType.KNIGHT, from, to);
			fail();
		} catch (MovementValidationException e) {
			assertEquals(StandardMovementValidationStrategy.formatMovementErrorMsg(ChessPieceType.KNIGHT, from),
					e.getMessage());
		}
	}
	
	@Test
	public void testInvalidKnightMovement_4() throws ChessException {
		to = StandardCoordinate.make('d', 2);
		
		assertEquals(whiteKnight, board.getPiece(from));
		try {
			game.makeMove(ChessPieceType.KNIGHT, from, to);
			fail();
		} catch (MovementValidationException e) {
			assertEquals(StandardMovementValidationStrategy.formatMovementErrorMsg(ChessPieceType.KNIGHT, from),
					e.getMessage());
		}
	}
	
	@Test
	public void testInvalidKnightMovement_3() throws ChessException {
		to = StandardCoordinate.make('d', 0);
		
		assertEquals(whiteKnight, board.getPiece(from));
		try {
			game.makeMove(ChessPieceType.KNIGHT, from, to);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.YOUR_PIECE_IS_THERE,e.getMessage());
		}
	}
	
}

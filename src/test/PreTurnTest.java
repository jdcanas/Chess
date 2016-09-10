package test;

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
import validation.PreTurnValidator;
import validation.exception.BoardOutOfBoundsException;
import validation.exception.PreTurnValidationException;

import static org.junit.Assert.*;

public class PreTurnTest {

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
		whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		whiteBishop = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.BISHOP);
		blackBishop = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.BISHOP);
		blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
	}
	
	@Test(expected = BoardOutOfBoundsException.class)
	public void testOutOfBounds() throws BoardOutOfBoundsException {
		
		board.movePiece(whiteBishop, StandardCoordinate.make(-1,0), StandardCoordinate.make('c', 0));
		
	}
	
	@Test()
	public void testMoveNonExistentPiece() throws ChessException {
		to = StandardCoordinate.make('d', 4);
		from = StandardCoordinate.make('h', 5);
		
		StandardChessGame game = new StandardChessGame(board, state);
		
		try {
			game.makeMove(ChessPieceType.BISHOP, from, to);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.PIECE_IS_NOT_THERE,e.getMessage());
		}
	}
	
	@Test()
	public void testMoveWrongTypePiece() throws ChessException {
		from = StandardCoordinate.make('a', 0);
		to = StandardCoordinate.make('c', 1);
		
		StandardChessGame game = new StandardChessGame(board, state);
		
		try {
			game.makeMove(ChessPieceType.BISHOP, from, to);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.PIECE_IS_NOT_THERE,e.getMessage());
		}
	}
	
	@Test
	public void testMovePieceNotYours() throws ChessException {
		to = StandardCoordinate.make('c', 0);
		from = StandardCoordinate.make('h', 5);

		emptyBoard.put(to, blackBishop);
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(StandardCoordinate.make('d', 7), whiteKing);
		
		board = new StandardBoard(emptyBoard);
		
		StandardChessGame game = new StandardChessGame(board, state);
		try {
			game.makeMove(ChessPieceType.BISHOP, to, from);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.PIECE_NOT_YOURS, e.getMessage());
		}
		
	}
	
	@Test
	public void testToFromSame() throws ChessException {
		
		emptyBoard.put(StandardCoordinate.make('a', 1), whiteBishop);
		to = StandardCoordinate.make('c', 0);
		from = StandardCoordinate.make('c', 0);
		
		try {
			game.makeMove(ChessPieceType.BISHOP, from, to);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.SAME_TO_FROM, e.getMessage());
		}
	}
	
	@Test
	public void testToIsYourOwnPiece() throws ChessException {
		to = StandardCoordinate.make('d', 1);
		from = StandardCoordinate.make('c', 0);
		
		try {
			game.makeMove(ChessPieceType.BISHOP, from, to);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.YOUR_PIECE_IS_THERE, e.getMessage());
		}
	}
	
}

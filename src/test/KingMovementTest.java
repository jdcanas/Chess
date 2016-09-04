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
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardCoordinate;
import standard.StandardPiece;
import strategies.StandardMovementValidationStrategy;
import validation.KingValidator;
import validation.PreTurnValidator;
import validation.exception.KingNotFoundException;
import validation.exception.MovePutsKingInCheckException;
import validation.exception.MovementValidationException;
import validation.exception.PreTurnValidationException;

public class KingMovementTest {

	HashMap<ChessCoordinate, StandardPiece> emptyBoard;
	StandardPiece whiteBishop, whiteKing;
	StandardPiece blackBishop, blackKing;
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
		whiteBishop = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.BISHOP);
		blackBishop = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.BISHOP);
		blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(from, whiteKing);
	}

	@Test
	public void testKingLocation() throws KingNotFoundException {
		board = new StandardBoard(emptyBoard);
		
		ChessCoordinate kingLoc = board.getKingLocation(ChessPlayerColor.WHITE);
		
		assertEquals(from, kingLoc);
		
	}

	@Test
	public void testKingMovingToSquareWithFriendlyPiece() throws ChessException {
		from = StandardCoordinate.make('c', 7);
		to = StandardCoordinate.make('d', 6);
		emptyBoard.put(to, whiteBishop);

		board = new StandardBoard(emptyBoard);

		game = new StandardChessGame(board, state);

		try {
			game.makeMove(ChessPieceType.KING, from, to);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.YOUR_PIECE_IS_THERE, e.getMessage());
		}
	}

	@Test
	public void testKingMovingToValidSquare() throws ChessException {
		from = StandardCoordinate.make('c', 7);
		to = StandardCoordinate.make('d', 6);

		board = new StandardBoard(emptyBoard);

		game = new StandardChessGame(board, state);

		assertEquals(whiteKing, board.getPiece(from));

		game.makeMove(ChessPieceType.KING, from, to);

		assertEquals(whiteKing, board.getPiece(to));
	}

	@Test
	public void testKingMovingToInvalidSquare() throws ChessException {
		from = StandardCoordinate.make('c', 7);
		to = StandardCoordinate.make('e', 6);

		board = new StandardBoard(emptyBoard);

		game = new StandardChessGame(board, state);

		assertEquals(whiteKing, board.getPiece(from));

		try {
			game.makeMove(ChessPieceType.KING, from, to);
			fail();
		} catch (MovementValidationException e) {
			assertEquals(StandardMovementValidationStrategy.formatErrorMsg(ChessPieceType.KING, from),
					e.getMessage());
		}
	}
	
	@Test 
	public void putOwnKingInCheckByMovingKingTest() throws ChessException {
		ChessCoordinate blackBishopLoc = StandardCoordinate.make('e', 5);
		ChessCoordinate whiteBishopLoc = StandardCoordinate.make('d', 6);
		emptyBoard.put(blackBishopLoc, blackBishop);
		emptyBoard.put(whiteBishopLoc, whiteBishop);
		
		from = (StandardCoordinate) whiteBishopLoc;
		to = StandardCoordinate.make('c', 5);

		board = new StandardBoard(emptyBoard);

		game = new StandardChessGame(board, state);
	
		try {
			game.makeMove(ChessPieceType.BISHOP, from, to);
			fail();
		} catch (MovePutsKingInCheckException e) {
			assertEquals(KingValidator.MOVE_PUTS_KING_IN_CHECK,
					e.getMessage());
		}
	}
	
	@Test
	public void testMovingPieceToCauseOwnCheck() throws ChessException {
		from = StandardCoordinate.make('c', 7);
		
		to = StandardCoordinate.make('d', 6);
		emptyBoard.put(to, whiteBishop);

		board = new StandardBoard(emptyBoard);

		game = new StandardChessGame(board, state);

		try {
			game.makeMove(ChessPieceType.KING, from, to);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.YOUR_PIECE_IS_THERE, e.getMessage());
		}
	}


}

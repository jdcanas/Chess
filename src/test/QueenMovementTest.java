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
import validation.exception.MovePutsKingInCheckException;
import validation.exception.MovementValidationException;
import validation.exception.PreTurnValidationException;

public class QueenMovementTest {

	HashMap<ChessCoordinate, StandardPiece> emptyBoard;
	StandardPiece whiteQueen, whiteKing;
	StandardPiece blackQueen, blackKing;
	StandardCoordinate to, from;
	StandardChessGame game;
	StandardBoard board;
	GameState state;

	@Before
	public void setUp() throws Exception {
		state = new GameState(ChessPlayerColor.WHITE);
		from = StandardCoordinate.make('e',5);
		emptyBoard = new HashMap<ChessCoordinate, StandardPiece>();                                                            
		whiteQueen = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.QUEEN);
		blackQueen = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.QUEEN);
		blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(StandardCoordinate.make('c', 7), whiteKing);
		emptyBoard.put(from, whiteQueen);
		emptyBoard.put(StandardCoordinate.make('h',0), blackQueen);

		board = new StandardBoard(emptyBoard);
		game = new StandardChessGame(board, state);
		
	}

	@Test
	public void testValidHorizontalMovement() throws ChessException {
		to = StandardCoordinate.make('e', 1);
		
		assertTrue(board.getPiece(from).equals(whiteQueen));
		game.makeMove(ChessPieceType.QUEEN, from, to);
		assertNull(board.getPiece(from));
		assertTrue(board.getPiece(to).equals(whiteQueen));
	}
	
	@Test
	public void testValidDiagonalMovement() throws ChessException {
		to = StandardCoordinate.make('f', 4);
		
		assertTrue(board.getPiece(from).equals(whiteQueen));
		game.makeMove(ChessPieceType.QUEEN, from, to);
		assertNull(board.getPiece(from));
		assertTrue(board.getPiece(to).equals(whiteQueen));
	}
	
	@Test
	public void testInvalidQueenMovement() throws ChessException {
		to = StandardCoordinate.make('f', 3);
		
		assertTrue(board.getPiece(from).equals(whiteQueen));
		
		try {
			game.makeMove(ChessPieceType.QUEEN, from, to);
			fail();
		} catch (MovementValidationException e) {
			assertEquals(StandardMovementValidationStrategy.formatMovementErrorMsg(ChessPieceType.QUEEN, from),
					e.getMessage());
		}
	}
	
	@Test
	public void testInvalidQueenMovementPieceInWay() throws ChessException {
		StandardPiece whiteBishop = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.BISHOP);
		StandardCoordinate bishopLoc = StandardCoordinate.make('f', 4);		
		to = StandardCoordinate.make('g', 3);

		board.movePiece(whiteBishop, bishopLoc, StandardCoordinate.make(0, 0));

		assertTrue(board.getPiece(from).equals(whiteQueen));
		assertTrue(board.getPiece(bishopLoc).equals(whiteBishop));
		try {
			game.makeMove(ChessPieceType.QUEEN, from, to);
			fail();
		} catch (MovementValidationException e) {
			assertEquals(StandardMovementValidationStrategy.formatPieceInWayErrorMsg(from, to, bishopLoc),
					e.getMessage());
		}
	}
	
	@Test
	public void testInvalidQueenMovementYourPieceInWay_DestinationIsOneSquareAway() throws ChessException {
		StandardPiece whiteBishop = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.BISHOP);
		StandardCoordinate bishopLoc = StandardCoordinate.make('f', 4);		
		to = bishopLoc;

		board.movePiece(whiteBishop, bishopLoc, StandardCoordinate.make(0, 0));

		assertTrue(board.getPiece(from).equals(whiteQueen));
		assertTrue(board.getPiece(bishopLoc).equals(whiteBishop));
		try {
			game.makeMove(ChessPieceType.QUEEN, from, to);
			fail();
		} catch (PreTurnValidationException e) {
			assertEquals(PreTurnValidator.YOUR_PIECE_IS_THERE,
					e.getMessage());
		}
	}
	
	@Test
	public void testValidQueenCapture_DestinationIsOneSquareAway() throws ChessException {
		StandardPiece blackBishop = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.BISHOP);
		StandardCoordinate bishopLoc = StandardCoordinate.make('f', 4);		
		to = bishopLoc;

		board.movePiece(blackBishop, bishopLoc, StandardCoordinate.make(0, 0));

		assertTrue(board.getPiece(from).equals(whiteQueen));
		assertTrue(board.getPiece(bishopLoc).equals(blackBishop));
		assertEquals(5, board.getPieceLocations().size());
		game.makeMove(ChessPieceType.QUEEN, from, to);
		assertTrue(board.getPiece(to).equals(whiteQueen));
		assertEquals(4, board.getPieceLocations().size());
	}
	
	@Test
	public void testQueenMovePutsKingInCheck() throws ChessException {
		emptyBoard = new HashMap<ChessCoordinate, StandardPiece>();
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(StandardCoordinate.make('c', 5), whiteKing);
		
		from = StandardCoordinate.make('d', 4);
		emptyBoard.put(from, whiteQueen);
		emptyBoard.put(StandardCoordinate.make('h',0), blackQueen);

		board = new StandardBoard(emptyBoard);
		game = new StandardChessGame(board, state);
		to = StandardCoordinate.make('d', 7);
		
		assertTrue(board.getPiece(from).equals(whiteQueen));
		
		try {
			game.makeMove(ChessPieceType.QUEEN, from, to);
			fail();
		} catch (MovePutsKingInCheckException e) {
			assertEquals(KingValidator.MOVE_PUTS_KING_IN_CHECK,
					e.getMessage());
		}
	}

}

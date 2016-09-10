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
import utilities.ValidMoveGenerator;
import validation.CheckValidator;
import validation.exception.KingNotFoundException;
import validation.exception.MovePutsKingInCheckException;

public class EndTurnStrategyTest {

	HashMap<ChessCoordinate, StandardPiece> emptyBoard;
	StandardPiece whiteBishop, whiteKing, whiteQueen, whitePawn;
	StandardPiece blackBishop, blackKing, blackPawn;
	StandardCoordinate to, from = StandardCoordinate.make('b', 7);
	StandardChessGame game;
	StandardBoard board;
	GameState state;
	CheckValidator checkValidator;

	@Before
	public void setUp() throws Exception {
		board = new StandardBoard(StandardBoard.initStandardBoard());
		state = new GameState(ChessPlayerColor.WHITE);
		emptyBoard = new HashMap<ChessCoordinate, StandardPiece>();                                                            
		whiteBishop = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.BISHOP);                                       
		whitePawn = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.PAWN);

		blackPawn = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.PAWN);
		blackBishop = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.BISHOP);
		blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		whiteQueen = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.QUEEN);

		game = new StandardChessGame(board, state);

	}

	@Test
	public void testCheck() throws ChessException {
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(StandardCoordinate.make('b', 7), whiteKing);
		board = new StandardBoard(emptyBoard);

		checkValidator = new CheckValidator(board);
		assertTrue(checkValidator.isKingInCheck(ChessPlayerColor.BLACK));
		assertTrue(checkValidator.isKingInCheck(ChessPlayerColor.WHITE));
	}

	@Test
	public void testCheckMate() throws ChessException {
		from = StandardCoordinate.make('c', 5);
		to = StandardCoordinate.make('a', 5);
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(StandardCoordinate.make('c', 7), whiteKing);
		emptyBoard.put(to, whiteQueen);

		board = new StandardBoard(emptyBoard);
		state = new GameState(ChessPlayerColor.BLACK);
		game = new StandardChessGame(board, state);
		game.setPreviousResult(MoveResult.BLACK_IN_CHECK);

		MoveResult result = game.makeMove(ChessPieceType.KING, StandardCoordinate.make('a', 7), StandardCoordinate.make('b', 7));

		assertEquals(MoveResult.WHITE_WINS, result);
	}

	@Test
	public void testDraw() throws ChessException {
		from = StandardCoordinate.make('c', 5);
		to = StandardCoordinate.make('a', 5);
		emptyBoard.put(StandardCoordinate.make('a', 0), blackKing);
		emptyBoard.put(StandardCoordinate.make('c', 0), whiteKing);
		emptyBoard.put(StandardCoordinate.make('a', 1), blackPawn);

		board = new StandardBoard(emptyBoard);
		state = new GameState(ChessPlayerColor.BLACK);
		game = new StandardChessGame(board, state);
		game.setPreviousResult(MoveResult.OK);
		
		System.out.println(board.getPrintableBoard());

		MoveResult result = game.makeMove(ChessPieceType.KING, StandardCoordinate.make('a', 7), StandardCoordinate.make('b', 7));

		assertEquals(MoveResult.DRAW, result);
	}

}

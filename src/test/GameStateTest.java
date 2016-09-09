package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.GameState;
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardCoordinate;
import standard.StandardPiece;

public class GameStateTest {

	StandardPiece whiteKnight, blackKnight;
	StandardCoordinate to, to2, from2, from1 = StandardCoordinate.make('b', 0);
	StandardChessGame game;
	StandardBoard board;
	GameState state;

	@Before
	public void setUp() throws Exception {
		board = new StandardBoard(StandardBoard.initStandardBoard());
		state = new GameState(ChessPlayerColor.WHITE);
		game = new StandardChessGame(board, state);                                                           
		whiteKnight = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KNIGHT);
		blackKnight = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KNIGHT);
	}
	
	@Test
	public void testTurnColorAdvances() throws ChessException {
		to = StandardCoordinate.make('a', 2);
		
		assertEquals(ChessPlayerColor.WHITE, state.getCurrPlayer());
		assertEquals(0, state.getTurn());
		
		game.makeMove(ChessPieceType.KNIGHT, from1, to);
		
		assertEquals(ChessPlayerColor.BLACK, state.getCurrPlayer());
		assertEquals(0, state.getTurn());
	}
	
	@Test
	public void testTurnNumAdvances() throws ChessException {
		to = StandardCoordinate.make('a', 2);
		to2 = StandardCoordinate.make('a', 5);
		from2 = StandardCoordinate.make('b', 7);
		
		assertEquals(ChessPlayerColor.WHITE, state.getCurrPlayer());
		assertEquals(0, state.getTurn());
		
		game.makeMove(ChessPieceType.KNIGHT, from1, to);
		
		assertEquals(ChessPlayerColor.BLACK, state.getCurrPlayer());
		assertEquals(0, state.getTurn());
		
		game.makeMove(ChessPieceType.KNIGHT, from2, to2);
		
		assertEquals(ChessPlayerColor.WHITE, state.getCurrPlayer());
		assertEquals(1, state.getTurn());
		assertEquals(blackKnight, board.getPiece(to2));
		
		to = from1;
		from1 = StandardCoordinate.make('a', 2);
		
		game.makeMove(ChessPieceType.KNIGHT, from1, to);
		
	}

}

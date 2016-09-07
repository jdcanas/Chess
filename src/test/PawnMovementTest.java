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

public class PawnMovementTest {

	HashMap<ChessCoordinate, StandardPiece> emptyBoard;
	StandardPiece whitePawn;
	StandardPiece blackPawn;
	StandardCoordinate to, from;
	StandardChessGame game;
	StandardBoard board;
	GameState state;

	@Before
	public void setUp() throws Exception {
		from = StandardCoordinate.make('a', 1);
		board = new StandardBoard(StandardBoard.initStandardBoard());
		state = new GameState(ChessPlayerColor.WHITE);
		game = new StandardChessGame(board, state);
		emptyBoard = new HashMap<ChessCoordinate, StandardPiece>();
		whitePawn = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.PAWN);
		blackPawn = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.PAWN);
	}

	@Test
	public void testBasicPawnMovement() throws ChessException {
		to = StandardCoordinate.make('a', 3);
		
		assertEquals(board.getPiece(from), whitePawn);
		game.makeMove(ChessPieceType.PAWN, from, to);
		assertEquals(board.getPiece(to), whitePawn);
		
	}

}

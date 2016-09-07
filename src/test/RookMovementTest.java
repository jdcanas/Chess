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
import validation.KingValidator;
import validation.exception.MovePutsKingInCheckException;

public class RookMovementTest {

	HashMap<ChessCoordinate, StandardPiece> emptyBoard;
	StandardPiece whiteRook, whiteKing;
	StandardPiece blackRook, blackKing;
	StandardCoordinate to, from;
	StandardChessGame game;
	StandardBoard board;
	GameState state;

	@Before
	public void setUp() throws Exception {
		state = new GameState(ChessPlayerColor.WHITE);
		from = StandardCoordinate.make('e',5);
		emptyBoard = new HashMap<ChessCoordinate, StandardPiece>();                                                            
		whiteRook = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.ROOK);
		blackRook = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.ROOK);
		blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(StandardCoordinate.make('c', 7), whiteKing);
		emptyBoard.put(from, whiteRook);
		emptyBoard.put(StandardCoordinate.make('h',0), blackRook);

		board = new StandardBoard(emptyBoard);
		game = new StandardChessGame(board, state);
		
	}

	@Test
	public void testBasicValidRookMove() throws ChessException {
		to = StandardCoordinate.make('e', 0);

		assertTrue(game.getPieceAt(from).equals(whiteRook));
		game.makeMove(ChessPieceType.ROOK, from, to);
		assertTrue(game.getPieceAt(to).equals(whiteRook));
	}
	
	@Test
	public void testBasicValidRookMove_2() throws ChessException {
		to = StandardCoordinate.make('g', 5);

		assertTrue(game.getPieceAt(from).equals(whiteRook));
		game.makeMove(ChessPieceType.ROOK, from, to);
		assertTrue(game.getPieceAt(to).equals(whiteRook));
	}
	
	@Test
	public void testRookMoveThatPutsSelfInCheck() throws ChessException {
		emptyBoard.remove(from);
		from = StandardCoordinate.make('c', 5);
		emptyBoard.put(from, whiteRook);
		
		
		emptyBoard.remove(StandardCoordinate.make('h',0));
		emptyBoard.put(StandardCoordinate.make('c', 0), blackRook);
		
		board = new StandardBoard(emptyBoard);
		game = new StandardChessGame(board, state);

		assertTrue(game.getPieceAt(from).equals(whiteRook));
		to = StandardCoordinate.make('d', 5);
		try {
			game.makeMove(ChessPieceType.ROOK, from, to);
			fail();
		} catch (MovePutsKingInCheckException e) {
			assertEquals(KingValidator.MOVE_PUTS_KING_IN_CHECK, 
					e.getMessage());
		}
		
	}

}

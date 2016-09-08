package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import common.ChessCoordinate;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.GameState;
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
	StandardPiece whiteBishop, whiteKing;
	StandardPiece blackBishop, blackKing;
	StandardCoordinate to, from = StandardCoordinate.make('b', 7);
	StandardChessGame game;
	StandardBoard board;
	GameState state;

	@Before
	public void setUp() throws Exception {
		board = new StandardBoard(StandardBoard.initStandardBoard());
		state = new GameState(ChessPlayerColor.WHITE);
		emptyBoard = new HashMap<ChessCoordinate, StandardPiece>();                                                            
		whiteBishop = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.BISHOP);
		blackBishop = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.BISHOP);
		blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		emptyBoard.put(StandardCoordinate.make('a', 7), blackKing);
		emptyBoard.put(from, whiteKing);
		board = new StandardBoard(emptyBoard);
		game = new StandardChessGame(board, state);
	}
	
	@Test
	public void testCheck() throws KingNotFoundException {
		try {
			CheckValidator.validate(board, ChessPlayerColor.BLACK, ValidMoveGenerator.VALIDATE_CHECK);
			fail();
		} catch (MovePutsKingInCheckException e) {
			
		}
		
		try {
			CheckValidator.validate(board, ChessPlayerColor.WHITE, ValidMoveGenerator.VALIDATE_CHECK);
			fail();
		} catch (MovePutsKingInCheckException e) {
			
		}	
	}
	
}

package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import common.ChessCoordinate;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardCoordinate;
import standard.StandardPiece;
import validation.exception.BoardOutOfBoundsException;

public class StandardTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String currentSquare  = "| %s |";
		String s = String.format(currentSquare, "B_Q");
		System.out.println(s);
	}
	
	@Test
	public void testPieceLocationList() {
		StandardBoard board = new StandardBoard();
		
		assertEquals(32, board.getPieceLocations().size()); 
		
		ArrayList<ChessCoordinate> seenCoordinates = new ArrayList<ChessCoordinate>();
		for (ChessCoordinate c: board.getPieceLocations()) {
			if (seenCoordinates.contains(c)) {
				fail();
			}
			seenCoordinates.add(c);
		}
	}
	
	@Test 
	public void testBoard() throws BoardOutOfBoundsException {
		HashMap<ChessCoordinate, StandardPiece> boardMap = new HashMap<ChessCoordinate, StandardPiece>();
		StandardPiece whiteKing = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.KING);
		StandardPiece blackKing = new StandardPiece(ChessPlayerColor.BLACK, ChessPieceType.KING);
		StandardCoordinate oldWhiteKingLoc = StandardCoordinate.make('a', 7);
		StandardCoordinate blackKingLoc = StandardCoordinate.make('c', 7);
		StandardCoordinate newWhiteKingLoc = StandardCoordinate.make('c', 6);
		
		boardMap.put(oldWhiteKingLoc, whiteKing);
		boardMap.put(blackKingLoc, blackKing);
		
		StandardBoard board = new StandardBoard(boardMap);
		
		assertEquals(2, board.getPieceLocations().size());
		assertTrue(board.getPieceLocations().contains(blackKingLoc));
		assertTrue(board.getPieceLocations().contains(oldWhiteKingLoc));
		assertTrue(board.getPiece(oldWhiteKingLoc).equals(whiteKing));
		assertTrue(board.getPiece(blackKingLoc).equals(blackKing));
		
		board.movePiece(whiteKing, newWhiteKingLoc, oldWhiteKingLoc);
		
		assertEquals(2, board.getPieceLocations().size());
		assertTrue(board.getPieceLocations().contains(blackKingLoc));
		assertTrue(board.getPieceLocations().contains(newWhiteKingLoc));
		assertTrue(board.getPiece(newWhiteKingLoc).equals(whiteKing));
		assertTrue(board.getPiece(blackKingLoc).equals(blackKing));
	}
 	
	@Test
	public void testPrintableBoard() {
		String baseBoard = "----A-----B-----C-----D-----E-----F-----G-----H---\n" +
						   "8| B_R | B_H | B_B | B_Q | B_K | B_B | B_H | B_R |\n" +
					       "7| B_P | B_P | B_P | B_P | B_P | B_P | B_P | B_P |\n" +
					       "6|     |     |     |     |     |     |     |     |\n" +
					       "5|     |     |     |     |     |     |     |     |\n" +
					       "4|     |     |     |     |     |     |     |     |\n" +
					       "3|     |     |     |     |     |     |     |     |\n" +
					       "2| W_P | W_P | W_P | W_P | W_P | W_P | W_P | W_P |\n" +
					       "1| W_R | W_H | W_B | W_Q | W_K | W_B | W_H | W_R |\n" +
					       " -------------------------------------------------";
		StandardChessGame game = new StandardChessGame(ChessPlayerColor.WHITE);
		assertEquals(baseBoard, game.getPrintableBoard());
		System.out.println(game.getPrintableBoard());
	}
}

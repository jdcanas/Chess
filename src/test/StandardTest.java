package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import common.ChessCoordinate;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.StandardChessGame;

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
	public void testPrintableBoard() {
		String baseBoard = "----A-----B-----C-----D-----E-----F-----G-----H---\n" +
				"8| B_R | W_H | B_B | B_K | B_Q | B_B | B_H | B_R |\n" +
				"7| B_P | B_P | B_P | B_P | B_P | B_P | B_P | B_P |\n" +
				"6|     |     |     |     |     |     |     |     |\n" +
				"5|     |     |     |     |     |     |     |     |\n" +
				"4|     |     |     |     |     |     |     |     |\n" +
				"3|     |     |     |     |     |     |     |     |\n" +
				"2| W_P | W_P | W_P | W_P | W_P | W_P | W_P | W_P |\n" +
				"1| W_R | W_H | W_B | W_K | W_Q | W_B | W_H | W_R |\n" +
				" -------------------------------------------------";
		StandardChessGame game = new StandardChessGame(ChessPlayerColor.WHITE);
		assertEquals(baseBoard, game.getPrintableBoard());
		System.out.println(game.getPrintableBoard());
	}
}

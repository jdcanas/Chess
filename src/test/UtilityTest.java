package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import common.ChessCoordinate;
import standard.StandardCoordinate;
import utilities.CoordinateUtilities;

public class UtilityTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLetterCoordinate() {
		StandardCoordinate c = StandardCoordinate.make('a',0);
		
		assertEquals(0,c.getX());
	}
	
	@Test
	public void testGetAdjacentCoordinates() {
		ChessCoordinate center = StandardCoordinate.make('e',4);
		ArrayList<ChessCoordinate> adjacencyList = CoordinateUtilities.getAdjacentCoordinates(center);
		
		assertEquals(8, adjacencyList.size());
	}

}

package strategies.standardpiece;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.StandardCoordinate;
import strategies.StandardMovementValidationStrategy;
import utilities.CoordinateUtilities;
import validation.exception.MovementValidationException;

public class KingMovementStrategy extends StandardMovementValidationStrategy {

	public KingMovementStrategy() {
		super();	
	}

	@Override
	public ArrayList<ChessCoordinate> getPath() { 
		return new ArrayList<ChessCoordinate>();
	}

	@Override
	public void validateDirection() throws ChessException {
		//If the king is moving somewhere other than an adjacent square
		
		if (isValidCastling()) {
			
		}
		
		if (!CoordinateUtilities.getAdjacentCoordinates(from).contains(to)) {
			throw new MovementValidationException(formatMovementErrorMsg(ChessPieceType.KING, from));
		}
	}
	
	private boolean isValidCastling() {
		boolean isValidCastling = false;
		
		ArrayList<StandardCoordinate> validBlackToLocations = new ArrayList<StandardCoordinate>();
		ArrayList<StandardCoordinate> validWhiteToLocations = new ArrayList<StandardCoordinate>();
		ArrayList<StandardCoordinate> validToLocations = new ArrayList<StandardCoordinate>();
		
		validBlackToLocations.add(StandardBoard.initialBlackRookLocation1);
		validBlackToLocations.add(StandardBoard.initialBlackRookLocation2);
		validWhiteToLocations.add(StandardBoard.initialWhiteRookLocation1);
		validWhiteToLocations.add(StandardBoard.initialWhiteRookLocation2);
		
		validToLocations = board.getPiece(from).getColor() == ChessPlayerColor.BLACK ?
				validBlackToLocations : validWhiteToLocations;
		
		StandardCoordinate validFromLocation = board.getPiece(from).getColor() == ChessPlayerColor.BLACK ? 
				StandardBoard.initialBlackKingLocation : StandardBoard.initialWhiteKingLocation;
		
		if (validToLocations.contains(to) && validFromLocation.equals(from)) {
			isValidCastling = true;
		}
		
		return isValidCastling;
	}


}

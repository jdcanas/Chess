package validation;

import common.ChessCoordinate;
import common.ChessException;
import standard.StandardBoard;

public interface Validator {

	public void validate(ChessCoordinate from, ChessCoordinate to, StandardBoard board) throws ChessException;
	
}

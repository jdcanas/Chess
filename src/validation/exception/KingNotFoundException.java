package validation.exception;

import common.ChessException;

public class KingNotFoundException extends ChessException {

	public KingNotFoundException(String message) {
		super(message);
	}

}

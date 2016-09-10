package validation.exception;

import common.ChessException;

public class KingCantMoveException extends ChessException {

	public KingCantMoveException(String message) {
		super(message);
	}

}

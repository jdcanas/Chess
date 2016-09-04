package validation.exception;

import common.ChessException;

public class BoardOutOfBoundsException extends ChessException {

	public BoardOutOfBoundsException(String message) {
		super(message);
	}

}

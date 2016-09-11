package validation.exception;

import common.ChessException;

public class MoveMadeAfterGameClosedException extends ChessException {

	public MoveMadeAfterGameClosedException(String message) {
		super(message);
	}

}

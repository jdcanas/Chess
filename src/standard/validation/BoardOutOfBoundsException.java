package standard.validation;

import common.ChessException;

public class BoardOutOfBoundsException extends ChessException {

	public BoardOutOfBoundsException(String message) {
		super(message);
	}

}

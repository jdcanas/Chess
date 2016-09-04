package standard.validation;

import common.ChessException;

public class KingNotFoundException extends ChessException {

	public KingNotFoundException(String message) {
		super(message);
	}

}

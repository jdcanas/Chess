package Controller;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.Move;
import standard.StandardCoordinate;
import utilities.CoordinateUtilities;
import validation.exception.BoardOutOfBoundsException;
import validation.exception.UserInputInvalidFormatException;

public class UserInputHandler {
	public static final String FROM_PROMPT = "\n\nYou are now inputting the piece location.";
	public static final String FROM_X_PROMPT = "\n\tInput the x coordinate of the piece: ";
	public static final String FROM_Y_PROMPT = "\n\tInput the y coordinate of the piece: ";
	public static final String TO_PROMPT = "\n\nYou are now inputting the destination location.";
	public static final String TO_X_PROMPT = "\n\tInput the x coordinate of the destination: ";
	public static final String TO_Y_PROMPT = "\n\tInput the y coordinate of the destination: ";
	public static final String PIECE_PROMPT = "\nYou are now inputting the piece type: ";
	
	
	private int fromX, fromY;
	private int toX, toY;
	private ChessCoordinate to, from;
	ChessPieceType piece;
	private Move m;
	
	public UserInputHandler() {
	
	}
	
	public Move getMove() {
		return m;
	}
	
	public void getUserInput() {
		getUserInputWrapper();
		m = new Move(to, from, piece);
	}
	
	private void getUserInputWrapper() {
		getFromUserInputWrapper();
		getToUserInputWrapper();
		getPieceFromUserInputWrapper();
	}
	
	private void getPieceFromUserInputWrapper() {
		try {
			getPieceUserInput();
		} catch (UserInputInvalidFormatException e) {
			System.out.println(e.getMessage());
			getPieceFromUserInputWrapper();
		}
	}
	
	private void getFromUserInputWrapper() {
		try {
			getFromUserInput();
		} catch (UserInputInvalidFormatException | BoardOutOfBoundsException e) {
			System.out.println(e.getMessage());
			getFromUserInputWrapper();
		}
	}
	
	private void getFromUserInput() throws UserInputInvalidFormatException, BoardOutOfBoundsException {
		System.out.println(FROM_PROMPT);
		fromX = withoutExceptionPromptForCoordinateInput(FROM_X_PROMPT);
		fromY = withoutExceptionPromptForCoordinateInput(FROM_Y_PROMPT);
		validateMove(fromX, fromY, true);
	}
	
	private void getToUserInputWrapper() {
		try {
			getToUserInput();
		} catch (UserInputInvalidFormatException | BoardOutOfBoundsException e) {
			System.out.println(e.getMessage());
			getToUserInputWrapper();
		}
	}
	
	private void getToUserInput() throws UserInputInvalidFormatException, BoardOutOfBoundsException {
		System.out.println(TO_PROMPT);
		toX = withoutExceptionPromptForCoordinateInput(TO_X_PROMPT);
		toY = withoutExceptionPromptForCoordinateInput(TO_Y_PROMPT);
		validateMove(toX, toY, true);
	}
	
	private void getPieceUserInput() throws UserInputInvalidFormatException {
	
		piece = withoutExceptionPromptForPieceInput();
	}
	
	private void validateMove(int x, int y, boolean isFrom) throws BoardOutOfBoundsException {
		StandardCoordinate testCoord = StandardCoordinate.make(x, y);
		
		if (!CoordinateUtilities.getValidCoordinates().contains(testCoord)) {
			throw new BoardOutOfBoundsException("The coordinate you entered is out of bounds\n " 
				+ "\tCoordinate: " + testCoord.toString());
		}
		
		if (isFrom) {
			from = testCoord;
		} else {
			to = testCoord;
		}
	}
	
	private ChessPieceType withoutExceptionPromptForPieceInput() throws UserInputInvalidFormatException {
		String input = ReadUserInput.getVisibleInput(PIECE_PROMPT);
		return validatePiece(input);
	}

	private ChessPieceType validatePiece(String input) throws UserInputInvalidFormatException {
		ChessPieceType pieceVal = null;
		if (!ChessPieceType.pieceNames().contains(input.toLowerCase())) {
			System.out.println("here1");
			throw new UserInputInvalidFormatException("The piece you entered is not a valid piece: " + input);
		}
		
		try  {
			pieceVal = ChessPieceType.stringToPieceType(input.toLowerCase());
		} catch (ChessException e) {

			System.out.println("here2");
			throw new UserInputInvalidFormatException(e.getMessage());
		}
		
		return pieceVal;
	}

	private int withoutExceptionPromptForCoordinateInput(String prompt) throws UserInputInvalidFormatException {
		String input = ReadUserInput.getVisibleInput(prompt);
		validateCoordinatePoint(input);
		return Integer.parseInt(input);
	}
	
	private void validateCoordinatePoint(String input) throws UserInputInvalidFormatException {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new UserInputInvalidFormatException("The input you entered is not a valid number");
		}
	}
	
	

}

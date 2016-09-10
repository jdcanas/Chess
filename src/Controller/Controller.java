package Controller;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.MoveResult;
import standard.StandardChessGame;

public class Controller {
	private static Controller instance = null;
	public static ChessPlayerColor startingColor = ChessPlayerColor.WHITE;
	
	private StandardChessGame game;
	
	/**
	 * Default constructor for the ViewEventController. Is protected to prevent instantiation.
	 */
	private Controller(){
		game = initGame();
	}


	/**
	 * Returns the single instance of the ViewEcentController.
	 * @return the instance of this controller.
	 */
	public static Controller getInstance(){
		if(instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	private StandardChessGame initGame() {
		return new StandardChessGame(startingColor);
	}
	
	public MoveResult makeMove(ChessPieceType piece, ChessCoordinate from, ChessCoordinate to) throws ChessException {
		return game.makeMove(piece, from, to);
	}
}

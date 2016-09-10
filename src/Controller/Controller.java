package Controller;

import java.util.ArrayList;

import common.ChessCoordinate;
import common.ChessException;
import common.ChessGameType;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.Move;
import common.MoveResult;
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardPiece;
import utilities.ValidMoveGenerator;

public class Controller {
	private static Controller instance = null;
	public static ChessPlayerColor startingColor = ChessPlayerColor.WHITE;
	
	private StandardChessGame game;
	private ChessPieceType piece;
	private ChessCoordinate from;
	private ChessCoordinate to;
	private StandardBoard board;
	private MoveResult result = MoveResult.OK;
	
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
		this.piece = piece;
		this.from = from;
		this.to = to;
		
		return game.makeMove(piece, from, to);
	}
}

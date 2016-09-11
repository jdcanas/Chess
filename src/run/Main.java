package run;

import AI.AIFactory;
import AI.RandomMoveAI;
import BasicView.BaseView;
import BasicView.exception.ButtonNotFoundException;
import Controller.Controller;
import Controller.UserInputHandler;
import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.Move;
import common.MoveResult;

public class Main {

	private static Move currMove;
	private static MoveResult result;
	
	public static final boolean IS_AI_GAME = true;
	
	public static void main(String[] args) throws ButtonNotFoundException {

		AIFactory.getInstance().setActiveAI(new RandomMoveAI(Controller.getInstance().getGame(), ChessPlayerColor.BLACK));
		BaseView view = new BaseView();
//		while (true) {
//			System.out.println(Controller.getInstance().getPrintableBoard());
//			
//			result = makeMoveWrapper();
//		
//			if (result == MoveResult.DRAW || result == MoveResult.BLACK_WINS || result == MoveResult.WHITE_WINS) {
//				System.out.println("The game is over: " + result);
//				System.exit(0);
//			}
//		}
	}
	
	private static MoveResult makeMoveWrapper() {
		MoveResult result = null;
		getMove();
		try {
			result = Controller.getInstance().makeMove(currMove.piece, currMove.from, currMove.to);
		} catch (ChessException e) {
			System.out.println(e.getMessage());
			makeMoveWrapper();
		}
		
		return result;
	}
	
	public static void getMove() {
		UserInputHandler input = new UserInputHandler();
		input.getUserInput();
		
		currMove = input.getMove();
	}
	
	public void validMoveWrapper() {
		
	}
	
	public void performMove(ChessPieceType piece, ChessCoordinate from, ChessCoordinate to) {
		
	}
	
}

package BasicView;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import AI.AIFactory;
import BasicView.exception.ButtonNotFoundException;
import BasicView.exception.ViewMoveInputException;
import Controller.Controller;
import common.ChessCoordinate;
import common.ChessException;
import common.ChessPieceType;
import common.ChessPlayerColor;
import common.Move;
import run.Main;
import standard.StandardBoard;
import standard.StandardChessGame;
import standard.StandardCoordinate;
import standard.StandardPiece;
import utilities.CoordinateUtilities;

public class ButtonManager {

	private ArrayList<PieceButton> pieceButtons = new ArrayList<PieceButton>();
	private static ButtonManager instance = null;
	private StandardBoard board;

	public boolean isOneButtonPressed = false;
	public PieceButton fromButton;
	public PieceButton toButton;

	public static ButtonManager getInstance(StandardBoard board) throws ButtonNotFoundException{
		if(instance == null){
			instance = new ButtonManager(board);
		}
		return instance;
	}
	
	private ButtonManager(StandardBoard board) throws ButtonNotFoundException{
		this.board = board;
		initPieceButtons();
		updatePieceButtons();	
	}
	
	public void buttonPressed(PieceButton b) throws ChessException {
		if (isOneButtonPressed) {
			toButton = b;
			
			makeMoveWrapper();
			this.board = Controller.getInstance().getBoard();
			updatePieceButtons();
			resetUserInput();
			
			if (Main.IS_AI_GAME) {
				makeAIMove();
				updatePieceButtons();
			}
		} else {
			isOneButtonPressed = true;
			fromButton = b;
		}
	}
	
	private void makeAIMove() throws ChessException {
		Move m = AIFactory.getInstance().getActiveAI().getAIMove();
		makeMove(m.piece, m.from, m.to);
	}

	private void resetUserInput() throws ButtonNotFoundException {
		System.out.println("resetting buttons");
		isOneButtonPressed = false;
		toButton.setSelected(false);
		fromButton.setSelected(false);
		updatePieceButtons();
	}
	
	private void makeMoveWrapper() throws ButtonNotFoundException {
		StandardPiece piece = fromButton.getPiece();
		ChessCoordinate from = fromButton.getLocationCoordinate();
		ChessCoordinate to = toButton.getLocationCoordinate();
		
		try {
			validateMove(piece, from, to);
		} catch(ViewMoveInputException e) {
			resetUserInput();
			return;
		}
		
		try {
			makeMove(piece.getType(), from, to);
		} catch(ChessException e) {
			resetUserInput();
		}
		
	}
	
	private void makeMove(ChessPieceType piece, ChessCoordinate from, ChessCoordinate to) throws ChessException {
		Controller.getInstance().makeMove(piece, from, to);
		
	}

	private static void validateMove(StandardPiece piece, ChessCoordinate from, ChessCoordinate to) throws ViewMoveInputException {
		if (piece == null || to == null || from == null) {
			throw new ViewMoveInputException("One of the inputs you entered is incorrect.\nPlease select the piece you would like to move, and then the desination.");
		}
	}


	private void initPieceButtons() {
		PieceButton b;
		StandardPiece piece;
		ArrayList<ChessCoordinate> sortedCoordinates = CoordinateUtilities.getSortedCoordinate();
		
		//for (ChessCoordinate c: sortedCoordinates) {
		for (int i = sortedCoordinates.size() - 1; i >= 0; i--)	{
			piece = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.PAWN);
			b = new PieceButton(piece, piece.toString(), sortedCoordinates.get(i));
			pieceButtons.add(b);
		}
	}
	
	private void updatePieceButtons() throws ButtonNotFoundException {
		ArrayList<ChessCoordinate> sortedCoordinates = CoordinateUtilities.getSortedCoordinate();
		PieceButton b;
		StandardPiece piece;
		
		for (ChessCoordinate c: sortedCoordinates) {
			b = ButtonRow.findButtonWithCoordinate(c, pieceButtons);
			
			if (board.getPiece(c) == null) {
				piece = null;
			} else {
				piece = new StandardPiece(board.getPiece(c).getColor(), board.getPiece(c).getType());
			}
			
			b.update(piece);
			b.repaint();
		}
	}
	
	public ArrayList<PieceButton> getButtons() {
		return pieceButtons;
	}
	
	
}

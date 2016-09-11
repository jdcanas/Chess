package BasicView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import BasicView.exception.ButtonNotFoundException;
import Controller.Controller;
import common.ChessPieceType;
import common.ChessPlayerColor;
import standard.StandardBoard;
import standard.StandardCoordinate;
import standard.StandardPiece;

public class BaseView {
	JFrame frame;
	BoardButtonPanel boardButtonContainer;
	ArrayList<PieceButton> pieceButtons;
	
	public BaseView() throws ButtonNotFoundException {
		setBaseFrame();
	}

	public void setBaseFrame() throws ButtonNotFoundException {
		//1. Create the frame.
		frame = new JFrame("Chess");

		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		//...create emptyLabel...
		addButton();
	//	configurePanels();

		setLayout();

		//4. Size the frame.
		//ViewUtilities.setSize(ViewConstants.BASE_X_SIZE, ViewConstants.BASE_Y_SIZE, frame);
		frame.pack();

		//5. Show it.
		frame.setVisible(ViewConstants.IS_VIEW_VISIBLE);
	}
	public void setLayout() {
	}
	
	public void addButton() throws ButtonNotFoundException {
		StandardPiece piece = new StandardPiece(ChessPlayerColor.WHITE, ChessPieceType.PAWN);
		StandardBoard board = Controller.getInstance().getBoard();

		ArrayList<PieceButton> buttons = ButtonManager.getInstance(board).getButtons();

		BoardButtonPanel boardButtons = new BoardButtonPanel();
		
//		ButtonRow b =ButtonRow.createButtonsRow(0, 7, buttons);
//		JPanel pane = new JPanel();
//		pane.add(b);
//		pane.add(ButtonRow.createButtonsRow(8, 15, buttons));
//		//pane.add(b);
//		//Container c = new Container();
//	//	c.add(new PieceButton(piece, "test", StandardCoordinate.make(0, 0)));
//	//	pane.add(c);
		frame.getContentPane().add(boardButtons);
	}
	
	public void configurePanels() throws ButtonNotFoundException {
		boardButtonContainer = new BoardButtonPanel();
		frame.getContentPane().add(boardButtonContainer);
	}
	
	

}

package BasicView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import BasicView.exception.ButtonNotFoundException;
import Controller.Controller;
import common.ChessCoordinate;
import common.ChessException;
import standard.StandardPiece;

public class PieceButton extends JToggleButton {

	private StandardPiece piece;
	private String text;
	private boolean hasPiece;
	private ChessCoordinate location;
	
	public PieceButton(StandardPiece piece, String text, ChessCoordinate location) {
		super();
		this.location = location;
		this.piece = piece;
		this.hasPiece = piece != null;
		this.text = text;
		this.setText(text);
		this.setSize();
		this.setVisible(ViewConstants.IS_VIEW_VISIBLE);
		this.addActionListener(new PieceButtonActionListener(this));
	}
	
	private void initButtonMembers(StandardPiece piece) {
		this.piece = piece;
		this.hasPiece = piece != null;
		this.text = hasPiece ? piece.toString() : "";
		System.out.println(this.text);
	}
	
	public void setSize() {
		ViewUtilities.setSize(ViewConstants.PIECE_BUTTON_X, ViewConstants.PIECE_BUTTON_Y, this);
	}
	
	public ChessCoordinate getLocationCoordinate() {
		return location;
	}
	
	public StandardPiece getPiece() {
		return piece;
	}
	
	public void update(StandardPiece piece) {
		initButtonMembers(piece);
		this.setText(text);
		reset();
	}
	
	public void reset() {
		ViewUtilities.setSize(ViewConstants.PIECE_BUTTON_X, ViewConstants.PIECE_BUTTON_Y, this);
		this.repaint();
		
	}
	

	
	private class PieceButtonActionListener implements ActionListener {
		PieceButton thisButton;
		public PieceButtonActionListener(PieceButton b) {
			super();
			thisButton = b;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ButtonManager.getInstance(Controller.getInstance().getBoard()).buttonPressed(thisButton);
			} catch (ChessException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	
	
}

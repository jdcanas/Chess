package BasicView;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import BasicView.exception.ButtonNotFoundException;
import common.ChessCoordinate;
import standard.StandardCoordinate;

public class ButtonRow extends JPanel {

	ArrayList<PieceButton> buttons;
	SpringLayout layout;
	int rowNum;

	private ButtonRow(ArrayList<PieceButton> buttons) {
		this.buttons = buttons;
		sizeComp();
		initRow();
	//	configureLayout();
		setVisible(ViewConstants.IS_VIEW_VISIBLE);
		repaint();
	}
	
	private void sizeComp() {
		Dimension d = new Dimension(ViewConstants.PIECE_BUTTON_X * 10, ViewConstants.PIECE_BUTTON_Y + ViewConstants.PIECE_BUTTON_Y/5);
		setPreferredSize(d);
		setMaximumSize(d);
	}
	
	private void initRow() {
		for (int i = 0; i < buttons.size(); i++) {
			this.add(buttons.get(i), i);
		}
	}
	
	public void configureLayout() {
		layout = new SpringLayout();
		
		for (int i = 0; i < buttons.size(); i++) {
			PieceButton b = buttons.get(i);
			stumpConfigureComp(this, b, layout);
//			if (i == 0) {
//				configureFirstComponent(this, b, layout);
//			} else if (i == (buttons.size() - 1)) {
//				configureLastComponent(this, b, layout);
//			} else {
//				configureComponent(buttons.get(i - 1), b, layout);
//			}
		}
		
		this.setLayout(layout);
	}
	
	private void stumpConfigureComp(ButtonRow buttonRow, PieceButton b, SpringLayout layout2) {
	//	l.putConstraint(SpringLayout.WEST, c2, 5, SpringLayout.EAST, c);
		
	}

	public static ButtonRow createButtonsRow(int startIndex, int endIndex, ArrayList<PieceButton> buttons) throws ButtonNotFoundException {
		ArrayList<PieceButton> thisRow = new ArrayList<PieceButton>();
		ButtonRow row;
		PieceButton currButton;
		ChessCoordinate currCoordinate;
		for (int i = startIndex; i <= endIndex; i++ ) {
			currCoordinate = getCoordinateFromIndex(i);
			currButton = findButtonWithCoordinate(currCoordinate, buttons);
			thisRow.add(currButton);
		}
		row = new ButtonRow(thisRow);
		
		return row;
	}
	
	public static ChessCoordinate getCoordinateFromIndex(int i) {
		int y = i / 8;
		int x = i % 8;
		return StandardCoordinate.make(x, y);
	}
	
	public static PieceButton findButtonWithCoordinate(ChessCoordinate coord, ArrayList<PieceButton> buttons) throws ButtonNotFoundException {
		PieceButton foundButton = null;
		
		for (PieceButton b: buttons) {
			if (b.getLocationCoordinate().equals(coord)) {
				foundButton = b;
			}
		}
		
		if (foundButton == null) {
			throw new ButtonNotFoundException("couldn't find button for coordinate: " + coord.toString());
		}
		
		return foundButton;
	}
	
	
	
	private void configureFirstComponent(Component c, Component c2, SpringLayout l) {
		l.putConstraint(SpringLayout.WEST, c2, 10, SpringLayout.WEST, c);	
		l.putConstraint(SpringLayout.NORTH, c2, 5, SpringLayout.NORTH, c);	
		l.putConstraint(SpringLayout.SOUTH, c2, 5, SpringLayout.SOUTH, c);	
		
	}
	
	private void configureLastComponent(Component c, Component c2, SpringLayout l) {
		l.putConstraint(SpringLayout.EAST, c2, 10, SpringLayout.EAST, c);	
		l.putConstraint(SpringLayout.NORTH, c2, 5, SpringLayout.NORTH, c);	
		l.putConstraint(SpringLayout.SOUTH, c2, 5, SpringLayout.SOUTH, c);	
	}

	private void configureComponent(Component c, Component c2, SpringLayout l) {
		l.putConstraint(SpringLayout.WEST, c2, 5, SpringLayout.EAST, c);	
	}

}

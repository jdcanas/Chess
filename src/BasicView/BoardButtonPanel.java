package BasicView;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import BasicView.exception.ButtonNotFoundException;
import Controller.Controller;

public class BoardButtonPanel extends JPanel {
	
	SpringLayout layout = new SpringLayout();
	ButtonManager m = ButtonManager.getInstance(Controller.getInstance().getBoard());
	ArrayList<PieceButton> buttons = m.getButtons();
	HashMap<Integer, ButtonRow> buttonRows = new HashMap<Integer, ButtonRow>();

	
	public BoardButtonPanel() throws ButtonNotFoundException {
		
		super();
		addButtonsToPanel();
	}
	
	public void addButtonsToPanel() throws ButtonNotFoundException {
		ViewUtilities.setSize(ViewConstants.PIECE_BUTTON_PANEL_X, ViewConstants.PIECE_BUTTON_PANEL_Y, this);
		initButtonRows();
		
		addButtonRows();
	}
	
	private void initButtonRows() throws ButtonNotFoundException {
		ButtonRow currRow;
		int rowNum = 0;
		
		for (int i = 0; i < buttons.size(); i += 8) {
			currRow = createButtonsRow(i, i + 7);
			buttonRows.put(rowNum, currRow);
			rowNum++;
		}
	}
	
	private ButtonRow createButtonsRow(int startIndex, int endIndex) throws ButtonNotFoundException {
		return ButtonRow.createButtonsRow(startIndex, endIndex, this.buttons);
	}

	
	private void addButtonRows() {
		for (int i = 0; i < buttonRows.size(); i++) {
			this.add(buttonRows.get(i));
		} 
	}
	
	public void configureLayout(PieceButton button1, PieceButton button2, boolean isFirstOrLastButton) {
		if (isFirstOrLastButton) {
			
		}
	}
	
	
	
	
	
}

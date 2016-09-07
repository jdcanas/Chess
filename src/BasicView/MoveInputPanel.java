package BasicView;

import javax.swing.JPanel;

public class MoveInputPanel extends JPanel {
	
	public MoveInputPanel() {
		super();
	}
	
	public void build() {
		setSize();
		addInputFields();
	}

	public void setSize() {
		ViewUtilities.setSize(ViewConstants.MOVE_INPUT_PANEL_X, ViewConstants.MOVE_INPUT_PANEL_Y, this);
	}

	private void addInputFields() {
		InputTextField coordinateInput = new InputTextField();
		
		this.add(coordinateInput);
	}
}

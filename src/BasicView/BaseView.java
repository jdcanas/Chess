package BasicView;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class BaseView {
	JFrame frame;
	
	public BaseView() {
		setBaseFrame();
		setLayout();
	}

	public void setBaseFrame() {
		//1. Create the frame.
		frame = new JFrame("Chess");

		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		//...create emptyLabel...
		JPanel testComp = new JPanel();
		testComp.setVisible(ViewConstants.IS_VIEW_VISIBLE);
		ViewUtilities.setSize(250, 250, testComp);
		frame.getContentPane().add(testComp, BorderLayout.CENTER);
		
		configurePanels();

		//4. Size the frame.
		ViewUtilities.setSize(ViewConstants.BASE_X_SIZE, ViewConstants.BASE_Y_SIZE, frame);
		frame.pack();

		//5. Show it.
		frame.setVisible(ViewConstants.IS_VIEW_VISIBLE);
	}
	
	public void setLayout() {
		TopLevelFrameLayout mainLayout = new TopLevelFrameLayout(new MoveInputPanel(), frame);
		frame.setLayout(mainLayout);
	}
	
	public void configurePanels() {
		MoveInputPanel moveInput = new MoveInputPanel();
		frame.add(moveInput);
	}

}

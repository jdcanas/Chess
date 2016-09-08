package BasicView;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class TopLevelFrameLayout extends SpringLayout {
	
	private JFrame baseFrame;
	private JPanel testBox;

	public TopLevelFrameLayout(JPanel testBox, JFrame baseFrame) {
		buildLayout(testBox, baseFrame);
	}

	private void buildLayout(JPanel testBox, JFrame baseFrame) {
		this.baseFrame = baseFrame;
		this.testBox = testBox;
		setupActionListeners();

		addComponents();
		configureComponent(this.testBox, this.baseFrame);
		
		setIcons();
	}

	private void setupActionListeners() {

	}

	private void addComponents() {

	}

	private void setIcons() {

	}
	
	private void configureComponent(Component c, Component c2) {
		putConstraint(SpringLayout.NORTH, c, 30, SpringLayout.NORTH, c2);	
		putConstraint(SpringLayout.WEST, c, 10, SpringLayout.WEST, c2);	
	}

}

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
		configureComponent(testBox, baseFrame);
		
		setIcons();
	}

	private void setupActionListeners() {

	}

	private void addComponents() {

	}

	private void setIcons() {

	}
	
	private void configureComponent(Component c, Component c2) {
		putConstraint(SpringLayout.NORTH, c, 0, SpringLayout.NORTH, c2);	//Adds the end date field underneath the radio buttons panel
		putConstraint(SpringLayout.SOUTH, c, 0, SpringLayout.SOUTH, c2);	
		putConstraint(SpringLayout.WEST, c, 0, SpringLayout.WEST, c2);					//Makes sure the left side of the panel stretches with the left side of the container
		putConstraint(SpringLayout.EAST, c, 0, SpringLayout.EAST, c2);
	}

}

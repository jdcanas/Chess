package BasicView;

import java.awt.Component;
import java.awt.Dimension;

public class ViewUtilities {
	
	public static void setSize(int x, int y, Component comp) {
		Dimension d = new Dimension(x, y);
		comp.setPreferredSize(d);
		comp.setMaximumSize(d);
	}
	
	
	
}

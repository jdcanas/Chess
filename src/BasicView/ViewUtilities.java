package BasicView;

import java.awt.Component;
import java.awt.Dimension;

public class ViewUtilities {
	
	public static void setSize(int x, int y, Component comp) {
		comp.setPreferredSize(new Dimension(x, y));
	}
	
}

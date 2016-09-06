package BasicView;

import java.text.Format;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class InputTextField extends JFormattedTextField {

	public static final int COORDINATE_NUM_FIELD_FORMAT_FLAG = 0;
	public static final int TEXT_FIELD_FORMAT_FLAG = 1;

	Format fieldFormat;

	public InputTextField() {
		super();
		fieldFormat = getFormat(COORDINATE_NUM_FIELD_FORMAT_FLAG);
		setSize();
	}

	public static Format getFormat(int formatFlag) {
		Format format;
		
		switch (formatFlag) {
		case COORDINATE_NUM_FIELD_FORMAT_FLAG:
			format = getCoordinateNumberFormat();
			break;
		case TEXT_FIELD_FORMAT_FLAG:
			format = getCoordinateNumberFormat();
			break;
		default:
			format = null;
			break;
		}
		
		return format;
	}
	
	private void setSize() {
		ViewUtilities.setSize(ViewConstants.COORDINATE_TEXT_FIELD_X, ViewConstants.COORDINATE_TEXT_FIELD_Y, this);	
	}
		
	public static Format getCoordinateNumberFormat() {
		Format coordinateFormat = NumberFormat.getInstance().getNumberInstance();
		return coordinateFormat;
	}

}

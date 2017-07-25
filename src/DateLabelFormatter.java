import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nandigam on 2/7/17.
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private String datePattern;
    private SimpleDateFormat dateFormatter;

    public DateLabelFormatter(String pattern) {
        datePattern = pattern;
        dateFormatter = new SimpleDateFormat(datePattern);

    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parse(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null){
            Calendar calendar = (Calendar) value;
            return dateFormatter.format(calendar.getTime());
        }

        return null;
    }
}

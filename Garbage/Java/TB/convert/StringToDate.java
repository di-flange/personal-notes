package ee.jiss.resourceflow.utils.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate implements Converter<String, Date> {

    public static final String DEFAULT_PATTERN = "dd.MM.yyyy";

    private final SimpleDateFormat pattern;

    public StringToDate() {

        this(DEFAULT_PATTERN);
    }

    public StringToDate(final String pattern) {

        super();

        this.pattern = new SimpleDateFormat(pattern);
    }

    @Override
    public Date execute(final String in) {

        if (in == null) {
            return null;
        }

        try {
            return this.pattern.parse(in);
        } catch (final ParseException exp) {
            return null;
        }
    }
}
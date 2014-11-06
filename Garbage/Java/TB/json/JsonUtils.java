package ee.jiss.resourceflow.utils.json;

public class JsonUtils {

    private static final String BLOCK_PREFIX = "{ ";
    private static final String BLOCK_POSTFIX = " }";

    private static final String ARRAY_PREFIX = "[ ";
    private static final String ARRAY_POSTFIX = " ]";

    public static final String SEPARATOR = ", ";
    public static final String ANNOTATOR = ": ";

    public static String toString(final JsonPair json) {

        final String key = quote(json.getKey());
        final Object value = json.getValue();
        
        if (value.getClass().equals(String.class)) {
            return key + ANNOTATOR + quote((String) value);
        }

        return key + ANNOTATOR + json.getValue().toString();
    }

    public static String toString(final JsonCollection json) {

        return block(enumeration(json.toArray(new JsonItem[json.size()])));
    }

    public static String toString(final JsonArray json) {

        return array(enumeration(json.toArray(new JsonItem[json.size()])));
    }

    public static String enumeration(final JsonItem... items) {

        final StringBuilder builder = new StringBuilder();
        boolean first = true;

        for (final JsonItem pair : items) {
            if (first) {
                first = false;
            } else {
                builder.append(SEPARATOR);
            }

            builder.append(pair.toString());
        }

        return builder.toString();
    }

    public static String enumeration(final String... items) {

        final StringBuilder builder = new StringBuilder();
        boolean first = true;

        for (final String item : items) {
            if (first) {
                first = false;
            } else {
                builder.append(SEPARATOR);
            }

            builder.append(quote(item));
        }

        return builder.toString();
    }


    public static String quote(final String str) {

       return '"' + str + '"';
    }

    public static String array(final String body) {

        if (body == null) {
            return emptyArray();
        }

        return ARRAY_PREFIX + body + ARRAY_POSTFIX;
    }

    public static String block(final String body) {

        if (body == null) {
            return emptyBlock();
        }

        return BLOCK_PREFIX + body + BLOCK_POSTFIX;
    }

    public static String emptyArray() {

        return BLOCK_PREFIX + BLOCK_POSTFIX;
    }

    public static String emptyBlock() {

        return BLOCK_PREFIX + BLOCK_POSTFIX;
    }
}
package ee.jiss.resourceflow.utils.json;

@SuppressWarnings("UnusedDeclaration")
public class JsonPair implements JsonItem {

    private String key;
    private Object value;

    public JsonPair() {

        super();
    }

    public JsonPair(final String key, final JsonItem value) {

        this();

        this.key = key;
        this.value = value;
    }

    public JsonPair(final String key, final String value) {

        this();

        this.key = key;
        this.value = value;
    }

    public String getKey() {

        return this.key;
    }

    public void setKey(final String key) {

        this.key = key;
    }

    public Object getValue() {

        return this.value;
    }

    public void setValue(final String value) {

        this.value = value;
    }

    public void setValue(final JsonCollection value) {

        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final JsonPair jsonPair = (JsonPair) o;

        return !(this.key == null ? jsonPair.key != null : !this.key.equals(jsonPair.key))
                || !(this.value == null ? jsonPair.value != null : !this.value.equals(jsonPair.value));

    }

    @Override
    public int hashCode() {

        return (this.key == null ? 0 : this.key.hashCode())
                + (this.value == null ? 0 : this.value.hashCode());
    }

    @Override
    public String getJsonString() {

        return this.toString();
    }

    @Override
    public JsonItem getJson() {

        return this;
    }

    @Override
    public String toString() {

        return JsonUtils.toString(this);
    }
}
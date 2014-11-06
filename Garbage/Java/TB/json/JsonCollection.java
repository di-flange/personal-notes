package ee.jiss.resourceflow.utils.json;

import ee.jiss.resourceflow.utils.DefaultUtils;
import ee.jiss.resourceflow.utils.StringUtils;

import java.util.HashSet;

@SuppressWarnings("CloneableClassWithoutClone")
public class JsonCollection  extends HashSet<JsonItem> implements JsonItem {

    public boolean add(final String key, final String value) {
        
        return super.add(new JsonPair(key, DefaultUtils.select(value, StringUtils.EMPTY)));
    }

    public boolean add(final String key, final Number value) {

        return this.add(key, DefaultUtils.select(value, 0).toString());
    }

    public boolean add(final String key, final Boolean value) {

        return this.add(key, DefaultUtils.select(value, false).toString());
    }

    public boolean add(final String key, final JsonItem value) {

        if (value == null) {
            return super.add(new JsonPair(key, StringUtils.EMPTY));
        }

        return super.add(new JsonPair(key, value));
    }

    public boolean add(final String key, final JsonConvertible value) {

        if (value == null) {
            return super.add(new JsonPair(key, new JsonCollection()));
        }

        return super.add(new JsonPair(key, value.getJson()));
    }

    public boolean add(final String key, final Iterable<? extends JsonConvertible> value) {

        final JsonArray array = new JsonArray();

        for (final JsonConvertible entry : value) {
            array.add(entry);
        }

        return super.add(new JsonPair(key, array));
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
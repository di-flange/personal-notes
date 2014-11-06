package ee.jiss.resourceflow.utils.json;

import ee.jiss.resourceflow.utils.DefaultUtils;

import java.util.HashSet;

@SuppressWarnings("CloneableClassWithoutClone")
public class JsonArray extends HashSet<JsonItem> implements JsonItem {

    public boolean add(final JsonConvertible value) {

        if (value == null) {
            return super.add(new JsonCollection());
        }

        return super.add(value.getJson());
    }

    public boolean add(final String key, final Iterable<JsonConvertible> value) {

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
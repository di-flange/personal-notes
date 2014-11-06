package ee.jiss.resourceflow.utils.json;

/**
 * Mark object that it can be serialized to JSON.
 */
@SuppressWarnings("UnusedDeclaration")
public interface JsonConvertible {

    /**
     * Get object as JSON string
     *
     *
     * @return JSON representation of this object as string
     */
    String getJsonString();

    /**
     * Get object as JSON
     *
     * @return JSON representation of this object
     */
    JsonItem getJson();
}
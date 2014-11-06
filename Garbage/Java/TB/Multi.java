package eu.aiskov.toolbox;

/**
 * Class for fast sending several arguments. Way to workaround to avoid restrictions of array.
 *
 * @param <T> Type of values.
 */
@SuppressWarnings("UnusedDeclaration")
public class Multi<T> {

    /**
     * Values
     */
    private final T[] values;

    /**
     * Default constructor
     *
     * @param values Values contained in object.
     */
    @SafeVarargs
    public Multi(final T... values) {

        super();

        this.values = values;
    }

    public T get(final int index) {

        return this.values[index];
    }
}
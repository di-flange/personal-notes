package eu.aiskov.toolbox;

import java.lang.reflect.Array;

/**
 * Utility for work with arrays.
 *
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {

    /**
     * Create array.
     *
     * @param type Type of array
     * @param size Size of array
     * @param <T>  Type of array
     * @return Array of selected type and size
     * @throws NullPointerException       If type is null
     * @throws NegativeArraySizeException If negative size
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] create(final Class<T> type, final int size)
            throws NullPointerException, NegativeArraySizeException {

        return (T[]) Array.newInstance(type, size);
    }
}
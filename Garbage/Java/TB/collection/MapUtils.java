package eu.aiskov.toolbox.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility for work with map.
 *
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public class MapUtils extends org.apache.commons.collections.MapUtils {

    public static final int SEPARATE_FALSE_PREDICATE = 0;
    public static final int SEPARATE_TRUE_PREDICATE = 1;

    /**
     * Create map from keys and values lists.
     * <p/>
     * <p/>
     * NB: Values with same keys will be overridden.
     *
     * @param keys   Lists of keys
     * @param values Lists of values
     * @param <K>    Keys type
     * @param <V>    Values type
     * @return Map generated from keys and values lists where key assigned with value which
     *         have same index
     * @throws IllegalArgumentException If keys or values are nulls or have different size
     */
    @SuppressWarnings({"unchecked", "TypeMayBeWeakened"})
    public static <K, V> Map<K, V> createMap(final List<K> keys, final List<V> values)

            throws IllegalArgumentException {

        return createMap((K[]) keys.toArray(), (V[]) values.toArray());
    }

    /**
     * Create map from keys and values array.
     * <p/>
     * <p/>
     * NB: Values with same keys will be overridden.
     *
     * @param keys   Array of keys
     * @param values Array of values
     * @param <K>    Keys type
     * @param <V>    Values type
     * @return Map generated from keys and values array's where key assigned with value which
     *         have same index
     * @throws IllegalArgumentException If keys or values are nulls or have different size
     */
    public static <K, V> Map<K, V> createMap(final K[] keys, final V[] values) throws IllegalArgumentException {

        if (keys == null || values == null || keys.length != values.length) {
            throw new IllegalArgumentException("Wrong arguments passed");
        }

        final Map<K, V> map = new HashMap<>(keys.length);

        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }

        return map;
    }

    /**
     * Create map from keys and values maps.
     * <p/>
     * <p/>
     * NB: Values with same keys will be overridden.
     *
     * @param keys   Map of keys
     * @param values Map of values
     * @param <I>    Indexes type
     * @param <K>    Keys type
     * @param <V>    Values type
     * @return Map generated from keys and values maps where key assigned with value which
     *         have same index (key for input maps)
     * @throws IllegalArgumentException If keys or values are nulls or have different size
     */
    public static <I, K, V> Map<K, V> createMap(final Map<I, K> keys, final Map<I, V> values) {

        if (keys == null || values == null || keys.size() != values.size()) {
            throw new IllegalArgumentException("Wrong arguments passed");
        }

        final Map<K, V> map = new HashMap<>(keys.size());

        for (final I entryKey : keys.keySet()) {
            map.put(keys.get(entryKey), values.get(entryKey));
        }

        return map;
    }

    /**
     * Separate entries of map to list of maps
     *
     * @param map       map which will be separated
     * @param predicate predicate which decide to which map goes result
     * @param <K>       Map key type
     * @param <V>       Map value type
     * @return [2] 0 - map with false result of predicate check, 1 - map with true result of predicate check
     */
    public static <K, V> Multi<Map<K, V>> separate(final Map<K, V> map, final Predicate<K> predicate) {

        @SuppressWarnings("unchecked")
        final Map<K, V>[] result = new HashMap[] {
                new HashMap<>(map.size()),
                new HashMap<>(map.size())
        };

        for (final Map.Entry<K, V> entry : map.entrySet()) {
            if (predicate.check(entry.getKey())) {
                result[SEPARATE_TRUE_PREDICATE].put(entry.getKey(), entry.getValue());
                continue;
            }

            result[SEPARATE_FALSE_PREDICATE].put(entry.getKey(), entry.getValue());
        }

        return new Multi<>(result);
    }

    public static <K, V> Set<V> getValuesSet(final Map<K, V> map) {

        return new HashSet<>(map.values());
    }
}
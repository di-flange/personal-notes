package eu.aiskov.toolbox;

import ee.jiss.resourceflow.utils.fop.Predicate;

/**
 * Utility for selection default value in case that main value not suitable.
 *
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public class DefaultUtils {

    /**
     * Select default if main value is null.
     *
     * @param target Main value
     * @param def    Default value
     * @param <T>    Type of value
     * @return Main value if it is not null, default value if main value is null.
     */
    public static <T> T select(final T target, final T def) {

        return target == null ? def : target;
    }

    /**
     * Select default if predicate for main value returns false.
     *
     * @param target    Main value
     * @param def       Default value
     * @param predicate Predicate for main value
     * @param <T>       Type of value
     * @return Main value if predicate returned true, default value if predicate returned false.
     */
    public static <T> T select(final T target, final T def, final Predicate<T> predicate) {

        return predicate.check(target) ? target : def;
    }
}
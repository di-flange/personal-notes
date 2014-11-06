package eu.aiskov.toolbox.fop;

/**
 * Predicate which always returns false.
 *
 * @param <T> Type of predicate input
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public class FalsePredicate<T> implements Predicate<T> {

    @Override
    public boolean check(final T target) {

        return false;
    }
}

package ee.jiss.resourceflow.utils.fop;

/**
 * Predicate which always returns true.
 *
 * @param <T> Type of predicate input
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
public class TruePredicate<T> implements Predicate<T> {

    @Override
    public boolean check(final T target) {

        return true;
    }
}

package ee.jiss.resourceflow.utils.fop;

/**
 * Container for check method which get object of T type and returns true or false.
 *
 * @param <T> Type of predicate input
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public interface Predicate<T> {

    Predicate<Object> FALSE_PREDICATE = new FalsePredicate<>();
    Predicate<Object> TRUE_PREDICATE = new TruePredicate<>();

    /**
     * Check which get object of T type and returns true or false
     *
     * @param target Target which will be checked
     * @return True or false, by check result
     */
    boolean check(T target);
}
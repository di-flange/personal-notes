package ee.jiss.resourceflow.utils.filter;

import ee.jiss.resourceflow.utils.fop.Function;

import java.util.Collection;

/**
 * Filter interface.
 *
 * @param <C> Collection type.
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public interface Filter<C extends Collection<?>> extends Function<C, C> {

    /**
     * Create new collection which filtrated with specified rule.
     *
     * @param in Filtration input
     * @return Filtration output
     */
    @Override
    C execute(C in);
}
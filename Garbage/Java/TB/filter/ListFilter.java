package ee.jiss.resourceflow.utils.filter;

import java.util.List;

/**
 * List filter interface.
 *
 * @param <T> Type of elements.
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public interface ListFilter<T> extends Filter<List<T>> {

    ListFilter<Object> NULL_FILTER = new NullListFilter<>();
    ListFilter<Object> DUPLICATE_FILTER = new DuplicateListFilter<>();
}
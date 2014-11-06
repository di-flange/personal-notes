package ee.jiss.resourceflow.utils.filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Filter for removing all duplicates from list.
 *
 * @param <T> Type of elements.
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
public class DuplicateListFilter<T> implements ListFilter<T> {

    @Override
    public List<T> execute(final List<T> in) {

        return new ArrayList<>(new HashSet<>(in));
    }
}

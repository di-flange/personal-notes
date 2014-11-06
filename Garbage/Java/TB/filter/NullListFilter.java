package ee.jiss.resourceflow.utils.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter for removing all nulls from list.
 *
 * @param <T> Type of elements.
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
public class NullListFilter<T> implements ListFilter<T> {

    @Override
    public List<T> execute(final List<T> in) {

        final List<T> out = new ArrayList<>(in.size());

        for (final T element : in) {
            if (element != null) {
                out.add(element);
            }
        }

        return out;
    }
}

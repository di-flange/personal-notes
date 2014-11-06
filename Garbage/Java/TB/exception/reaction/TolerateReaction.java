package ee.jiss.resourceflow.utils.exception.reaction;

import ee.jiss.resourceflow.utils.fop.Function;

/**
 * Zero reaction to exception, always returns zero.
 *
 * @param <T> Type of output
 */
public class TolerateReaction<T> implements Reaction<T> {

    @Override
    public T execute(final Exception in) {

        return null;
    }
}
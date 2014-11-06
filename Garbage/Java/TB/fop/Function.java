package eu.aiskov.toolbox.fop;

/**
 * Function interface.
 *
 * @param <I> Type of function input
 * @param <O> Type of function output
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public interface Function<I, O> {

    /**
     * Function execution
     *
     * @param in Function input
     * @return Function output
     */
    O execute(I in);
}
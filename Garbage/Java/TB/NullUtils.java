package ee.jiss.resourceflow.utils;

/**
 * Utility for replacing null value with zero or false.
 *
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public class NullUtils {

    /**
     * Replace null with zero.
     *
     * @param x Value object
     * @return Value if x not null, zero if x is null.
     */
    public static int orZero(final Integer x) {

        return x == null ? 0 : x;
    }

    /**
     * Replace null with zero.
     *
     * @param x Value object
     * @return Value if x not null, zero if x is null.
     */
    public static long orZero(final Long x) {

        return x == null ? 0L : x;
    }

    /**
     * Replace null with zero.
     *
     * @param x Value object
     * @return Value if x not null, zero if x is null.
     */
    public static float orZero(final Float x) {

        return x == null ? 0.0F : x;
    }

    /**
     * Replace null with zero.
     *
     * @param x Value object
     * @return Value if x not null, zero if x is null.
     */
    public static double orZero(final Double x) {

        return x == null ? 0.0 : x;
    }

    /**
     * Replace null with false.
     *
     * @param x Value object
     * @return Value if x not null, false if x is null.
     */
    public static boolean orFalse(final Boolean x) {

        return x == null ? false : x;
    }
}

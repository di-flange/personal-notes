package ee.jiss.resourceflow.utils;

import ee.jiss.resourceflow.utils.fop.Predicate;

/**
 * Utility for work with pagination.
 *
 * @author Anton Iskov (iskov.anton@yandex.com)
 */
@SuppressWarnings("UnusedDeclaration")
public class PaginationUtils {

    /**
     * Function for calculation count of pages
     *
     * @param count Count of elements
     * @param size  Size of page
     * @return Count of pages of specified size,which can hold all elements
     * @throws IllegalArgumentException If count or size is invalid
     */
    public static int pageCount(final long count, final int size) throws IllegalArgumentException {

        if (count < 0 || size <= 0) {
            throw new IllegalArgumentException("Wrong arguments");
        }

        final double x = (double) count / size;
        final int y = (int) x;

        return x > y || y == 0 ? y + 1 : y;
    }

    /**
     * Function for calculation number of first element.
     *
     * @param page Number of page.
     * @param size Size of page.
     * @return Number of first element.
     * @throws IllegalArgumentException If page or size is invalid
     */
    public static int first(final int page, final int size) throws IllegalArgumentException {

        if (page <= 0 || size <= 0) {
            throw new IllegalArgumentException("Wrong arguments");
        }

        return (page - 1) * size + 1;
    }

    /**
     * Function for obtaining predicate for check page size
     *
     * @param max Max page size
     * @return Predicate for check
     */
    public static Predicate<Integer> getSizePredicate(final Integer max) {

        return new PageSizePredicate(max);
    }

    public static Predicate<Integer> getNumberPredicate() {

        return new PageNumberPredicate();
    }

    private static class PageNumberPredicate implements Predicate<Integer> {

        @Override
        public boolean check(final Integer target) {

            return target != null && target > 0;
        }
    }

    private static class PageSizePredicate implements Predicate<Integer> {

        private static final int DEFAULT_MAX = 1_000;
        private final int max;

        private PageSizePredicate(final Integer max) {

            super();

            this.max = max != null && max > 0 ? max : DEFAULT_MAX;
        }

        @Override
        public boolean check(final Integer target) {

            return target != null && target > 0 && target <= this.max;
        }
    }
}
package java.awt.print;

import java.lang.annotation.Native;

/**
 * The {@code Pageable} implementation represents a set of
 * pages to be printed. The {@code Pageable} object returns
 * the total number of pages in the set as well as the
 * {@link PageFormat} and {@link Printable} for a specified page.
 * @see java.awt.print.PageFormat
 * @see java.awt.print.Printable
 */
public interface Pageable {

    /**
     * This constant is returned from the
     * {@link #getNumberOfPages() getNumberOfPages}
     * method if a {@code Pageable} implementation does not know
     * the number of pages in its set.
     */
    @Native int UNKNOWN_NUMBER_OF_PAGES = -1;

    /**
     * Returns the number of pages in the set.
     * To enable advanced printing features,
     * it is recommended that {@code Pageable}
     * implementations return the true number of pages
     * rather than the
     * UNKNOWN_NUMBER_OF_PAGES constant.
     * @return the number of pages in this {@code Pageable}.
     */
    int getNumberOfPages();

    /**
     * Returns the {@code PageFormat} of the page specified by
     * {@code pageIndex}.
     * @param pageIndex the zero based index of the page whose
     *            {@code PageFormat} is being requested
     * @return the {@code PageFormat} describing the size and
     *          orientation.
     * @throws IndexOutOfBoundsException if
     *          the {@code Pageable} does not contain the requested
     *          page.
     */
    PageFormat getPageFormat(int pageIndex)
        throws IndexOutOfBoundsException;

    /**
     * Returns the {@code Printable} instance responsible for
     * rendering the page specified by {@code pageIndex}.
     * @param pageIndex the zero based index of the page whose
     *            {@code Printable} is being requested
     * @return the {@code Printable} that renders the page.
     * @throws IndexOutOfBoundsException if
     *            the {@code Pageable} does not contain the requested
     *            page.
     */
    Printable getPrintable(int pageIndex)
        throws IndexOutOfBoundsException;
}

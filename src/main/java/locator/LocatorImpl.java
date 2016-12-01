package locator;

/**
 * Problem:
 * <p>
 * Your class, LocatorSolution, must find the index of an item efficiently (1) from within a sorted array of candidates (2). The implementation must be written
 * from first principles, and must not make use of classes not explicitly mentioned in the interface definition. The solution should adhere to the specification
 * below when supplied with reasonable inputs (3) and must provide a no-throw guarantee (4).
 * <p>
 * Observations:
 * <p>
 * (1) efficiently - Time ?, space ? - Rather difficult to meet requirement without further information, for example typical data set size, machine
 * architecture, nature of individual elements etc
 * <p>
 * (2) Sorted - how ascending ?, descending ?, duplicates allowed ?
 * <p>
 * (3) What defines reasonable ?
 * <p>
 * (4) Two strategies here, (a) make it robust, (2) wrap in a try..catch that will scoop up any failure.
 * <p>
 * Solution:
 * <p>
 * (1) Implemented simple binary chop. Will work up to MAX_INT elements if you are so inclined (!)
 * <p>
 * (2) Deals with ascending and descending.  Ok with duplicates but index returned is not guaranteed to any particular duplicate
 * <p>
 * (3) Good question
 * <p>
 * (4) Prefer robust
 */
public class LocatorImpl implements Locator {
    /**
     * @param itemSought the item for which the index is sought
     * @param candidates a sorted array of items, from within which the itemSought must be found
     * @return the zero based index of the itemSought within the array of candidates. In the event that
     * the itemSought is not found in the candidates array an index of -1 is returned.
     */
    @Override
    public int getIndex(final String itemSought, final String[] candidates) {
        // sanity checks
        if ((null != itemSought) && (null != candidates) && (candidates.length > 0)) {
            // Ok, basic binary chop setup
            int left = 0, right = candidates.length - 1;
            // Slight wrinkle - sort order not specified so deal with both
            boolean ascending = 0 > candidates[left].compareTo(candidates[right]);
            int position, comparison;
            while (left <= right) {
                position = (left + right) >>> 1; // counteract possible overflow on very large arrays
                comparison = ascending ? (itemSought.compareTo(candidates[position])) : candidates[position].compareTo(itemSought);
                if (0 == comparison)
                    return position;
                else if (comparison < 0)
                    right = position - 1;
                else
                    left = position + 1;
            }
        }
        return -1;
    }
}

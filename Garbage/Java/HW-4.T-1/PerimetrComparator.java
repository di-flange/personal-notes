/**
 * Homework 4
 */
package figures;

import java.util.Comparator;

/**
 * Compare tool for sorting of Figure interfaces array by perimetr.
 * 
 * @author Anton Ishkov
 */
public class PerimetrComparator implements Comparator<Figure>
{
    /**
     * Simple constructor which set default order.
     */
    public PerimetrComparator()
    {
        // Set order
        this.desc = false;
    }

    /**
     * Full constructor which set needed order.
     */
    public PerimetrComparator(boolean desc)
    {
        // Set order
        this.desc = desc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    /**
     * Compare two elements.
     */
    @Override
    public int compare(Figure arg0, Figure arg1)
    {
        if(this.desc)
        {
            return new Double(arg1.getPerimetr()).compareTo(new Double(arg0
                    .getPerimetr()));
        }
        return new Double(arg0.getPerimetr()).compareTo(new Double(arg1
                .getPerimetr()));
    }

    /**
     * Sort order.
     */
    private boolean desc = false;
}
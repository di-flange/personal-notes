/**
 * Homework 4
 */
package figures;

import java.util.Comparator;

/**
 * Compare tool for sorting of Figure interfaces array by square.
 * 
 * @author Anton Ishkov
 */
public class SquareComparator implements Comparator<Figure>
{
    /**
     * Simple constructor which set default order.
     */
    public SquareComparator()
    {
        // Set order
        this.desc = false;
    }

    /**
     * Full constructor which set needed order.
     */
    public SquareComparator(boolean desc)
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
            return new Double(arg1.getSquare()).compareTo(new Double(arg0
                    .getSquare()));
        }
        return new Double(arg0.getSquare()).compareTo(new Double(arg1
                .getSquare()));
    }

    /**
     * Sort order.
     */
    private boolean desc = false;
}
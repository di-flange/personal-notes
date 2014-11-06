/**
 * Homework 4
 */
package figures;

/**
 * Class of triangle figures.
 * 
 * @author Anton Ishkov
 */
public class Triangle implements Figure
{
    /**
     * Simple null triangle constructor. Which block all operations before
     * points will be changed.
     */
    public Triangle()
    {
        this.errorTrigger = true;
    }

    /**
     * Full triangle constructor. Which create triangle by four points.
     * 
     * @param pnt0
     *            Point one.
     * @param pnt1
     *            Point two.
     * @param pnt2
     *            Point three
     */
    public Triangle(Point pnt0, Point pnt1, Point pnt2)
    {
        this.setPoints(pnt0, pnt1, pnt2);
    }

    /**
     * Set points of figure and check it.
     * 
     * @param pnt0
     *            Point one.
     * @param pnt1
     *            Point two.
     * @param pnt2
     *            Point three
     */
    public void setPoints(Point pnt0, Point pnt1, Point pnt2)
    {
        // Check points to similar
        if(pnt0.equals(pnt1) || pnt0.equals(pnt2) || pnt1.equals(pnt2))
        {
            this.errorTrigger = true;
            return;
        }

        // Set points
        this.pointZero = pnt0;
        this.pointOne = pnt1;
        this.pointTwo = pnt2;

        this.errorTrigger = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getEdge(int)
     */
    /**
     * Getter for getting information about any figure edges, but if entered
     * points are apex of the triangle it return one value for each edges.
     * 
     * @param edge
     *            Not used.
     * @return Length of an edge if triangle if all points are accepted or -1 if
     *         points of triangle are corrupted.
     */
    @Override
    public double getEdge(int edge)
    {
        // Check blocking and obtained parameter
        if(this.errorTrigger || edge > 3 || edge < 1)
        {
            return - 1;
        }

        // Calculate
        if(edge == 1)
        {
            return this.pointOne.distance(this.pointTwo);
        }
        if(edge == 2)
        {
            return this.pointTwo.distance(this.pointZero);
        }
        return this.pointZero.distance(this.pointOne);
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getPerimetr()
     */
    /**
     * Getter for getting information about current triangle periphery.
     * 
     * @return Periphery of current triangle, or -1 if points of triangle are
     *         corrupted.
     */
    @Override
    public double getPerimetr()
    {
        // Check blocking
        if(this.errorTrigger)
        {
            return - 1;
        }

        // Calculate
        return this.pointZero.distance(this.pointOne)
                + this.pointOne.distance(this.pointTwo)
                + this.pointTwo.distance(this.pointZero);
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getSquare()
     */
    /**
     * Getter for getting information about current triangle square.
     * 
     * @return Square of current triangle, or -1 if points of triangle are
     *         corrupted.
     */
    @Override
    public double getSquare()
    {
        // Check blocking
        if(this.errorTrigger)
        {
            return - 1;
        }

        // Declare variables
        double a = this.pointZero.distance(this.pointOne);
        double b = this.pointOne.distance(this.pointTwo);
        double c = this.pointTwo.distance(this.pointZero);

        // Calculate
        return Math.sqrt(( a + b + c ) * ( b + c - a ) * ( a + c - b )
                * ( a + b - c )) / 4;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    /**
     * Return main information about object in one string.
     */
    @Override
    public String toString()
    {
        // Check blocking
        if(this.errorTrigger)
        {
            return "ERROR: Triangle points not seted or corrupted.";
        }
        
        // Compile string
        return "Triangle [" + this.pointZero + "; " + this.pointOne + "; "
                + this.pointTwo + "]";
    }

    /**
     * Block trigger.
     */
    private boolean errorTrigger = false;

    /**
     * Point zero
     */
    private Point   pointZero;

    /**
     * Point one
     */
    private Point   pointOne;

    /**
     * Point two
     */
    private Point   pointTwo;
}
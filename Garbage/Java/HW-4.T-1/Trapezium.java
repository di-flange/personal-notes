/**
 * Homework 4
 */
package figures;

/**
 * Class of trapezium figures.
 * 
 * @author Anton Ishkov
 */
public class Trapezium implements Figure
{
    /**
     * Simple null trapezium constructor. Which block all operations before
     * points will be changed.
     */
    public Trapezium()
    {
        this.errorTrigger = true;
    }

    /**
     * Full trapezium constructor. Which create trapezium by four points.
     * 
     * @param pnt0
     *            Point one.
     * @param pnt1
     *            Point two.
     * @param pnt2
     *            Point three
     * @param pnt3
     *            Point four.
     */
    public Trapezium(Point pnt0, Point pnt1, Point pnt2, Point pnt3)
    {
        this.setPoints(pnt0, pnt1, pnt2, pnt3);
    }

    /**
     * Set points of figure and check it.
     * 
     * NOTE: This method must build up points so that the edge 0-1 has always
     * been the basis and other points will be in right order.
     * 
     * @param pnt0
     *            Point one.
     * @param pnt1
     *            Point two.
     * @param pnt2
     *            Point three
     * @param pnt3
     *            Point four.
     */
    public void setPoints(Point pnt0, Point pnt1, Point pnt2, Point pnt3)
    {
        // Check points to similar
        if(pnt0.equals(pnt1) || pnt0.equals(pnt2) || pnt0.equals(pnt3)
                || pnt1.equals(pnt2) || pnt1.equals(pnt3) || pnt2.equals(pnt3))
        {
            this.errorTrigger = true;
            return;
        }

        // Declare variables
        double angleOne = pnt0.angle(pnt1, pnt2);
        double angleTwo = pnt0.angle(pnt2, pnt3);
        double angleThree = pnt0.angle(pnt1, pnt3);

        Point tmp;

        // Set first point
        this.pointZero = pnt0;
        
        // Check angle
        if((int)(angleOne + angleTwo) == (int)angleThree)
        {
            this.pointOne = pnt1;
            this.pointTwo = pnt2;
            this.pointThree = pnt3;
        }
        else if((int)(angleOne + angleThree) == (int)angleTwo)
        {
            this.pointOne = pnt2;
            this.pointTwo = pnt1;
            this.pointThree = pnt3;
        }
        else if((int)(angleTwo + angleThree) == (int)angleOne)
        {
            this.pointOne = pnt1;
            this.pointTwo = pnt3;
            this.pointThree = pnt2;
        }
        else
        {
            this.errorTrigger = true;
            return;
        }

        // Replace points
        if(this.pointZero.angle(this.pointOne, this.pointThree)
                + this.pointThree.angle(this.pointZero, this.pointTwo) != 180
                || this.pointTwo.angle(this.pointOne, this.pointThree)
                        + this.pointOne.angle(this.pointZero, this.pointTwo) != 180)
        {
            if(this.pointZero.angle(this.pointOne, this.pointThree)
                    + this.pointOne.angle(this.pointZero, this.pointTwo) != 180
                    || this.pointTwo.angle(this.pointOne, this.pointThree)
                            + this.pointThree.angle(this.pointZero,
                                    this.pointTwo) != 180)
            {
                this.errorTrigger = true;
                return;
            }
            else
            {
                // Move points
                tmp = this.pointZero;
                this.pointZero = this.pointOne;
                this.pointOne = this.pointTwo;
                this.pointTwo = this.pointThree;
                this.pointThree = tmp;
            }
        }

        this.errorTrigger = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getEdge(int)
     */
    /**
     * Getter for getting information about any figure edges, but if entered
     * points are apex of the trapezium it return one value for each edges.
     * 
     * @param edge
     *            Not used.
     * @return Length of an edge if trapezium if all points are accepted or -1
     *         if points of trapezium are corrupted.
     */
    @Override
    public double getEdge(int edge)
    {
        // Check blocking and obtained parameter
        if(this.errorTrigger || edge > 4 || edge < 1)
        {
            return - 1;
        }

        // Calculate
        if(edge == 2)
        {
            return this.pointOne.distance(this.pointTwo);
        }
        else if(edge == 3)
        {
            return this.pointTwo.distance(this.pointThree);
        }
        else if(edge == 4)
        {
            return this.pointThree.distance(this.pointZero);
        }

        return this.pointZero.distance(this.pointOne);
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getPerimetr()
     */
    /**
     * Getter for getting information about current trapezium periphery.
     * 
     * @return Periphery of current trapezium, or -1 if points of trapezium are
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
                + this.pointTwo.distance(this.pointThree)
                + this.pointThree.distance(this.pointZero);
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getSquare()
     */
    /**
     * Getter for getting information about current trapezium square.
     * 
     * @return Square of current trapezium, or -1 if points of trapezium are
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
        double b = this.pointTwo.distance(this.pointThree);
        double c = this.pointOne.distance(this.pointTwo);
        double d = this.pointZero.distance(this.pointThree);

        // Calculate
        return ( a + b )
                * Math.sqrt(Math.pow(c, 2.0)
                        - ( Math.pow(b - a, 2.0) - Math.pow(d, 2.0) + Math.pow(
                                c, 2.0) ) / ( 2 * b - 2 * a )) / 2;
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
            return "ERROR: Trapezium points not seted or corrupted.";
        }
        
        // Compile string
        return "Trapezium [" + this.pointZero + "; " + this.pointOne + "; "
                + this.pointTwo + "; " + this.pointThree + "]";
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

    /**
     * Point three
     */
    private Point   pointThree;
}
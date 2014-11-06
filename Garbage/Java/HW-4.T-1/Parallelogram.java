/**
 * Homework 4
 */
package figures;

/**
 * Class of parallelograms figures.
 * 
 * @author Anton Ishkov
 */
public class Parallelogram implements Figure
{
    /**
     * Simple null parallelogram constructor. Which block all operations before
     * points will be changed.
     */
    public Parallelogram()
    {
        this.errorTrigger = true;
    }

    /**
     * Full parallelogram constructor. Which create parallelogram by four
     * points.
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
    public Parallelogram(Point pnt0, Point pnt1, Point pnt2, Point pnt3)
    {
        this.setPoints(pnt0, pnt1, pnt2, pnt3);
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

        // Set first point
        this.pointZero = pnt0;

        // Check angle
        if((int)(angleOne + angleTwo) ==  (int)angleThree)
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

        // Check edges
        if(this.pointZero.distance(this.pointOne) != this.pointThree
                .distance(this.pointTwo)
                || this.pointOne.distance(this.pointTwo) != this.pointZero
                        .distance(this.pointThree))
        {
            this.errorTrigger = true;
            return;
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
     * points are apex of the parallelogram it return one value for each edges.
     * 
     * @param edge
     *            Not used.
     * @return Length of an edge if parallelogram if all points are accepted or
     *         -1 if points of parallelogram are corrupted.
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
        if(edge % 2 == 0)
        {
            return this.pointOne.distance(this.pointTwo);
        }
        return this.pointZero.distance(this.pointOne);
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getPerimetr()
     */
    /**
     * Getter for getting information about current parallelogram periphery.
     * 
     * @return Periphery of current parallelogram, or -1 if points of
     *         parallelogram are corrupted.
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
        return this.pointZero.distance(this.pointOne) * 2
                + this.pointOne.distance(this.pointTwo) * 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getSquare()
     */
    /**
     * Getter for getting information about current parallelogram square.
     * 
     * @return Square of current parallelogram, or -1 if points of parallelogram
     *         are corrupted.
     */
    @Override
    public double getSquare()
    {
        // Check blocking
        if(this.errorTrigger)
        {
            return - 1;
        }
        
        // Calculate
        return this.pointZero.distance(this.pointOne)
                * this.pointZero.distance(this.pointThree)
                * Math.sin(Math.toRadians(this.pointZero.angle(this.pointOne,
                        this.pointThree)));
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
            return "ERROR: Parallelogram points not seted or corrupted.";
        }
        
        // Compile string
        return "Parallelogram [" + this.pointZero + "; " + this.pointOne + "; "
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
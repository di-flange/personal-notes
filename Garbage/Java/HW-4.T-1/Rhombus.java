/**
 * Homework 4
 */
package figures;

/**
 * Class of rhomb figures.
 * 
 * @author Anton Ishkov
 */
public class Rhombus implements Figure
{
    /**
     * Simple null rhombus constructor. Which block all operations before points
     * will be changed.
     */
    public Rhombus()
    {
        this.errorTrigger = true;
    }

    /**
     * Full rhombus constructor. Which create rhombus by four points.
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
    public Rhombus(Point pnt0, Point pnt1, Point pnt2, Point pnt3)
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
        if(angleOne == angleTwo && angleOne < angleThree)
        {
            this.pointOne = pnt1;
            this.pointTwo = pnt2;
            this.pointThree = pnt3;
        }
        else if(angleOne == angleThree && angleOne < angleTwo)
        {
            this.pointOne = pnt2;
            this.pointTwo = pnt1;
            this.pointThree = pnt3;
        }
        else if(angleTwo == angleThree && angleTwo < angleOne)
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
        if(this.pointZero.distance(this.pointOne) != this.pointOne
                .distance(this.pointTwo)
                || this.pointOne.distance(this.pointTwo) != this.pointTwo
                        .distance(this.pointThree)
                || this.pointTwo.distance(this.pointThree) != this.pointThree
                        .distance(this.pointZero))
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
     * points are apex of the rhombus it return one value for each edges.
     * 
     * @param edge
     *            Not used.
     * @return Length of an edge if rhombus if all points are accepted or -1 if
     *         points of rhombus are corrupted.
     */
    @Override
    public double getEdge(int edge)
    {
        // Check blocking
        if(this.errorTrigger)
        {
            return - 1;
        }

        // Calculate
        return this.pointZero.distance(this.pointOne);
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getPerimetr()
     */
    /**
     * Getter for getting information about current rhombus periphery.
     * 
     * @return Periphery of current rhombus, or -1 if points of rhombus are
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
        return this.pointZero.distance(this.pointOne) * 4;
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getSquare()
     */
    /**
     * Getter for getting information about current rhombus square.
     * 
     * @return Square of current rhombus, or -1 if points of rhombus are
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

        // Calculate
        return this.pointZero.distance(this.pointTwo)
                * this.pointOne.distance(this.pointThree) / 2;
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
            return "ERROR: Rhombus points not seted or corrupted.";
        }
        
        // Compile string
        return "Rhombus [" + pointOne + "; " + pointThree + "; " + pointTwo
                + "; " + pointZero + "]";
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
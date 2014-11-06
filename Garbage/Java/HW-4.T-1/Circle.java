/**
 * Homework 4
 */
package figures;

/**
 * Class of circle figures.
 * 
 * @author Anton Ishkov
 */
public class Circle implements Figure
{
    /**
     * Simple null circle constructor. Which block all operations before points
     * will be changed.
     */
    public Circle()
    {
        // Set block
        this.errorTrigger = true;
    }

    /**
     * Full circle constructor. Which create circle by two points.s
     * 
     * @param center
     *            Center point of circus.
     * @param radius
     *            Point in circus periphery.
     */
    public Circle(Point center, Point radius)
    {
        // Set fields
        this.center = center;
        this.radius = radius;

        // Control errors while setting
        if(this.center.equals(this.radius))
        {
            // Set block
            this.errorTrigger = true;
        }
    }

    /**
     * Setter for setting center of this circle.
     * 
     * @param center
     *            Point for setting as center.
     */
    public void setCenter(Point center)
    {
        // Compare two points
        if(this.radius.equals(center))
        {
            this.errorTrigger = true;
        }
        else
        {
            this.errorTrigger = false;
        }

        // Set
        this.center = center;
    }

    /**
     * Setter for setting second point of radius for this circle.
     * 
     * @param center
     *            Point for setting as second radius point.
     */
    public void setRadius(Point radius)
    {
        // Compare two points
        if(this.center.equals(radius))
        {
            this.errorTrigger = true;
        }
        else
        {
            this.errorTrigger = false;
        }

        // Set
        this.radius = radius;
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getEdge()
     */
    /**
     * Getter for getting information about any figure edges, but in this case
     * it is alias of getting current circle periphery.
     * 
     * @param edge
     *            Not used
     * @return Periphery of current circle, or -1 if points of circle are
     *         corrupted.
     */
    @Override
    public double getEdge(int edge)
    {
        // Call to alias
        return this.getPerimetr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getPerimetr()
     */
    /**
     * Getter for getting information about current circle periphery.
     * 
     * @return Periphery of current circle, or -1 if points of circle are
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
        return Math.PI * this.center.distance(this.radius) * 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see figures.Figure#getSquare()
     */
    /**
     * Getter for getting information about current circle square.
     * 
     * @return Square of current circle, or -1 if points of circle are
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
        return Math.PI * Math.pow(this.center.distance(this.radius), 2.0);
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
            return "ERROR: Circle points not seted or corrupted.";
        }
        
        // Compile string
        return "Circle [center: " + this.center + ", radius: "
                + this.center.distance(this.radius) + "]";
    }

    /**
     * Center of circle.
     */
    private Point   center       = new Point();

    /**
     * Point in circus periphery.
     */
    private Point   radius       = new Point();

    /**
     * Block trigger.
     */
    private boolean errorTrigger = false;
}
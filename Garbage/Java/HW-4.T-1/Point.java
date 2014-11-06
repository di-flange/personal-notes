/**
 * Homework 4
 */
package figures;

/**
 * @author Anton Ishkov
 * 
 */
final public class Point
{
    /**
     * Simple constructor of point, do nothing.
     */
    public Point()
    {
    }

    /**
     * Full constructor which set coordinates.
     * 
     * @param posX
     *            Coordinates by X.
     * @param posY
     *            Coordinates by Y.
     */
    public Point(double posX, double posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Getter for getting information about point coordinates.
     * 
     * @return Coordinates by X.
     */
    public double getPosX()
    {
        return this.posX;
    }

    /**
     * Setter for setting information about coordinates.
     * 
     * @param posX
     *            Coordinates by X to set.
     */
    public void setPosX(double posX)
    {
        this.posX = posX;
    }

    /**
     * Getter for getting information about point coordinates.
     * 
     * @return Coordinates by Y.
     */
    public double getPosY()
    {
        return this.posY;
    }

    /**
     * Setter for setting information about coordinates.
     * 
     * @param posY
     *            Coordinates by Y to set.
     */
    public void setPosY(double posY)
    {
        this.posY = posY;
    }

    /**
     * Calculator of distance between two points.
     * 
     * @param pnt
     *            Destination point.
     * @return Distance between two points.
     */
    public double distance(Point pnt)
    {
        double result =
                Math.sqrt(Math.pow(this.posX - pnt.getPosX(), 2.0)
                        + Math.pow(this.posY - pnt.getPosY(), 2.0));

        if(result < 0)
        {
            result *= - 1;
        }

        return result;
    }

    /**
     * Calculator of angle between two vectors which based on current point.
     * 
     * @param edge1
     *            Vector one.
     * @param edge2
     *            Vector two.
     * @return Angle between two vectors as degree.
     */
    public double angle(Point edge1, Point edge2)
    {
        return Math
                .toDegrees(Math
                        .acos(( ( edge1.getPosX() - this.posX )
                                * ( edge2.getPosX() - this.posX ) + ( edge1
                                .getPosY() - this.posY )
                                * ( edge2.getPosY() - this.posY ) )
                                / Math
                                        .sqrt(( Math
                                                .pow(
                                                        ( edge1.getPosX() - this.posX ),
                                                        2) + Math.pow(( edge1
                                                .getPosY() - this.posY ), 2) )
                                                * ( Math
                                                        .pow(
                                                                ( edge2
                                                                        .getPosX() - this.posX ),
                                                                2) + Math
                                                        .pow(
                                                                ( edge2
                                                                        .getPosY() - this.posY ),
                                                                2) ))));
    }
    

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    /**
     * Generate hash code
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(posX);
        result = prime * result + (int)( temp ^ ( temp >>> 32 ) );
        temp = Double.doubleToLongBits(posY);
        result = prime * result + (int)( temp ^ ( temp >>> 32 ) );
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    /**
     * Compare two points.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Point other = (Point)obj;
        if(Double.doubleToLongBits(posX) != Double.doubleToLongBits(other.posX))
            return false;
        if(Double.doubleToLongBits(posY) != Double.doubleToLongBits(other.posY))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    /**
     * Return coordinates in string.
     */
    @Override
    public String toString()
    {
        return "(" + posX + ";" + posY + ")";
    }

    /**
     * Coordinate by X.
     */
    private double posX;

    /**
     * Coordinate by Y
     */
    private double posY;
}
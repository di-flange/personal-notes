/**
 * Homework 3
 */
package publishing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which can contains information about any type. It extends a Publication
 * class which store main information about this items and guarantees the
 * assignment to each item an unique identifier. And Type class add to it fields
 * which contents length, format and size of type.
 * 
 * @author Anton Ishkov
 */
public class Type extends Publication
{
    /**
     * The simple version of constructor, which sets only the identification
     * number.will be used as identifier of this element.
     */
    public Type()
    {
        // Installation of a unique identifier
        super();
    }

    /**
     * The version of full constructor, which sets all field from obtained
     * parameters, and generate auto number which will be used as identifier of
     * this element.
     * 
     * @param length
     *            Float for setting as length of this type.
     * @param format
     *            Format of this type for setting.
     * @param size
     *            Float for setting as size of this type.
     * @param name
     *            Name of this book for setting.
     * @param author
     *            Author of this book for setting.
     * @param lang
     *            Language on which is written this book for setting.
     * @param descript
     *            Description of this book for setting.
     */
    public Type(float length, String format, float size, String name,
            String author, Language lang, String descript)
    {
        // Initialize basic fields which inheritances from the super class
        super(name, author, lang, descript);

        // Set current class fields
        this.length = length;
        this.format = format;
        this.size = size;
    }

    /**
     * The version of full constructor, which sets all field from obtained
     * parameters and one object of Publication class. While this operation
     * generated auto number which will be used as identifier of this element.
     * 
     * @param length
     *            Float for setting as length of this type.
     * @param format
     *            Format of this type for setting.
     * @param size
     *            Float for setting as size of this type.
     * @param template
     *            Object of Publication which fields will be copied to this
     *            object.
     */
    public Type(float length, String format, float size, Publication template)
    {
        // Copy fields from object
        super(template);

        // Set current class fields
        this.length = length;
        this.format = format;
        this.size = size;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    /**
     * A simple method to get the basic parameters of the object, returns a
     * string containing the name of the type, its author and the ID, as well as
     * the size and length.
     */
    @Override
    public String toString()
    {
        // Compile the returning string
        return super.toString() + " length: " + this.getFormatedLength() + " - "
                + this.size;
    }

    /**
     * This method is way to get information about type from user with console
     * input stream.
     * 
     * @param target
     *            Parameter which select field which we will get from user while
     *            method executing.
     * @return It return true if data from user is accepted and false if it
     *         corrupted or target parameter can't be used.
     * @throws IOException
     */
    public boolean getData(String target) throws IOException
    {
        if(target.equalsIgnoreCase("author") || target.equalsIgnoreCase("name")
                || target.equalsIgnoreCase("description")
                || target.equalsIgnoreCase("language"))
        {
            if(super.getData(target))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        if(target.equalsIgnoreCase("all"))
        {
            // Get them all
            if(! super.getData(target))
            {
                return false;
            }

            if(! this.getDataLength())
            {
                return false;
            }

            if(! this.getDataFormat())
            {
                return false;
            }

            if(! this.getDataSize())
            {
                return false;
            }
        }
        else
        {
            // Get type length
            if(target.equalsIgnoreCase("length"))
            {
                if(! this.getDataLength())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            // Get type format
            if(target.equalsIgnoreCase("format"))
            {
                if(! this.getDataFormat())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            // Get type format
            if(target.equalsIgnoreCase("size"))
            {
                if(! this.getDataSize())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }

        return true;
    }

    /**
     * Getter for getting length of this type.
     * 
     * @return The length of this type.
     */
    public float getLength()
    {
        return this.length;
    }

    /**
     * Getter for getting formated length of this type.
     * 
     * @return The formated length of this type.
     */
    public String getFormatedLength()
    {
        return Integer.toString((int)this.length)
                + ":"
                + Integer
                        .toString((int)( ( this.length - (int)this.length ) * 60 ));
    }

    /**
     * Setter for setting information about length of this type.
     * 
     * @param length
     *            The number to set as length of this type.
     */
    public void setLength(float length)
    {
        this.length = length;
    }

    /**
     * Setter for setting information about length of this type.
     * 
     * @param length
     *            The formated string to set as length of this type.
     * @return Return true is data parsed or false if it corrupted.
     */
    public boolean setFormatedLength(String length)
    {
        if(! length.matches("^[0-9]{1,1000}?:[0-9]{0,3}?$"))
        {
            try
            {
                this.length = Float.parseFloat(length);
            }
            catch(java.lang.NumberFormatException ex)
            {
                return false;
            }

            return true;
        }

        // Declare variables
        float min = 0;
        float sec = 0;

        StringBuffer rsltEnd = new StringBuffer();
        StringBuffer rsltStart = new StringBuffer();

        Matcher matchStart = Pattern.compile("^[0-9]*:").matcher(length);
        Matcher matchEnd = Pattern.compile(":[0-9]*$").matcher(length);

        // Parse string
        if(matchEnd.find() && matchStart.find())
        {
            matchEnd.appendReplacement(rsltStart, "");
            matchEnd.appendTail(rsltStart);

            matchStart.appendReplacement(rsltEnd, "");
            matchStart.appendTail(rsltEnd);
        }

        try
        {
            min = Float.parseFloat(rsltStart.toString());
            sec = Float.parseFloat(rsltEnd.toString());
        }
        catch(java.lang.NumberFormatException ex)
        {
            return false;
        }

        if(sec > 60)
        {
            return false;
        }

        this.length = min + sec / 60;
        return true;
    }

    /**
     * Getter for getting size of this type.
     * 
     * @return The size of this type.
     */
    public float getSize()
    {
        return this.size;
    }

    /**
     * Setter for setting information about size of this type.
     * 
     * @param size
     *            The float to set as this type size.
     */
    public void setSize(float size)
    {
        this.size = size;
    }

    /**
     * Getter for getting size of this type.
     * 
     * @return The size of this type.
     */
    public String getFormat()
    {
        return this.format;
    }

    /**
     * Setter for setting information about format of this type.
     * 
     * @param format
     *            The float to set as this type format.
     */
    public void setFormat(String format)
    {
        this.format = format;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    /**
     * Calculator of hash code of object.
     * 
     * @return Hash code generated from main fields of object without ID.
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;

        return prime
                * ( prime
                        * ( prime * super.hashCode() + ( ( format == null ) ? 0
                                : format.hashCode() ) ) + Float
                        .floatToIntBits(length) ) + Float.floatToIntBits(size);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    /**
     * Comparing of two objects without ID.
     */
    @Override
    public boolean equals(Object obj)
    {
        // Return true if the resulting object is a reference to the same object
        if(this == obj)
        {
            return true;
        }

        // Return false if super objects is not similar
        if(! super.equals(obj))
        {
            return false;
        }

        // Compare class's of this and obtained object
        if(getClass() != obj.getClass())
        {
            return false;
        }

        // Convert object to current class
        Type other = (Type)obj;

        // Compare fields
        if(format == null
                && other.format != null
                || ! format.equals(other.format)
                || Float.floatToIntBits(length) != Float
                        .floatToIntBits(other.length)
                || Float.floatToIntBits(size) != Float
                        .floatToIntBits(other.size))
        {
            return false;
        }

        return true;
    }

    /**
     * Get from console size of this type.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataSize() throws IOException
    {
        // Declare variables
        BufferedReader input = new BufferedReader(new InputStreamReader(
                System.in));
        String inString;
        float inFloat;

        System.out.print("Please, enter type size:");
        inString = input.readLine();

        if(inString.isEmpty())
        {
            return false;
        }

        try
        {
            inFloat = Float.parseFloat(inString);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }

        this.size = inFloat;
        return true;
    }

    /**
     * Get from console format of type.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataFormat() throws IOException
    {
        // Declare variables
        BufferedReader input = new BufferedReader(new InputStreamReader(
                System.in));
        String inString;

        System.out.print("Please, enter type format:");
        inString = input.readLine();

        if(inString.isEmpty())
        {
            return false;
        }

        this.format = inString;
        return true;
    }

    /**
     * Get from console length of type.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataLength() throws IOException
    {
        // Declare variables
        BufferedReader input = new BufferedReader(new InputStreamReader(
                System.in));
        String inString;

        System.out.print("Please, enter type length:");
        inString = input.readLine();
        
        if(inString.isEmpty())
        {
            return false;
        }
        
        if(! this.setFormatedLength(inString))
        {
            return false;
        }
        
        return true;
    }

    /**
     * Field contains information about this type length.
     */
    private float  length;

    /**
     * Field contains information about this type size.
     */
    private float  size;

    /**
     * Field contains information about this type format.
     */
    private String format;
}
/**
 * Homework 3
 * + Homework 6.2
 */
package table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class which can contains information about any publications. It store main
 * information about this items and guarantees the assignment to each item an
 * unique identifier.
 * 
 * @author Anton Ishkov
 */
public class Publication
{
    /**
     * The simple version of constructor, which sets only the identification
     * number.will be used as identifier of this element.
     */
    public Publication()
    {
        // Get next auto number for identifier
        this.id = ++Publication.counter;
    }

    /**
     * The version of full constructor, which sets all field from obtained
     * parameters, and generate auto number which will be used as identifier of
     * this element.
     * 
     * @param name
     *            Name of this publication for setting.
     * @param author
     *            Author of this publication for setting.
     * @param lang
     *            Language on which is written this publication for setting.
     * @param descript
     *            Description of this publication for setting.
     */
    public Publication(String name, String author, Language lang,
            String descript)
    {
        // Get next auto number for identifier
        this.id = ++Publication.counter;

        // Set object fields
        this.name = name;
        this.author = author;
        this.lang = lang;
        this.descript = descript;
    }

    /**
     * The version of full constructor, which using for generated extended
     * object from template. It's generate new auto number which will be used as
     * identifier of this element and copy all another fields from obtained
     * publication object.
     * 
     * This constructor may be used as a super constructor during initialization
     * of the sub-class object.
     * 
     * @param template
     *            the object which field using as template for generate this
     *            object.
     */
    protected Publication(Publication template)
    {
        // Get next auto number for identifier
        this.id = ++Publication.counter;

        // Copy fields form template object
        this.name = template.name;
        this.author = template.author;
        this.lang = template.lang;
        this.descript = template.descript;
    }

    /**
     * This method is way to get information about publication from user with
     * console input stream.
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
        if(target.equals("all"))
        {
            // Get them all
            if(! this.getDataName())
            {
                return false;
            }

            if(! this.getDataAuthor())
            {
                return false;
            }

            if(! this.getDataDesc())
            {
                return false;
            }

            if(! this.getDataLang())
            {
                return false;
            }
        }
        else
        {
            // Get publication name
            if(target.equals("name"))
            {
                if(! this.getDataName())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            // Get publication author
            if(target.equals("author"))
            {
                if(! this.getDataAuthor())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            // Get publication description
            if(target.equals("description"))
            {
                if(! this.getDataDesc())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            // Get publication language
            if(target.equals("language"))
            {
                if(! this.getDataLang())
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
     * This method is way to move counter value to upper values. It can be used
     * to restore the counter to a previous state if for some reason he was
     * reduced to zero.
     * 
     * @param value
     *            value to setting as counter.
     * @return Method returning boolean values. It's <strong>True</strong> if
     *         counter was installed, or <strong>False</strong> values if
     *         obtained integer as value parameter was corrupted and it's
     *         setting could cause errors.
     */
    public static boolean setCounter(int value)
    {
        // Checking the received value to match the logic
        if(value < 1 || Publication.counter > 0 && value < Publication.counter)
        {
            return false;
        }

        // Set value and exit
        Publication.counter = value;

        return true;
    }

    /**
     * Getter for getting the publication name(title).
     * 
     * @return The name of this publication.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Setter for setting information about publication name(title).
     * 
     * @param name
     *            The name to set as publication name(title).
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for getting the publication author.
     * 
     * @return The name of the author of this publication.
     */
    public String getAuthor()
    {
        return this.author;
    }

    /**
     * Setter for setting information about publication author.
     * 
     * @param author
     *            The name to set as this publication author.
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * Getter for getting the publication language. In this case available
     * languages list contains in used enum data type, which compile from ISO
     * two chars language codes.
     * 
     * @return The language of this publication as two chars language code.
     */
    public Language getLang()
    {
        return this.lang;
    }

    /**
     * Setter for setting information about publication language. In this case
     * available languages list contains in used enum data type, which compile
     * from ISO two chars language codes.
     * 
     * @param lang
     *            The language to set as this publication language.
     */
    public void setLang(Language lang)
    {
        this.lang = lang;
    }

    /**
     * Getter for getting the publication description.
     * 
     * @return The description of this publication.
     */
    public String getDescript()
    {
        return this.descript;
    }

    /**
     * Setter for setting description of this publication.
     * 
     * @param descript
     *            The information to set as publication description.
     */
    public void setDescript(String descript)
    {
        this.descript = descript;
    }

    /**
     * Getter for getting current value of counter which used while setting new
     * objects ID's.
     * 
     * @return Current value of counter.
     */
    public static int getCounter()
    {
        return Publication.counter;
    }

    /**
     * Getter for getting this object identifier.
     * 
     * @return Object unique identifier obtained while creation.
     */
    public int getId()
    {
        return this.id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    /**
     * A simple method to get the basic parameters of the object. Returns a
     * string containing the name of the publication, its author and the ID.
     */
    @Override
    public String toString()
    {
        return this.author + ": " + this.name + " [" + this.id + "]";
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
        // Declare variables
        final int prime = 31;

        // Calculate
        return prime
                * ( prime
                        * ( prime
                                * ( prime + ( ( this.author == null ) ? 0
                                        : this.author.hashCode() ) ) + ( ( this.descript == null ) ? 0
                                : this.descript.hashCode() ) ) + ( ( this.lang == null ) ? 0
                        : this.lang.hashCode() ) )
                + ( ( this.name == null ) ? 0 : this.name.hashCode() );
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

        // Return false if object is empty
        if(obj == null)
        {
            return false;
        }

        // Compare class's of this and obtained object
        if(this.getClass() != obj.getClass())
        {
            return false;
        }

        // Convert object to current class
        Publication other = (Publication)obj;

        // Compare fields
        if(this.author == null && other.author != null
                || ! this.author.equals(other.author) || this.descript == null
                && other.descript != null
                || ! this.descript.equals(other.descript) || this.lang == null
                && other.lang != null || ! this.lang.equals(other.lang)
                || this.name == null && other.name != null
                || ! this.name.equals(other.name))
        {
            return false;
        }

        return true;
    }

    /**
     * Get from console name of publication.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataName() throws IOException
    {
        // Declare variables
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));
        String inString;

        System.out.print("Please, enter publication name:");
        inString = input.readLine();

        if(inString.isEmpty())
        {
            return false;
        }

        this.name = inString;
        return true;
    }

    /**
     * Get from console name of author of publication.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataAuthor() throws IOException
    {
        // Declare variables
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));
        String inString;

        System.out.print("Please, enter publication author:");
        inString = input.readLine();

        if(inString.isEmpty())
        {
            return false;
        }

        this.author = inString;
        return true;
    }

    /**
     * Get from console description of publication.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataDesc() throws IOException
    {
        // Declare variables
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));
        String inString;

        System.out.print("Please, enter publication description:");
        inString = input.readLine();

        if(inString.isEmpty())
        {
            return false;
        }

        this.descript = inString;
        return true;
    }

    /**
     * Get from console language of publication.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataLang() throws IOException
    {
        // Declare variables
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));
        String inString;

        System.out.print("Please, enter publication language:");
        inString = input.readLine().toUpperCase();

        if(inString.isEmpty() || inString.length() > 2)
        {
            return false;
        }

        try
        {
            this.lang = Language.valueOf(inString);
        }
        catch(IllegalArgumentException ex)
        {
            return false;
        }

        return true;
    }

    /**
     * Field contains count of created objects and used while new objects
     * creations. For generate new auto number constructors get this value and
     * increment it. Past this auto number will be installed as object
     * identifier.
     * 
     * The value of the last identifier stored in the class to avoid the
     * creation is not unique identifier.
     */
    private static int counter = 0;

    /**
     * Field contains unique identifier, obtained while creation this object.
     * It's generated as auto number, based on the counter values(which
     * calculate number of created objects).
     */
    protected int      id;

    /**
     * Field contains information about this publication name(title).
     */
    protected String   name;

    /**
     * Field contains information about this publication author.
     */
    protected String   author;

    /**
     * Field contains information about this publication language. This
     * information is stored as two chars language in special enum data type.
     * This code is same with ISO two chars language codes.
     */
    protected Language lang;

    /**
     * Field contains description of this publication.
     */
    protected String   descript;
}
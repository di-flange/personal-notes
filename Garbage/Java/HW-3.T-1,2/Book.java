/**
 * Homework 3
 */
package publishing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class which can contains information about any book. It extends a Publication
 * class which store main information about this items and guarantees the
 * assignment to each item an unique identifier. And Book class add to it fields
 * which contents price and count of book pages.
 * 
 * @author Anton Ishkov
 */
public class Book extends Publication
{
    /**
     * The simple version of constructor, which sets only the identification
     * number.will be used as identifier of this element.
     */
    public Book()
    {
        // Installation of a unique identifier
        super();
    }

    /**
     * The version of full constructor, which sets all field from obtained
     * parameters, and generate auto number which will be used as identifier of
     * this element.
     * 
     * @param pageCount
     *            Integer for setting as count of pages in this book.
     * @param price
     *            Price of this book for setting.
     * @param name
     *            Name of this book for setting.
     * @param author
     *            Author of this book for setting.
     * @param lang
     *            Language on which is written this book for setting.
     * @param descript
     *            Description of this book for setting.
     */
    public Book(int pageCount, float price, String name, String author,
            Language lang, String descript)
    {
        // Initialize basic fields which inheritances from the super class
        super(name, author, lang, descript);

        // Set current class fields
        this.pageCount = pageCount;
        this.price = price;
    }

    /**
     * The version of full constructor, which sets all field from obtained
     * parameters and one object of Publication class. While this operation
     * generated auto number which will be used as identifier of this element.
     * 
     * @param pageCount
     *            Integer for setting as count of pages in this book.
     * @param price
     *            Price of this book for setting.
     * @param template
     *            Object of Publication which fields will be copied to this
     *            object.
     */
    public Book(int pageCount, float price, Publication template)
    {
        // Copy fields from object
        super(template);

        // Set current class fields
        this.pageCount = pageCount;
        this.price = price;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    /**
     * A simple method to get the basic parameters of the object, returns a
     * string containing the name of the book, its author and the ID, as well as
     * the number of pages and cost.
     */
    @Override
    public String toString()
    {
        // Compile the returning string
        return super.toString() + " pages: " + this.pageCount + " - "
                + this.price;
    }

    /**
     * This method is way to get information about book from user with console
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

            if(! this.getDataPageCount())
            {
                return false;
            }

            if(! this.getDataPrice())
            {
                return false;
            }
        }
        else
        {
            // Get book page count
            if(target.equalsIgnoreCase("pageCount"))
            {
                if(! this.getDataPageCount())
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }

            // Get book price
            if(target.equalsIgnoreCase("price"))
            {
                if(! this.getDataPrice())
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
     * Search string in array of Book objects.
     * 
     * @param target
     *            String which value we search in array.
     * @param arr
     *            Array in which we search string.
     * @param field
     *            Field which we compare with target string.
     * @return Return key of suitable object in array if it found, or -1 if
     *         didn't found.
     */
    public static int search(String target, Book arr[], String field)
    {
        for(int i = 0; i < arr.length; i++ )
        {
            if(field.equals("name"))
            {
                if(target.equals(arr[i].getName()))
                {
                    return i;
                }
            }
            else
            {
                if(target.equals(arr[i].getAuthor()))
                {
                    return i;
                }
            }
        }

        return - 1;
    }

    /**
     * Program call recursive work of method quick sort which used <a
     * href="http://en.wikipedia.org/wiki/Quicksort">"Quick sort" algorithm</a>
     * and with it sort array of Book objects by price.
     * 
     * @param arr
     *            Array of book objects.
     * @param order
     *            Order of sorting (asc/desc).
     */
    public static void sort(Book arr[], String order)
    {
        if(order.equalsIgnoreCase("desc"))
        {
            Book.quickSort(arr, 0, arr.length - 1, true);
        }
        else
        {
            Book.quickSort(arr, 0, arr.length - 1, false);
        }
    }

    /**
     * Getter for getting count of this book pages.
     * 
     * @return The count of pages in this book.
     */
    public int getPageCount()
    {
        return this.pageCount;
    }

    /**
     * Setter for setting information about count of book pages.
     * 
     * @param pageCount
     *            The number to set as count of this book.
     */
    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    /**
     * Getter for getting price of this book.
     * 
     * @return The price of this book.
     */
    public float getPrice()
    {
        return this.price;
    }

    /**
     * Setter for setting information about price of this book.
     * 
     * @param price
     *            The float to set as this book price.
     */
    public void setPrice(int price)
    {
        this.price = price;
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

        return prime * ( prime * super.hashCode() + pageCount )
                + Float.floatToIntBits(price);
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
        Book other = (Book)obj;

        // Compare fields
        if(this.pageCount != other.pageCount
                || Float.floatToIntBits(this.price) != Float
                        .floatToIntBits(other.price))
        {
            return false;
        }

        return true;
    }

    /**
     * Get from console page count of this book.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataPageCount() throws IOException
    {
        // Declare variables
        BufferedReader input = new BufferedReader(new InputStreamReader(
                System.in));
        String inString;
        int inInt;

        System.out.print("Please, enter book count of pages:");
        inString = input.readLine();

        if(inString.isEmpty())
        {
            return false;
        }

        try
        {
            inInt = Integer.parseInt(inString);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        
        this.pageCount = inInt;
        return true;
    }

    /**
     * Get from console price of this book.
     * 
     * @return True if entered valid value, or false if value corrupted.
     * @throws IOException
     */
    private boolean getDataPrice() throws IOException
    {
        // Declare variables
        BufferedReader input = new BufferedReader(new InputStreamReader(
                System.in));
        String inString;
        float inFloat;

        System.out.print("Please, enter book price:");
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
        
        this.price = inFloat;
        return true;
    }

    /**
     * Implementation of quick sort algorithm, which running recursively and
     * sort array of Book objects.
     * 
     * @param arr
     *            Array of Book objects for sorting.
     * @param limLeft
     *            Left limit of current stage sorting.
     * @param limRight
     *            Right limit of current stage sorting.
     * @param desc
     *            Order of sorting is really reversely?
     */
    private static void quickSort(Book arr[], int limLeft, int limRight,
            boolean desc)
    {
        // Control limits
        if(limLeft >= limRight)
        {
            return;
        }

        // Declare variables
        // ---
        // It can be random but we select middle item.
        int midKey = ( limLeft + limRight ) / 2;
        // Select value of it.
        float medValue = arr[midKey].getPrice();
        // Set sub limits in start position (we will move it)
        int limLeftSub = limLeft;
        int limRightSub = limRight;
        // ---

        // Search replacements
        while(limLeftSub < limRightSub)
        {
            // Here we search limits out of which values compare with condition
            if(desc)
            {
                while(arr[limLeftSub].getPrice() > medValue)
                {
                    limLeftSub++ ;
                }
                while(arr[limRightSub].getPrice() < medValue)
                {
                    limRightSub-- ;
                }
            }
            else
            {
                while(arr[limLeftSub].getPrice() < medValue)
                {
                    limLeftSub++ ;
                }
                while(arr[limRightSub].getPrice() > medValue)
                {
                    limRightSub-- ;
                }
            }

            // Make replacement
            if(limLeftSub <= limRightSub)
            {
                // Declare buffer
                Book tmp;

                tmp = arr[limLeftSub];
                arr[limLeftSub] = arr[limRightSub];
                arr[limRightSub] = tmp;

                limLeftSub++ ;
                limRightSub-- ;
            }
        }

        // Research sub arrays
        if(limLeftSub < limRight)
        {
            Book.quickSort(arr, limLeftSub, limRight, desc);
        }
        if(limRightSub > limLeft)
        {
            Book.quickSort(arr, limLeft, limRightSub, desc);
        }
    }

    /**
     * Field contains information about count of this book pages.
     */
    private int   pageCount;

    /**
     * Field contains information about this book price.
     */
    private float price;
}
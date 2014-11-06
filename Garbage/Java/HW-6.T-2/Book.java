/**
 * Homework 6.2
 */
package table;

/**
 * Class which contains information about book.
 * 
 * @author Anton Ishkov
 * 
 */
public class Book extends Publication
{
    /**
     * This is simple constructor, which provide ability to create empty book.
     */
    public Book()
    {
        // Get id number
        super();
    }

    /**
     * Simple constructor, which set information about book.
     * 
     * @param name
     *            Book title.
     * @param author
     *            Book author.
     * @param lang
     *            Book language.
     * @param desc
     *            Book description.
     * @param code
     *            Book code.
     * @param category
     *            Book category.
     * @param price
     *            Book price.
     * @param count
     *            Books count.
     */
    public Book(String name, String author, Language lang, String desc,
            String code, String category, float price, int count)
    {
        // Set information in to the super class
        super(name, author, lang, desc);

        // Set current class information
        this.code = code;
        this.category = category;
        this.price = price;
        this.count = count;
    }

    /**
     * Getter for getting current book code.
     * 
     * @return Book code.
     */
    public String getCode()
    {
        return this.code;
    }

    /**
     * Setter for setting current book code.
     * 
     * @param code
     *            Code to setting.
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * Getter for getting current book code.
     * 
     * @return Book category.
     */
    public String getCategory()
    {
        return this.category;
    }

    /**
     * Setter for setting current book category.
     * 
     * @param category
     *            Category to setting.
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Getter for getting current book price.
     * 
     * @return Book price.
     */
    public float getPrice()
    {
        return this.price;
    }

    /**
     * Setter for setting current book price.
     * 
     * @param price
     *            Price to set.
     */
    public void setPrice(float price)
    {
        this.price = price;
    }

    /**
     * Getter for getting current books count.
     * 
     * @return Books count.
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     * Setter for setting current books count.
     * 
     * @param price
     *            Count to set.
     */
    public void setCount(int count)
    {
        this.count = count;
    }

    /**
     * Code of current book.
     */
    private String code     = "";

    /**
     * Category of current book.
     */
    private String category = "";

    /**
     * Price of current book.
     */
    private float  price    = 0;

    /**
     * Count of current books.
     */
    private int    count    = 0;
}
/**
 * Homework 6.1
 */
package links;

/**
 * Class which contains information about link and category.
 * 
 * @author Anton Ishkov
 * 
 */
public class Link
{
    /**
     * This is empty constructor, which provide ability to create empty link.
     */
    public Link()
    {
        // <^_^> - ZZZzzzzzZZZZZzzzzz boring constructor!
    }

    /**
     * Simple constructor, which set only information about link URL.
     * 
     * @param link
     *            Link URL.
     */
    public Link(String link)
    {
        // Set information.
        this.link = link;
    }

    /**
     * Full constructor, which set all information about link.
     * 
     * @param link
     *            Link URL.
     * @param category
     *            Link category.
     */
    public Link(String link, String category)
    {
        // Set information.
        this.category = category;
        this.link = link;
    }

    /**
     * Getter for getting category of current link.
     * 
     * @return Current category.
     */
    public String getCategory()
    {
        return this.category;
    }

    /**
     * Setter of new category for current link.
     * 
     * @param category
     *            New category.
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Getter for getting URL of current link.
     * 
     * @return Current URL.
     */
    public String getLink()
    {
        return this.link;
    }

    /**
     * Setter of new URL for current link.
     * 
     * @param link
     *            New URL.
     */
    public void setLink(String link)
    {
        this.link = link;
    }

    /**
     * Way to print link URL. 
     */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return this.link;
    }

    /**
     * Category of this link.
     */
    private String category = "Other";

    /**
     * URL of current link.
     */
    private String link     = "";
}
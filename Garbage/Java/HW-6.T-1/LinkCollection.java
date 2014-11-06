/**
 * Homework6.1
 */
package links;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Collection of links.
 * 
 * @author Anton Ishkov
 */
public class LinkCollection
{
    /**
     * Simple constructor which create collection from source(in this case
     * generate in method).
     */
    public LinkCollection()
    {
        // Set collection items.
        this.setLinks();
    }

    /**
     * Version of links getter which get all links.
     * 
     * @return Links array.
     */
    public String[] getLinks()
    {
        // Declare variables
        HashSet<String> links = new HashSet<String>();

        // Search all category's
        for(int i = 0; i < this.linkList.size(); i++ )
        {
            links.add(this.linkList.get(i).getLink());
        }

        // Return result
        return (String[])links.toArray(new String[0]);
    }

    /**
     * Version of links getter which get links from a category.
     * 
     * @param category
     *            Links category.
     * @return Links array.
     */
    public String[] getLinks(String category)
    {
        // Declare variables
        HashSet<String> links = new HashSet<String>();

        // Search all category's
        for(int i = 0; i < this.linkList.size(); i++ )
        {
            if(this.linkList.get(i).getCategory() == category)
            {
                links.add(this.linkList.get(i).getLink());
            }
        }

        // Return result
        return (String[])links.toArray(new String[0]);
    }

    /**
     * Version of links getter which get n random links.
     * 
     * @param n
     *            Count of needed links.
     * @return Links array.
     */
    public String[] getRandomLinks(int n)
    {
        // Error exit
        if(n >= this.linkList.size())
        {
            return null;
        }

        // Declare variables
        HashSet<String> links = new HashSet<String>();
        Random ran = new Random(System.currentTimeMillis());

        // Search all category's
        while(links.size() < n)
        {
            links.add(this.linkList.get(ran.nextInt(this.linkList.size()))
                    .getLink());
        }

        // Return result
        return (String[])links.toArray(new String[0]);
    }

    /**
     * Get all links category's.
     */
    public String[] getCategorys()
    {
        // Declare variables
        HashSet<String> categorys = new HashSet<String>();

        // Search all category's
        for(int i = 0; i < this.linkList.size(); i++ )
        {
            categorys.add(this.linkList.get(i).getCategory());
        }

        // Return result
        return (String[])categorys.toArray(new String[0]);
    }

    /**
     * Add new link to the collection.
     * 
     * @param link
     *            Link URL.
     * @param category
     *            Link category.
     */
    public void addLink(String link, String category)
    {
        // Add link to the link list
        this.linkList.add(new Link(link, category));

        // TODO: Here if collection have outside list source, we need to run
        // method which add new link to this source
    }

    /**
     * Remove a link.
     * 
     * @param link
     *            Link to removing.
     */
    public void removeLink(String link)
    {
        // Search element and remove it
        for(int i = 0; i < this.linkList.size(); i++ )
        {
            if(this.linkList.get(i).getLink() == link)
            {
                this.linkList.remove(i);
            }
        }
    }

    /**
     * Set collection items.
     */
    private void setLinks()
    {
        // Here can be any link loader from DB or XML
        this.linkList.add(new Link("http://habrahabr.ru", "Interesting"));
        this.linkList.add(new Link("http://radio-t.com", "Interesting"));
        this.linkList.add(new Link("http://it-thoughts.ru", "Interesting"));
        this.linkList.add(new Link("http://revision3.com", "Interesting"));
        this.linkList.add(new Link("http://academicearth.org", "Interesting"));
        this.linkList.add(new Link("http://stackoverflow.com", "Interesting"));
        this.linkList.add(new Link("http://ibm.com/developerworks",
                "Interesting"));

        this.linkList.add(new Link("http://java.sun.com", "Java"));
        this.linkList.add(new Link("http://taop.rpod.ru", "Java"));
        this.linkList.add(new Link("http://javable.com", "Java"));
        this.linkList.add(new Link("http://java2s.com", "Java"));

        this.linkList.add(new Link("http://php.net", "PHP"));
        this.linkList.add(new Link("http://php.su", "PHP"));
        this.linkList.add(new Link("http://phpclub.ru", "PHP"));

        this.linkList.add(new Link("http://lisp.net", "Lisp"));
        this.linkList.add(new Link("http://lisp.org", "Lisp"));

        this.linkList.add(new Link("http://linux.org.ru", "Linux"));
        this.linkList.add(new Link("http://gentoo.org", "Linux"));
        this.linkList.add(new Link("http://gentoo.ru", "Linux"));
        this.linkList.add(new Link("http://ubuntu.com", "Linux"));
    }

    /**
     * List of links.
     */
    private ArrayList<Link> linkList = new ArrayList<Link>();
}
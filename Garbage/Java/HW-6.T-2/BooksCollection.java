/**
 * Homework 6.2
 */
package table;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Collection of books.
 * 
 * @author Anton Ishkov
 */
public class BooksCollection
{
    /**
     * Simple constructor which create collection from source(in this case
     * generate in method).
     */
    public BooksCollection()
    {
        // Set collection items.
        this.setBooks();
    }

    /**
     * Get size of collection.
     * 
     * @return Size of collection.
     */
    public int size()
    {
        return this.booksList.size();
    }

    /**
     * Version of book data getter which get data of all books.
     * 
     * @return Books data array.
     */
    public String[][] getBooks()
    {
        // Declare variable
        String[][] buffer = new String[this.booksList.size()][6];

        // Compile array of data
        for(int i = 0; i < this.booksList.size(); i++ )
        {
            buffer[i][0] = this.booksList.get(i).getCode();
            buffer[i][1] = this.booksList.get(i).getCategory();
            buffer[i][2] = this.booksList.get(i).getName();
            buffer[i][3] = this.booksList.get(i).getAuthor();
            buffer[i][4] = String.valueOf(this.booksList.get(i).getPrice());
            buffer[i][5] = String.valueOf(this.booksList.get(i).getCount());
        }

        // Return result
        return buffer;
    }

    /**
     * Version of book data getter which get data of books from category.
     * 
     * @param category
     *            Category of books.
     * @return Books data array.
     */
    public String[][] getBooks(String category)
    {
        // Declare variables
        int count = 0;
        int place = 0;

        // Calculate count of books
        for(int i = 0; i < this.booksList.size(); i++ )
        {
            if(this.booksList.get(i).getCategory().equalsIgnoreCase(category))
            {
                count++ ;
            }
        }

        // Create buffer
        String[][] buffer = new String[count][6];

        // Compile array of data
        for(int i = 0; i < this.booksList.size(); i++ )
        {
            if(this.booksList.get(i).getCategory().equalsIgnoreCase(category))
            {
                buffer[place][0] = this.booksList.get(i).getCode();
                buffer[place][1] = this.booksList.get(i).getCategory();
                buffer[place][2] = this.booksList.get(i).getName();
                buffer[place][3] = this.booksList.get(i).getAuthor();
                buffer[place][4] =
                        String.valueOf(this.booksList.get(i).getPrice());
                buffer[place][5] =
                        String.valueOf(this.booksList.get(i).getCount());

                if(count <= place++ )
                {
                    break;
                }
            }
        }

        // Return result
        return buffer;
    }

    /**
     * Getter for book data
     * 
     * @param index
     *            Index of book.
     * @return Book.
     */
    public Book getBook(int index)
    {
        return this.booksList.get(index);
    }

    /**
     * Version of book names getter which get name of all books.
     * 
     * @return Books name array.
     */
    public String[] getBookNames()
    {
        // Return result
        return (String[])this.booksList.toArray(new String[0]);
    }

    /**
     * Version of book name getter which get names of books from a category.
     * 
     * @param category
     *            Books category.
     * @return Books name array.
     */
    public String[] getBookNames(String category)
    {
        // Declare variables
        ArrayList<String> books = new ArrayList<String>();

        // Search all category's
        for(int i = 0; i < this.booksList.size(); i++ )
        {
            if(this.booksList.get(i).getCategory().equalsIgnoreCase(category))
            {
                books.add(this.booksList.get(i).getName());
            }
        }

        // Return result
        return (String[])books.toArray(new String[0]);
    }

    /**
     * Get all books category's.
     */
    public String[] getCategorys()
    {
        // Declare variables
        HashSet<String> categorys = new HashSet<String>();

        // Search all category's
        for(int i = 0; i < this.booksList.size(); i++ )
        {
            categorys.add(this.booksList.get(i).getCategory());
        }

        // Return result
        return (String[])categorys.toArray(new String[0]);
    }

    /**
     * Add new book to the collection.
     * 
     * @param name
     *            Book title.
     * @param author
     *            Book author.
     * @param code
     *            Book code.
     * @param category
     *            Book category.
     * @param price
     *            Book price.
     * @param count
     *            Books count.
     */
    public void addBook(String name, String author, String code,
            String category, float price, int count)
    {
        // Add link to the link list
        this.booksList.add(new Book(name, author, Language.EN, "", code,
                category, price, count));

        // Change sources
        this.rewriteSource();
    }

    /**
     * Remove a book by code.
     * 
     * @param code
     *            Code of book to removing.
     */
    public void removeBook(String code)
    {
        // Search element and remove it
        for(int i = 0; i < this.booksList.size(); i++ )
        {
            if(this.booksList.get(i).getCode().equalsIgnoreCase(code))
            {
                // Remove item
                this.booksList.remove(i);

                // Change sources
                this.rewriteSource();

                // Break cycle
                return;
            }
        }
    }

    /**
     * Set collection items.
     */
    private void setBooks()
    {
        // Declare variable
        String line;
        BufferedReader readBuffer;
        StringTokenizer cell;
        String[] dataBuffer = new String[6];

        try
        {
            // Initialize file reading stream
            readBuffer =
                    new BufferedReader(new InputStreamReader(
                            new FileInputStream("Books.txt")));

            // Skip headers
            readBuffer.readLine();

            // Read data
            while(( line = readBuffer.readLine() ) != null)
            {
                cell = new StringTokenizer(line, ",");

                for(int i = 0; cell.hasMoreTokens() && i < 6; i++ )
                {
                    dataBuffer[i] = cell.nextToken();
                }

                this.booksList.add(new Book(dataBuffer[2], dataBuffer[3],
                        Language.EN, "", dataBuffer[0], dataBuffer[1], Float
                                .valueOf(dataBuffer[4]), Integer
                                .valueOf(dataBuffer[5])));
            }

            readBuffer.close();
        }
        catch(Exception e)
        {
            System.exit(0);
        }
    }

    /**
     * Change modify to the source.
     */
    private void rewriteSource()
    {
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter("Books.txt"));

            out.println("ID,Type,Title,Author,Price,Qty");

            // Write data
            for(int i = 0; i < this.booksList.size(); i++ )
            {
                out.println(this.booksList.get(i).getCode() + ","
                        + this.booksList.get(i).getCategory() + ","
                        + this.booksList.get(i).getName() + ","
                        + this.booksList.get(i).getAuthor() + ","
                        + String.valueOf(this.booksList.get(i).getPrice())
                        + ","
                        + String.valueOf(this.booksList.get(i).getCount()));
            }

            out.close();
        }
        catch(IOException e)
        {
            System.exit(0);
        }
    }

    /**
     * List of books.
     */
    private ArrayList<Book> booksList = new ArrayList<Book>();
}
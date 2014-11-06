/**
 * Homework 4.2
 * 
 * Anton Iskov
 * RDIR-42
 * 
 * Реализуйте поиск подстроки в строке (15 баллов) поиск всех вхождений
 * подстроки в строке, всех вхождений заданной подстроки в списке строк
 * (объектов классов реализующих интерфейс List), всех вхождений искомой строки
 * в объект реализующий интерфейс Map.
 */
package find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Anton Ishkov
 */
public class Finder
{
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        // Tests
        // Finder.testInString();
        // Finder.testInList();
        Finder.testInMap();
    }

    /**
     * Test sub sting searching in map.
     * 
     * @throws IOException
     */
    private static void testInMap() throws IOException
    {
        // Declare variables
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));

        TreeMap<String, String> mainString = new TreeMap<String, String>();
        String subString;
        String tmp;

        int match = - 1;
        final int COUNT = 3;

        // Get main strings
        for(int i = 0; i < COUNT; i++ )
        {
            System.out.print("Please, enter string name and value:");
            mainString.put(input.readLine(), input.readLine());
        }

        // Get main string
        System.out.print("Please, enter sub string:");
        subString = input.readLine();

        // Get matches
        Iterator<String> i = mainString.keySet().iterator();
        while(i.hasNext())
        {
            tmp = i.next();

            while(- 1 != ( match =
                    mainString.get(tmp).indexOf(subString, match + 1) ))
            {
                System.out.println("FOUND: The substring \"" + subString
                        + "\" is founded from the " + ( match + 1 )
                        + "th character of the \"" + tmp + "\" line.");
            }
            match = - 1;
        }
    }

    /**
     * Test sub sting searching in list.
     * 
     * @throws IOException
     */
    private static void testInList() throws IOException
    {
        // Declare variables
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));
        List<String> mainString = new ArrayList<String>();
        String subString;
        int match = - 1;
        final int COUNT = 3;

        // Get main strings
        for(int i = 0; i < COUNT; i++ )
        {
            System.out.print("Please, enter string (" + ( i + 1 ) + "/" + COUNT
                    + "):");
            mainString.add(input.readLine());
        }

        // Get main string
        System.out.print("Please, enter sub string:");
        subString = input.readLine();

        // Get matches
        for(int i = 0; i < COUNT; i++ )
        {
            while(- 1 != ( match =
                    mainString.get(i).indexOf(subString, match + 1) ))
            {
                System.out.println("FOUND: The substring \"" + subString
                        + "\" is founded from the " + ( match + 1 )
                        + "th character of the " + ( i + 1 ) + " line.");
            }

            match = - 1;
        }
    }

    /**
     * Test sub sting searching in string.
     * 
     * @throws IOException
     */
    private static void testInString() throws IOException
    {
        // Declare variables
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));
        String mainString;
        String subString;
        int match = - 1;

        // Get main string
        System.out.print("Please, enter main string:");
        mainString = input.readLine();

        // Get main string
        System.out.print("Please, enter sub string:");
        subString = input.readLine();

        // Get matches
        while(- 1 != ( match = mainString.indexOf(subString, match + 1) ))
        {
            System.out.println("FOUND: The substring \"" + subString
                    + "\" is founded from the " + ( match + 1 )
                    + "th character of the main line.");
        }
    }
}
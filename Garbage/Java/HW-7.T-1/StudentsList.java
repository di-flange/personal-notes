/**
 * Homework 7
 */
package students;

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
public class StudentsList
{
    /**
     * Simple constructor which create collection from source(in this case
     * generate in method).
     */
    public StudentsList()
    {
        // Set collection items.
        this.setStudents();
    }

    /**
     * Get size of collection.
     * 
     * @return Size of collection.
     */
    public int size()
    {
        return this.studentsList.size();
    }

    /**
     * Version of students data getter which get data of all students.
     * 
     * @return Students data array.
     */
    public String[][] getStudents()
    {
        // Declare variable
        String[][] buffer = new String[this.studentsList.size()][6];

        // Compile array of data
        for(int i = 0; i < this.studentsList.size(); i++ )
        {
            buffer[i][0] = String.valueOf(this.studentsList.get(i).getCode());
            buffer[i][1] = this.studentsList.get(i).getGroup();
            buffer[i][2] =
                    String.valueOf(this.studentsList.get(i).getSemester());
            buffer[i][3] = this.studentsList.get(i).getName();
            buffer[i][4] = this.studentsList.get(i).getSurname();
        }

        // Return result
        return buffer;
    }

    /**
     * Version of students data getter which get data of students from group and
     * semester.
     * 
     * @param group
     *            Group of students.
     * @param semester
     *            Semester of students.
     * @return Students data array.
     */
    public String[][] getStudents(String group)
    {
        // Declare variables
        int count = 0;
        int place = 0;

        // Calculate count of books
        for(int i = 0; i < this.studentsList.size(); i++ )
        {
            if(group.equalsIgnoreCase(this.studentsList.get(i).getGroup() + "0"
                    + this.studentsList.get(i).getSemester()))
            {
                count++ ;
            }
        }

        // Create buffer
        String[][] buffer = new String[count][6];

        // Compile array of data
        for(int i = 0; i < this.studentsList.size(); i++ )
        {
            if(group.equalsIgnoreCase(this.studentsList.get(i).getGroup() + "0"
                    + this.studentsList.get(i).getSemester()))
            {
                buffer[place][0] =
                        String.valueOf(this.studentsList.get(i).getCode());
                buffer[place][1] = this.studentsList.get(i).getGroup();
                buffer[place][2] =
                        String.valueOf(this.studentsList.get(i).getSemester());
                buffer[place][3] = this.studentsList.get(i).getName();
                buffer[place][4] = this.studentsList.get(i).getSurname();

                if(count < ++place)
                {
                    break;
                }
            }
        }

        // Return result
        return buffer;
    }

    /**
     * Getter for student data
     * 
     * @param index
     *            Index of book.
     * @return Book.
     */
    public Student getStudent(int index)
    {
        return this.studentsList.get(index);
    }

    /**
     * Version of students name getter which get names of students from a group.
     * 
     * @param group
     *            Students group.
     * @return Students name array.
     */
    public String[] getStudentsNames(String group)
    {
        // Declare variables
        ArrayList<String> students = new ArrayList<String>();

        // Search all groups
        for(int i = 0; i < this.studentsList.size(); i++ )
        {
            if(this.studentsList.get(i).getGroup().equalsIgnoreCase(group))
            {
                students.add(this.studentsList.get(i).getName());
            }
        }

        // Return result
        return (String[])students.toArray(new String[0]);
    }

    /**
     * Get all students groups.
     * 
     * @return Array of groups.
     */
    public String[] getGroups()
    {
        // Declare variables
        HashSet<String> groups = new HashSet<String>();

        // Search all groups
        for(int i = 0; i < this.studentsList.size(); i++ )
        {
            groups.add(this.studentsList.get(i).getGroup());
        }

        // Return result
        return (String[])groups.toArray(new String[0]);
    }

    /**
     * Get all students semesters.
     * 
     * @return Array of semesters.
     */
    public Integer[] getSemesters()
    {
        // Declare variables
        HashSet<Integer> semesters = new HashSet<Integer>();

        // Search all semesters
        for(int i = 0; i < this.studentsList.size(); i++ )
        {
            semesters.add(this.studentsList.get(i).getSemester());
        }

        // Return result
        return (Integer[])semesters.toArray(new Integer[0]);
    }

    /**
     * Get all students semesters.
     * 
     * @param Group
     *            of semesters.
     * 
     */
    public Integer[] getSemesters(String group)
    {
        // Declare variables
        HashSet<Integer> semesters = new HashSet<Integer>();

        // Search all semesters
        for(int i = 0; i < this.studentsList.size(); i++ )
        {
            if(this.studentsList.get(i).getGroup().equalsIgnoreCase(group))
            {
                semesters.add(this.studentsList.get(i).getSemester());
            }
        }

        // Return result
        return (Integer[])semesters.toArray(new Integer[0]);
    }

    /**
     * Add new students to the collection.
     * 
     * @param surname
     *            Student title.
     * @param name
     *            Student author.
     * @param group
     *            Student group.
     * @param code
     *            Student code.
     * @param semester
     *            Student semester.
     */
    public void addStudents(int code, String group, int semester, String name,
            String surname)
    {
        // Add link to the link list
        this.studentsList
                .add(new Student(code, group, semester, name, surname));

        // Change sources
        this.rewriteSource();
    }

    /**
     * Remove student by code.
     * 
     * @param code
     *            Code of student to removing.
     */
    public void removeStudent(int code)
    {
        // Search element and remove it
        for(int i = 0; i < this.studentsList.size(); i++ )
        {
            if(this.studentsList.get(i).getCode() == code)
            {
                // Remove item
                this.studentsList.remove(i);

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
    private void setStudents()
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
                            new FileInputStream("Students.txt")));

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

                this.studentsList.add(new Student(Integer
                        .valueOf(dataBuffer[0]), dataBuffer[1], Integer
                        .valueOf(dataBuffer[2]), dataBuffer[3], dataBuffer[4]));
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
            PrintWriter out = new PrintWriter(new FileWriter("Students.txt"));

            out.println("Code,Group,Semester,Surname,Name");

            // Write data
            for(int i = 0; i < this.studentsList.size(); i++ )
            {
                out.println(this.studentsList.get(i).getCode() + ","
                        + this.studentsList.get(i).getGroup() + ","
                        + this.studentsList.get(i).getSemester() + ","
                        + this.studentsList.get(i).getSurname() + ","
                        + this.studentsList.get(i).getName() + ",");
            }

            out.close();
        }
        catch(IOException e)
        {
            System.exit(0);
        }
    }

    /**
     * List of students.
     */
    private ArrayList<Student> studentsList = new ArrayList<Student>();
}
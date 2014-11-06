/**
 * 
 */

package students;

/**
 * @author Anton Ishkov
 * 
 */
public class Student
{
    /**
     * Empty constructor.
     */
    public Student()
    {
        // ^^
    }

    /**
     * Full constructor which set field information.
     * 
     * @param code
     *            Student code.
     * @param group
     *            Student group.
     * @param course
     *            Student semester.
     * @param name
     *            Student name.
     * @param surname
     *            Student surname.
     */
    public Student(int code, String group, int semester, String name,
            String surname)
    {
        // Set field information
        this.code = code;
        this.group = group;
        this.semester = semester;
        this.name = name;
        this.surname = surname;
    }

    /**
     * Getter for getting current student code.
     * 
     * @return Student code.
     */
    public int getCode()
    {
        return this.code;
    }

    /**
     * Getter for getting current student group.
     * 
     * @return Group code.
     */
    public String getGroup()
    {
        return this.group;
    }

    /**
     * Getter for getting current student surname.
     * 
     * @return Student surname.
     */
    public int getSemester()
    {
        return this.semester;
    }

    /**
     * Getter for getting current student name.
     * 
     * @return Student name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Getter for getting current student surname.
     * 
     * @return Student surname.
     */
    public String getSurname()
    {
        return this.surname;
    }

    /**
     * Setter for setting current student code.
     * 
     * @param code
     *            Student code.
     */
    public void setCode(int code)
    {
        this.code = code;
    }

    /**
     * Setter for setting current student group.
     * 
     * @param group
     *            Student group.
     */
    public void setGroup(String group)
    {
        this.group = group;
    }

    /**
     * Setter for setting current student semester.
     * 
     * @param semester
     *            Student semester.
     */
    public void setSemester(int semester)
    {
        this.semester = semester;
    }
    
    /**
     * Setter for setting current student name.
     * 
     * @param code
     *            Student name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter for setting current student surname.
     * 
     * @param Student surname.
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * Student personal code.
     */
    private int    code;
    /**
     * Student group.
     */
    private String group;
    /**
     * Student course.
     */
    private int    semester;
    /**
     * Student name.
     */
    private String name;
    /**
     * Student surname.
     */
    private String surname;
}
/**
 * Homework 2
 * 
 * Имеются данные о людях (имя, фамилия, дата рождения). Необходимо распечатать:
 * у кого именины и день рождение в ближайшие 10 дней. Создайте необходимые
 * классы, методы (15 баллов)
 */
package test2;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Anton Ishkov
 */
class Main {
    /**
     * Initialize tow list ad compare it, when founded birthday, or name days in
     * next 10 days it print it in report
     * 
     * @param argv
     *            not used
     */
    public static void main(String argv[]) {
	// Fill data arrays
	Main.personListInit();
	Main.namedayListInit();

	// Print people who have birthday in to the next 10 days
	System.out.println("Next birthdays:");

	for (Person human : Main.personList) {
	    if (human.days2birthday() <= 10 && human.days2birthday() >= 0)
	    {
		    System.out.println(human);
	    }
	}
	System.out.println();

	/**
	 * NOTE: Name days list contains information only about 10 days. Thus to
	 * see the work of the algorithm need to move this to a certain number
	 * of days.
	 */

	// Print people who have name days in to the next 10 days
	System.out.println("Next name days:");

	GregorianCalendar date = new GregorianCalendar();

    // So here must be:
    // for (int i = date.get(Calendar.DAY_OF_YEAR); i < date.get(Calendar.DAY_OF_YEAR) + 10; i++)
	for (int i = 0; i < 10; i++) 
	{
	    for (String name : Main.namedayList[i])
	    {
		    for (Person human : Main.personList) {
		        if (human.getName() == name)
		        {
			        System.out
				        .println((date.get(Calendar.DAY_OF_MONTH) + i)
					        + "." + date.get(Calendar.DAY_OF_MONTH)
					        + " " + human.getName() + " "
					        + human.getSurname());
		        }
		    }
	    }
	    System.out.println();
	}
    }

    /**
     * List of peoples
     */
    private static Person personList[];

    /**
     * List of name days
     */
    private static String namedayList[][];

    /**
     * Generate list of people
     */
    private static void personListInit() {
	Main.personList = new Person[] {
		new Person("Григорий", "Петров", 1, 2, 1970),
		new Person("Василий", "Петров", 3, 2, 1970),
		new Person("Иван", "Петров", 6, 2, 1970),
		new Person("Ипполит", "Петров", 9, 2, 1970),
		new Person("Максим", "Петров", 12, 2, 1970),
		new Person("Пётр", "Петров", 15, 2, 1970),
		new Person("Фёдор", "Петров", 18, 2, 1970),
		new Person("Виктор", "Петров", 21, 2, 1970),
		new Person("Илья", "Петров", 24, 2, 1970),
		new Person("Никита", "Петров", 27, 2, 1970),

		new Person("Анна", "Петрова", 1, 3, 1970),
		new Person("Роман", "Петров", 3, 3, 1970),
		new Person("Николай", "Петров", 6, 3, 1970),
		new Person("Кирилл", "Петров", 9, 3, 1970),
		new Person("Антон", "Петров", 12, 3, 1970),
		new Person("Мария", "Петров", 15, 3, 1970),
		new Person("Изабелла", "Петров", 18, 3, 1970),
		new Person("Аркадий", "Петров", 21, 3, 1970),
		new Person("Галина", "Петров", 24, 3, 1970),
		new Person("Семён", "Петров", 27, 3, 1970),

		new Person("Павел", "Петров", 30, 3, 1970),
		new Person("Лев", "Петров", 1, 4, 1970),
		new Person("Анфиса", "Петрова", 3, 4, 1970),
		new Person("Евгений", "Петров", 6, 4, 1970),
		new Person("Яков", "Петров", 9, 4, 1970),
		new Person("Антонина", "Петрова", 12, 4, 1970),
		new Person("Луиза", "Петрова", 15, 4, 1970),
		new Person("Лаврентий", "Петров", 18, 4, 1970),
		new Person("Марина", "Петров", 21, 4, 1970),
		new Person("Александр", "Петров", 24, 4, 1970) };
    }

    /**
     * Generate list of name days
     */
    private static void namedayListInit() {
	Main.namedayList = new String[][] { { "Григорий", "Луиза", "Фёдор" },
		{ "Инна", "Лаврентий", "Лев" },
		{ "Анастасий", "Евгений", "Иван", "Максим", },
		{ "Георгий", "Иван", "Иосиф", "Пётр", "Тимофей" },
		{ "Геннадий", "Климент", "Максим", "Фёдор" },
		{ "Герасим", "Денис", "Иван", "Ксения", "Павел" },
		{ "Григорий", "Луиза", "Фёдор" },
		{ "Инна", "Лаврентий", "Лев" },
		{ "Анастасий", "Евгений", "Иван", "Максим" },
		{ "Георгий", "Иван", "Иосиф", "Пётр", "Тимофей" } };
    }
}

class Person {
    /**
     * Simple create object with increasing counter.
     */
    public Person() {
	Person.setCount();
    }

    /**
     * Full version of constructor which create object(increase value of
     * counter) and fill object fields from obtained parameter.
     * 
     * @param name
     *            Name of person.
     * @param surname
     *            Surname of person.
     * @param day
     *            Month day of person birthday.
     * @param month
     *            Month number of person birthday.
     * @param year
     *            Year of person birthday.
     */
    public Person(String name, String surname, int day, int month, int year) {
	this.name = name;
	this.surname = surname;
	this.setBirthday(day, month, year);
	Person.setCount();
    }

    /**
     * Get string which contains person name surname and birthday.
     */
    public String toString() {
	return name + " " + surname + " - " + this.getBirthday();
    }

    /**
     * Get person name.
     * 
     * @return Person name.
     */
    public String getName() {
	return this.name;
    }

    /**
     * Set name of person.
     * 
     * @param surname
     *            Person name.
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Get person surname.
     * 
     * @return Person surname.
     */
    public String getSurname() {
	return this.surname;
    }

    /**
     * Set surname of person.
     * 
     * @param surname
     *            Person surname.
     */
    public void setSurname(String surname) {
	this.surname = surname;
    }

    /**
     * Get count of days before birthday.
     * 
     * @return Count of days before birthday.
     */
    public int days2birthday() {
	// Declare variables
	GregorianCalendar date = new GregorianCalendar();

	return this.birthday.get(Calendar.DAY_OF_YEAR)
		- date.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Get count of created objects.
     * 
     * @return Count of created object.
     */
    public static int getCount() {
	return Person.count;
    }

    /**
     * Get person birth day.
     * 
     * @return Person birthday in object.
     */
    public String getBirthday() {
	return this.getBirthday("DATE") + "." + this.getBirthday("MONTH") + "."
		+ this.getBirthday("YEAR");
    }

    /**
     * Get a part of person birthday.
     * 
     * @param field
     *            Requested part.
     * @return Value of requested part.
     */
    public int getBirthday(String field) {
	if (field.equalsIgnoreCase("date")) {
	    return this.birthday.get(Calendar.DATE);
	} else if (field.equalsIgnoreCase("month")) {
	    return this.birthday.get(Calendar.MONTH) + 1;
	} else if (field.equalsIgnoreCase("year")) {
	    return this.birthday.get(Calendar.YEAR);
	}
	return 0;
    }

    /**
     * Set person birth day.
     * 
     * @param day
     *            Month day of birthday.
     * @param month
     *            Month number of birthday.
     * @param year
     *            Year of birthday.
     */
    public void setBirthday(int day, int month, int year) {
	this.birthday = new GregorianCalendar(year, month - 1, day);
    }

    /**
     * Increment counter.
     */
    private static void setCount() {
	Person.count++;
    }

    /**
     * Person name.
     */
    private String name;

    /**
     * Person surname.
     */
    private String surname;

    /**
     * Count of created objects.
     */
    private static int count = 0;

    /**
     * Date of person birthday.
     */
    private GregorianCalendar birthday;
}

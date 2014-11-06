/**
 * Homework 2
 * 
 * ������� ������ � ����� (���, �������, ���� ��������). ���������� �����������:
 * � ���� ������� � ���� �������� � ��������� 10 ����. �������� �����������
 * ������, ������ (15 ������)
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
		new Person("��������", "������", 1, 2, 1970),
		new Person("�������", "������", 3, 2, 1970),
		new Person("����", "������", 6, 2, 1970),
		new Person("�������", "������", 9, 2, 1970),
		new Person("������", "������", 12, 2, 1970),
		new Person("ϸ��", "������", 15, 2, 1970),
		new Person("Ը���", "������", 18, 2, 1970),
		new Person("������", "������", 21, 2, 1970),
		new Person("����", "������", 24, 2, 1970),
		new Person("������", "������", 27, 2, 1970),

		new Person("����", "�������", 1, 3, 1970),
		new Person("�����", "������", 3, 3, 1970),
		new Person("�������", "������", 6, 3, 1970),
		new Person("������", "������", 9, 3, 1970),
		new Person("�����", "������", 12, 3, 1970),
		new Person("�����", "������", 15, 3, 1970),
		new Person("��������", "������", 18, 3, 1970),
		new Person("�������", "������", 21, 3, 1970),
		new Person("������", "������", 24, 3, 1970),
		new Person("����", "������", 27, 3, 1970),

		new Person("�����", "������", 30, 3, 1970),
		new Person("���", "������", 1, 4, 1970),
		new Person("������", "�������", 3, 4, 1970),
		new Person("�������", "������", 6, 4, 1970),
		new Person("����", "������", 9, 4, 1970),
		new Person("��������", "�������", 12, 4, 1970),
		new Person("�����", "�������", 15, 4, 1970),
		new Person("���������", "������", 18, 4, 1970),
		new Person("������", "������", 21, 4, 1970),
		new Person("���������", "������", 24, 4, 1970) };
    }

    /**
     * Generate list of name days
     */
    private static void namedayListInit() {
	Main.namedayList = new String[][] { { "��������", "�����", "Ը���" },
		{ "����", "���������", "���" },
		{ "���������", "�������", "����", "������", },
		{ "�������", "����", "�����", "ϸ��", "�������" },
		{ "��������", "�������", "������", "Ը���" },
		{ "�������", "�����", "����", "������", "�����" },
		{ "��������", "�����", "Ը���" },
		{ "����", "���������", "���" },
		{ "���������", "�������", "����", "������" },
		{ "�������", "����", "�����", "ϸ��", "�������" } };
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

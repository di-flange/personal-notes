/**
 * Author:	Anton Ishkov
 * Date:	17.11.2010
 *
 * Task:
 * Work 1:	Program which store  in array data about student's group.
 *			- Name
 *			- EGN (Personal code/Identifier)
 *			- Faculty number 
 *			- Array of marks
 *			alowe operations of:
 *			- Adding new student
 *			- Show all data
 *			- Show filtered data
 *
 *			Да се състави програма, която съхранява в масив следните данни за група студенти:
 *			- име;
 *			- ЕГН;
 *			- факултетен номер (6 цифри);
 *			- оценките по 10 дисциплини
 *			и извършва следните операции, избирани от меню:
 *			- добавя към масив данните на нов студент;
 *			- извежда всички въведени данни;
 *			- изежда имената и броя на слабите оценки на студентките с фак. номер, започващ с 07   
 *			- създава масив с фамилните имена и фак. номера на студентите мъже над 19 г.
 *
 * Implementation notes:
 * - Here created three namespaces:
 *		- Task - this name space contants classes of data which present main object area of application.
 *		- Utils - contains some utilit functions which using while runing of programm but not included to the classes.
 *		- Scripts - this space contains parts of main application script, called from main function
 */

#include <iostream>
#include <vector>
#include <string>

#include <conio.h>

#include "Student.h"
#include "StudentCollection.h"

using namespace task;

namespace scripts
{
	/**
	 * Print information about a student.
	 */
	void testStudent(Student stud);

	/**
	 * Create of student list.
	 */
	void initList(StudentCollection &list);

	/**
	 * Add new student.
	 */
	void addNew(StudentCollection &list);

	/**
	 * Print all list.
	 */ 
	void printAll(StudentCollection &list);

	/**
	 * Print with first filter: weak marks and fac number started with 07.
	 */
	void printWeak(StudentCollection &list);

	/**
	 * Print with first filter: mens and older than 19 years old.
	 */
	void printMens(StudentCollection &list);
}

int main(void)
{
	// Declare variables;
	int sel = 0;
	StudentCollection list;

	scripts::initList(list);

	// Menu implemntation loop;
	while (true)
	{
		// Output menu;
		std::cout	<< "Menu:\n"
					<< "\t [1] Add new student\n"
					<< "\t [2] Print all data\n"
					<< "\t [3] Print data by filter: Small marks & faculty ID start with 07\n"
					<< "\t [4] Print data by filter: Mans older than 19 years old\n"
					<< "\n"
					<< "\t [0] Exit"
					<< std::endl;

        // Input menu data;
		std::cout	<< "Select: ";
		
		while (true)
		{
			std::cin.clear();
			std::cin.sync();

			std::cin	>> sel;

			if (std::cin.good() && sel >= 0  && sel <= 4)
			{
				system("cls");
				break;
			}

			std::cout	<< "Invalid choice, try again:";
		}

		// Menu cases;
		switch (sel)
		{
			case 1:
				scripts::addNew(list);
				break;
			case 2:
				scripts::printAll(list);
				break;
			case 3:
				scripts::printWeak(list);
				break;
			case 4:
				scripts::printMens(list);
				break;
			case 0:
				// End work;
				std::cout	<< std::endl
							<< "\t\t\t    Press any key to exit."
							<< std::endl;

				_getch();
				system("cls");

				return 0;
		}

		// Hold result;
		std::cout	<< std::endl
					<< "\t\t    Press any key for return to the menu."
					<< std::endl;

		_getch();
		system("cls");
	}
	
	return 1;
}

namespace scripts
{
	void initList(StudentCollection &list)
	{
		list.addStudent(Student("Andrus",	"Aasmaa",	"Markus",	"38908181391", 991223));
		list.addStudent(Student("Anton",	"Komonen",	"Sigurn",	"38807172282",  72213));
		list.addStudent(Student("Aivor",	"Krut",		"Vailo",	"39009199400",  92136));
		list.addStudent(Student("Tonis",	"Turkson",	"Mark",		"39310208519",   2238));
		list.addStudent(Student("Liili",	"Vajnenen",	"Sigrid",	"29211017628",  12337));
		list.addStudent(Student("Kristina",	"Loos",		"Kaatri",	"29112026737",  22453));
		list.addStudent(Student("Sofi",		"Vaginen",	"Kilii",	"28701035846",  32531));
		list.addStudent(Student("Tunnel",	"Pader",	"Hein",		"38302044955",  42632));
		list.addStudent(Student("Katrin",	"Saavi",	"Lembi",	"28403053064",  52833));
		list.addStudent(Student("Katlin",	"Joonas",	"Anni",		"29104062173",  62734));
		list.addStudent(Student("Rein",		"Ots",		"Lembitu",	"39405071282",  72935));
		list.addStudent(Student("Aino",		"Vaino",	"Arko",		"39306080391",  70237));
		list.addStudent(Student("Olav",		"Peterson", "Ainar",	"39207099400",  71237));
		list.addStudent(Student("Eivi",		"Piimel",	"Aidi",		"29408108519",  82234));
		list.addStudent(Student("Elerin",	"Vailo",	"Anneke",	"28509117628",  93236));
		list.addStudent(Student("Laila",	"Roots",	"Anni",		"28210126737",  72435));
	}

	void addNew(StudentCollection &list)
	{
		// Declare variables
		std::string fName;
		std::string sName;
		std::string tName;

		std::string id  = "";
		int fId = 0;

		// Get data
		std::cout	<< "Add new student:\n\n";

		std::cout	<< "Name:";
		
		while (true)
		{
			std::cin.clear();
			std::cin.sync();

			std::cin	>> fName
						>> sName
						>> tName;

			if (std::cin.good())
			{
				break;
			}

			std::cout	<< "Invalid choice, try again:";
		}

		std::cout	<< "Personal code:";

		while (true)
		{
			std::cin.clear();
			std::cin.sync();

			std::cin	>> id;

			if (std::cin.good() && id.size() == 11)
			{
				break;
			}

			std::cout	<< "Invalid choice, try again:";
		}

		std::cout	<< "Faculty number:";

		while (true)
		{
			std::cin.clear();
			std::cin.sync();

			std::cin	>> fId;

			if (std::cin.good() && 0 < fId && 1000000 > fId)
			{
				break;
			}

			std::cout	<< "Invalid choice, try again:";
		}


		// Create student
		Student tmpStud(fName, sName, tName, id, fId);

		testStudent(tmpStud);

		// Save or not? 
		char query;

		std::cout	<< "Save student on the list (Y/N):";

		while (true)
		{
			std::cin.clear();
			std::cin.sync();

			std::cin	>> query;

			if (std::cin.good() && (toupper(query) == 'N' || toupper(query) == 'Y'))
			{
				break;
			}

			std::cout	<< "Invalid choice, try again:";
		}

		if (toupper(query) == 'Y')
		{
			list.addStudent(tmpStud);

			std::cout	<< "\n\t\t\t\t  Saved\n\n";
		}

		// Make loop
		std::cout	<< "Add more students to the list (Y/N):";

		while (true)
		{
			std::cin.clear();
			std::cin.sync();

			std::cin	>> query;

			if (std::cin.good() && (toupper(query) == 'N' || toupper(query) == 'Y'))
			{
				break;
			}

			std::cout	<< "Invalid choice, try again:";
		}

		if (toupper(query) == 'Y')
		{
			system("cls");
			addNew(list);
		}
	}

	void printAll(StudentCollection &list)
	{
		std::cout	<< "Print all data:\n\n";

		for (int i = 0; list.size() > i; i++)
		{
			std::cout	<< "Name: "	<< list.getByPos(i).getFName() << " "
									<< list.getByPos(i).getSName() << " "
									<< list.getByPos(i).getTName() << "\t"
						<< "ID: "	<< list.getByPos(i).getId() << "\t"
						<< "FID: "	<< list.getByPos(i).getFId() << "\n"
						<< "Marks: ";
			
			for (int j = 0; 10 > j; j++)
			{
				std::cout	<< list.getByPos(i).getMark(j) << " ";
			}

			std::cout	<< std::endl;
		}
	}

	void printWeak(StudentCollection &list)
	{
		std::cout	<< "Print data by filter: Small marks & faculty ID start with 07:\n\n";

		for (int i = 0; list.size() > i; i++)
		{
			if (list.isCodeStart07(i))
			{
				std::cout	<< "Name: "	<< list.getByPos(i).getFName()  << " "
										<< list.getByPos(i).getSName()  << " "
										<< list.getByPos(i).getTName()  << " - "
										<< list.weakMarks(i)			<< std::endl;
			}
		}
	}

	void printMens(StudentCollection &list)
	{
		std::cout	<< "Print data by filter: Mans older than 19 years old:\n\n";

		for (int i = 0; list.size() > i; i++)
		{
			if (list.isMen(i) && list.isOlder(i, 19))
			{
				std::cout	<< "Name: "	<< list.getByPos(i).getFName() << " "
										<< list.getByPos(i).getSName() << " "
										<< list.getByPos(i).getTName() << "\t"
							<< "ID: "	<< list.getByPos(i).getId()    << "\t"
							<< "FID: "	<< list.getByPos(i).getFId()   << std::endl;
			}
		}
	}

	void testStudent(Student stud)
	{
		std::cout	<< "Student:\n" 
					<< "\t" << stud.getFName()	<< " " << "\t" << stud.getSName() << " " << "\t" << stud.getTName() << "\n"
					<< "\n"
					<< "\t" << (stud.getSex() ? "Men" : "Women") << "\n"
					<< "\t" << stud.getBDDay() << "." << stud.getBDMonth() << "." << stud.getBDYear()
							<< " (" << stud.getAge() << ")" << "\n"
					<< "\n"
					<< "\t" << stud.getId()		<< "\n"
					<< "\t" << stud.getFId()	<< "\n"
					<< "\n"
					<< "\tMarks:";

		for (int i = 0; stud.getMark(i) > -1; i++)
		{
			std::cout	<< " " << stud.getMark(i);
		}

		std::cout << std::endl;
	}
}
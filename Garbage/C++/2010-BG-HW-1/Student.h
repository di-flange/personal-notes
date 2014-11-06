/**
 * Author:	Anton Ishkov
 * Date:	17.11.2010
 *
 * Heading of student class.
 *
 * In theory it's only incapulation of data of one persone to one class with some methods whcih will be
 * store information from ID code.
 *
 * Implementation notes:
 * -	ID in some cases can include count of numbers which we can't save to the int.
 *		This information can be saved like array of numbers or char string, string is more 
 *		flexible way to do it, becouse in some cases in this code can be included numbers.
 *
 *		- 38817072133 - code in Estonia.
 *			3**********	- Sex.
 *			*88********	- Year of birthday (%YY).
 *			***07******	- Mont of birthday.
 *			*****17**** - Day of birthaday.
 *			*******2133 - Postfix code to set ID unique.
 */
#pragma once

#include <string>
#include "utils.h"

#define MARKS_COUNT 10

namespace task
{
	class Student
	{
	public:
		/**
		 * Standart constructor.
		 *
		 * Set default values for all fields.
		 */
		Student(void);

		/**
		 * Standart constructor.
		 *
		 * Set values of fields.
		 */
		Student(std::string fname, std::string sname, std::string tname, std::string id, int fid);

		/**
		 * Accessors for first name.
		 */
		std::string getFName(void);
		void setFName(std::string fName);

		/**
		 * Accessors for second name.
		 */
		std::string getSName(void);
		void setSName(std::string sName);

		/**
		 * Accessors for third name.
		 */
		std::string getTName(void);
		void setTName(std::string tName);

		/**
		 * Accessors for ID.
		 */
		std::string getId(void);
		void setId(std::string id);

		/**
		 * Getters of personal information stored from ID.
		 */
		bool getSex(void);
		int getAge(void);
		int getBDDay(void);
		int getBDMonth(void);
		int getBDYear(void);

		/**
		 * Accessors for faculty ID.
		 */
		int getFId(void);
		void setFId(int fId);

		/**
		 * Accessors for marks.
		 */
		int getMark(int key);
		void setMark(int key, int mark);

	private:
		/**
		 * Clear list of marks.
		 */
		void clearMarks(void);

		/**
		 * Student first name.
		 */
		std::string fName;

		/**
		 * Student second name.
		 */
		std::string sName;

		/**
		 * Student thirsd name.
		 */
		std::string tName;

		/**
		 * Personal code.
		 */
		std::string id;

		/**
		 * Faculty number.
		 */
		int fId;

		/**
		 * Mark list
		 */
		int marks[MARKS_COUNT];
	};
}
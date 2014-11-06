/**
 * Author:	Anton Ishkov
 * Date:	17.11.2010
 *
 * Heading of student collection class.
 * 
 * This class is colection of student class objects.
 *
 * Implementation notes:
 * -
 */
#pragma once

#include <vector>
#include <string>

#include "Student.h"

namespace task
{
	class StudentCollection
	{
	public:
		/**
		 * Create of collection.
		 */
		StudentCollection(void);

		/**
		 * Add element to the collection.
		 */
		void addStudent(Student stud);

		/**
		 * Geter of element.
		 */
		Student getByPos(int pos);

		/**
		 * Test for filters.
		 */
		bool isMen(int pos);
		bool isCodeStart07(int pos);
		bool isOlder(int pos, int age);

		/**
		 * Counter of weak marks.
		 */
		int weakMarks(int pos);

		/**
		 * Size of collection.
		 */
		int size();
	private:
		/**
		 * Array of collection.
		 */
		std::vector<Student> list;
	};
}
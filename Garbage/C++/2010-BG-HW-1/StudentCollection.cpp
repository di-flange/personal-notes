/**
 * Author:	Anton Ishkov
 * Date:	17.11.2010
 *
 * Implementation of student collection class.
 *
 * Implementation notes:
 * - Thinking that's weak marks is the marks lower than 2.
 * - Thinking that's man's have another first number in ID code, and it is 3.
 */

#include <vector>
#include <string>
#include <time.h>

#include "Student.h"
#include "StudentCollection.h"

#define WEAK_MARK 2; 

task::StudentCollection::StudentCollection(void)
{

}

void task::StudentCollection::addStudent(Student stud)
{
	this->list.push_back(stud);
}

task::Student task::StudentCollection::getByPos(int i)
{
	return this->list[i];
}

int task::StudentCollection::size()
{
	return this->list.size();
}

int task::StudentCollection::weakMarks(int pos)
{
	int count = 0;

	for (int i = 0; 10 > i; i++)
	{
		if (3 > this->list[pos].getMark(i))
		{
			count++;
		}
	}

	return count;
}

bool task::StudentCollection::isMen(int pos)
{
	return this->list[pos].getSex();
}

bool task::StudentCollection::isCodeStart07(int pos)
{
	if (7 == this->list[pos].getFId() / 10000)
	{
		return true;
	}

	return false;
}

bool task::StudentCollection::isOlder(int pos, int age)
{
	return this->list[pos].getAge() > age;
}
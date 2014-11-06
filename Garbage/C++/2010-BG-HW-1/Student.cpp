/**
 * Author:	Anton Ishkov
 * Date:	17.11.2010
 *
 * Implementation of student class.
 */
#include "Student.h"
#include "utils.h"

#include <string>
#include <time.h>

#define STD_STR ""
#define MARKS_COUNT 10

task::Student::Student(void)
{
	this->fName = STD_STR;
	this->sName = STD_STR;
	this->tName = STD_STR;

	this->id  = STD_STR;
	this->fId = 0;

	this->clearMarks();
}

task::Student::Student(std::string fname, std::string sname, std::string tname, std::string id, int fId)
{
	this->fName = fname;
	this->sName = sname;
	this->tName = tname;

	this->id  = id;
	this->fId = fId;

	this->clearMarks();
}

std::string task::Student::getFName(void)
{
	return this->fName;
}

void task::Student::setFName(std::string fName)
{
	this->fName = fName;
}

std::string task::Student::getSName(void)
{
	return this->sName;
}

void task::Student::setSName(std::string sName)
{
	this->sName = sName;
}

std::string task::Student::getTName(void)
{
	return this->tName;
}

void task::Student::setTName(std::string tName)
{
	this->tName = tName;
}

std::string task::Student::getId(void)
{
	return this->id;
}

bool task::Student::getSex(void)
{
	if (3 == utils::char2int(this->id[0]))
	{
		return 1;
	}

	return 0;
}

int task::Student::getAge(void)
{
	time_t rawtime;

	struct tm *ct;

	time(&rawtime);
	ct = localtime(&rawtime);

	if (this->getBDMonth() > ct->tm_mon + 1 ||
		this->getBDMonth() == ct->tm_mon + 1 && this->getBDDay() <= ct->tm_mday)
	{
		return 1901 + ct->tm_year - this->getBDYear();
	}

	return 1900 + ct->tm_year - this->getBDYear();
}

int task::Student::getBDDay(void)
{
	char buf[2];

	buf[0] = this->id[5];
	buf[1] = this->id[6];
	
	return utils::char2int(buf, 2);
}

int task::Student::getBDMonth(void)
{
	char buf[2];

	buf[0] = this->id[3];
	buf[1] = this->id[4];
	
	return utils::char2int(buf, 2);
}

int task::Student::getBDYear(void)
{
	char buf[2];

	buf[0] = this->id[1];
	buf[1] = this->id[2];
	
	if (utils::char2int(buf, 2) > 10)
	{
		return 1900 + utils::char2int(buf, 2);
	}
	else
	{
		return 2000 + utils::char2int(buf, 2);
	}
}

void task::Student::setId(std::string id)
{
	this->id = id;
}

int task::Student::getFId(void)
{
	return this->fId;
}

void task::Student::setFId(int fId)
{
	this->fId = fId;
}

int task::Student::getMark(int key)
{
	if (sizeof(this->marks) / sizeof(this->marks[0]) <= key || 0 > key)
	{
		return -1;
	}

	return this->marks[key];
}

void task::Student::setMark(int key, int mark)
{
	if (sizeof(this->marks) / sizeof(this->marks[0]) <= key || 0 > key)
	{
		return;
	}

	this->marks[key] = mark;
}

void task::Student::clearMarks(void)
{
	int j = sizeof(this->marks) / sizeof(this->marks[0]);

	for (int i = 0; j > i; i++)
	{
		this->marks[i] = 0;
	}
}
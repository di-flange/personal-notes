/**
 * Author:	Anton Ishkov
 * Date:	17.11.2010
 */

#include "utils.h"

int utils::pow(int n, int x)
{
	if (n != 0)
	{
		return x * pow(n - 1, x);
	}
	else
	{
		return 1;
	}
}

int utils::char2int(char ch)
{
	return (int)ch - 48;
}

int utils::char2int(char* ch, int size)
{
	int result = 0;

	for (int i = 0; size > i; i++)
	{
		result += ((int)ch[size - i - 1] - 48) * pow(i, 10);
	}

	return result;
}
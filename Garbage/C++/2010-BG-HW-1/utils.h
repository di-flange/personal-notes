/**
 * Author:	Anton Ishkov
 * Date:	17.11.2010
 *
 * Heading of small function library which using while runing.
 */
#pragma once

namespace utils
{
	/**
	 * Exponentiation
	 */
	int pow(int n, int x);

	/**
	 * Converter from chars to int.
	 */
	int char2int(char ch);
	int char2int(char* ch, int size);
}
#include <iostream>
#include "code-1.lib.cpp"

int linarSearch(float target, float *arr, int size)
{
	// Search
	for (int i = 0; i < size; i++)
	{
		if (target == arr[i])
		{
			// We found it, exit
			return i;
		}
	}
  
	// Return error code (-1 - Not found)
	return -1;
}
int main()
{
	int key;
	float value;
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);

	std::cout << "Number: ";
	std::cin  >> value;
	
	if ((key = linarSearch(value, a, 15)) == -1)
	{
		std::cout << "Not found" << std::endl;
	}
	else
	{
		std::cout << "Position: " << key << std::endl;
	}
	
	return key;
}
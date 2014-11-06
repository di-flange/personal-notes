#include <iostream>
#include "code-1.lib.cpp"

void selSort(float *arr, int size)
{
	int   min;      // Location of minimum

	for (int i = 0; i < size - 1; i++)
	{
	    // At start minimum is frst element of current list
		min = i;
		
		// Search minimum
		for (int j = i + 1; j < size; j++)
		{
		    // If foun save it location
			if (arr[j] < arr[min])
			{
				min = j;
			}
		}

        // If minimum is not first element replace it
		if (min > i)
		{
		    replace(arr + min, arr + i);
		}
	}
}

int binarySearch(float target, float *arr, int size)
{ 
	printArr(std::cout, arr, size);
	
 	if (target < arr[0])
	{
		return NaN;
	}
	
	if (target > arr[size - 1])
	{
		return NaN;
	}

	if (size == 1)
	{
		return 0;
	}
	
	int mediane;
	
	mediane = size / 2;

	if (target < arr[mediane])
	{
		return binarySearch(target, arr, mediane);
	}

	return mediane + binarySearch(target, arr + mediane, size - 1);
}

int main()
{
	int key;
	float value;
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

				  
	selSort(a, 15);
    printArr(std::cout, a, 15);

	std::cout << "Number: ";
	std::cin  >> value;
	
	if ((key = binarySearch(value, a, 15)) == -1)
	{
		std::cout << "Not found" << std::endl;
	}
	else
	{
		std::cout << "Position: " << key << std::endl;
	}
	
	return key;
}
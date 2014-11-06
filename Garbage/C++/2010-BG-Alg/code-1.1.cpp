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

int main()
{
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);
    selSort(a, 15);
    std::cout << "Resulte:\n";
    printArr(std::cout, a, 15);
	
    return 0;	
}
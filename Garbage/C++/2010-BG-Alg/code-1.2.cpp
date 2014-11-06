#include <iostream>
#include "code-1.lib.cpp"

void selSortR(float *arr, int size)
{
    int min = 0; // location of minimum.
    
    // Search minimum
    for (int j = 1; j < size; j++)
    {
        // Save minimum location
        if (arr[j] < arr[min])
        {
            min = j;
        }
    }

    // Replace minimum and first element
    if (min > 0)
    {
        replace(arr + min, arr);
    }
    
    // Explore next part of array
    if (size > 2)
    {
        selSortR(arr + 1, size - 1);
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

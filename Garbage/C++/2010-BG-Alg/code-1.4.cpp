#include <iostream>
#include "code-1.lib.cpp"

void razmSort(float *arr, int size)
{
    int lastMove;               // Last move
    int leftBorder = 0;         // First move
    int rightBorder = size - 1; // Border of unsorted area
    
    int c = 0;
    
    while (rightBorder > 0)
    {
        lastMove = 0;
		int i = leftBorder;
		leftBorder = rightBorder;
		
        for (; i < rightBorder; i++)
        {
	        if (arr[i + 1] < arr[i])
	        {
	            replace(arr + i + 1, arr + i);

	            lastMove = i;

	            if (leftBorder > i)
	            {
	                leftBorder = i - 1;
	            }
	        }
	    }

        rightBorder = lastMove;
    }
}


int main()
{
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);
    razmSort(a, 15);
    std::cout << "Resulte:\n";
    printArr(std::cout, a, 15);

    return 0;
}

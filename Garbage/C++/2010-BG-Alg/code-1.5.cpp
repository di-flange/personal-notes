#include <iostream>
#include "code-1.lib.cpp"

void shakerSort(float *arr, int size)
{
    int left = 0;
    int right = size - 1;
    int point = 0;
  
    while (left < right)
    {
	    for (int i = left; i < right; i++)
		{ 
		    if (arr[i + 1] < arr[i])
		    {
		        replace(arr + i, arr + i + 1);
		        point = i;
		    }
	    }
	     
	    right = point;
	    
	    for (int i = right; i > left; i--)
	    {
		    if (arr[i - 1] > arr[i])
		    {
		        replace(arr + i, arr + i - 1);
		        point = i;
		    }
		}
		
	    left = point;
    }
}

int main()
{
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);
    shakerSort(a, 15);
    std::cout << "Resulte:\n";
    printArr(std::cout, a, 15);
	
    return 0;	
}

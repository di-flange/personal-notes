#include <iostream>
#include "code-1.lib.cpp"

void insertSort(float *arr, int size)
{
    float buffer;
    int point;
  
    for (int i = 1; i < size; i++)
    {
	    buffer = arr[i];
	    point  = i - 1;
	
	    while (point >= 0 && buffer < arr[point])
	    {
	        arr[point + 1] = arr[point];
	        point--;
	    }
	
	    arr[point + 1] = buffer;
    }
}

int main()
{
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);
    insertSort(a, 15);
    std::cout << "Resulte:\n";
    printArr(std::cout, a, 15);
    
    return 0;	
}

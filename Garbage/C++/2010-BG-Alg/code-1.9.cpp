#include <iostream>
#include "code-1.lib.cpp"

void sito(float *arr, int key, int size)
{
    float buffer = arr[key];
  
    for (int j; (j = 2 * key + 1) < size; )
    {
        if (j < size - 1 && arr[j] < arr[j + 1])
        {
            j++;
        }
        
	    if (buffer >= arr[j])
	    {
	        break;
	    }
	    
	    arr[key] = arr[j];
	    key = j;
    }
    
    arr[key] = buffer;
}

void heapSort(float *arr, int size)
{
    for (int k = size / 2 - 1; k >= 0; k--)
    {
        sito(arr, k, size);
    }
    
    while (--size > 0)
    {
        printArr(std::cout, arr, 15);
        replace(arr, arr + size);
	    std::cout << "<--" << std::endl;
	    printArr(std::cout, arr, 15);
	    sito(arr, 0, size);	    
    }
}

int main()
{
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);
    heapSort(a, 15);
    std::cout << "Resulte:\n";
    printArr(std::cout, a, 15);
	
    return 0;	
}

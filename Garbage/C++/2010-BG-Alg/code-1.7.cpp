#include <iostream>
#include "code-1.lib.cpp"

void shellSort(float *arr, int size)
{
    int h = 0;
    int j;
  
    float buffer;

    // Select  first hope size  
    for (int n = 1; 2 * n <= size; n = 3 * h + 1)
    {
        h = n;
    }

    // Look smaler hopes. 
    for (; h > 0; h /= 3)
    {
 	    for (int i = h; i < size; i++)
	    {
		    buffer = arr[i];
		    j = i - h;
		
		    while (j >= 0 && buffer < arr[j])
		    {
		        arr[j + h] = arr[j];
		        j -= h;		        
		    }
		
		    arr[j + h] = buffer;		    
	    }
    }
}

int main()
{
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);
    shellSort(a, 15);
    std::cout << "Resulte:\n";
    printArr(std::cout, a, 15);
	
    return 0;	
}

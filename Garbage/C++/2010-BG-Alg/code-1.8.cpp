#include <iostream>
#include "code-1.lib.cpp"

void qSort(float *arr, int size)
{
    int limLeft  = -1;   
    int limRight = size;

    float mediane = arr[limRight / 2];
    
    while (++limLeft < --limRight)
    {
        // Search limits
        for (; arr[limLeft]  < mediane; limLeft++);
        for (; arr[limRight] > mediane; limRight--);        
        
        // Replace items
	    if (limLeft < limRight)
	    {
	        replace(arr + limLeft, arr + limRight);
	    }
	    else
	    {
	        if (limLeft == limRight)
	        {
	            ++limLeft;
	            --limRight;
	        }
	        
	        break;
	    }
	    
    }

    if (limRight > 0)
    {
        qSort(arr, limRight + 1);
    }
    
    if (limLeft < size - 1)
    {
        qSort(arr + limLeft, size - limLeft);
    }
}

int main()
{
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);
    qSort(a, 15);
    std::cout << "Resulte:\n";
    printArr(std::cout, a, 15);
	
    return 0;	
}

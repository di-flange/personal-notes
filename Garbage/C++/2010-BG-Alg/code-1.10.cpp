#include <iostream>
#include "code-1.lib.cpp"

void merge(float *arr1, int size1, float *arr2, int size2, float *result)
{
	// Declare variables of pointers.
	int i1=0;
	int i2=0;
	int iR=0;
	
	// Merge arrays to result.
	while (true)
	{
		// If first array ended.
		if (i1 >= size1)
		{
			// Then move second to end of result 
			while (i2 < size2)
			{
				result[iR++] = arr2[i2++];
			}
			
			break;
		}

		// If second array ended.
		if (i2 >= size2)
		{
			// Then move second to end of result 
			while (i1 < size1)
			{
				result[iR++] = arr2[i2++];
			}
			
			break;
		}

		// If we have two arrays get smallest value
		if (arr1[i1] < arr2[i2])
		{
			result[iR++] = arr1[i1++];
		}
		else
		{
			result[iR++] = arr2[i2++];
		}
	}	
}

void mergeSort(float *arr, int size)
{  
	// Exit if in array one item.
	if (size < 2)
	{
		return;
	}
	
	int separater  = size / 2; // Separeter
	
	// Separete it for parts.
	mergeSort(arr, separater);
	mergeSort(arr + separater, size - separater);
	
	float *result = new float[size]; // Result array	
	merge(arr, separater, arr + separater, size - separater, result);
		
	for (int i = 0; i < size; i++)
	{
		arr[i] = result[i];
	}
	
	delete[] result;
}

int main()
{
	float a[] = { 1.2, 8.5, 0.3, 4.3, 0.1, 5.5, 0.75, 3.2,
				  8.1, 3.3, 4.5, 9.5, 2.7, 7.3, 3.3 };

    printArr(std::cout, a, 15);
    mergeSort(a,15);
    std::cout << "Resulte:\n";
    printArr(std::cout, a, 15);
	
    return 0;	
}
#include <fstream>

#include <stdio.h>
#include <fcntl.h>

#define FILE_NAME "dat.bin"

/**
 * Write to stream.
 */
void writeToStream(std::ostream &stream)
{
    float buffer;
    
    while (true)
    {
        std::cout << "Enter number: ";
        std::cin  >> buffer;
        
	    if (buffer > 0)
	    {
	        stream.write((char*) &buffer, sizeof(float));
	    }
	    
	    if (buffer == 0)
	    {
	        break;
	    }
    }
}

/** 
 * Print from stream
 */
void printStream(std::istream &stream)
{
    float buffer;
    
    while (true)
    {
        stream.read((char*) buffer, sizeof(buffer));
 
        if (stream.eof())
        {
            break;
        }
 
        std::cout << buffer << ' ';
    }
    
    std::cout << std::endl;
}

/**
 * Sort stream
 */ 
void sortSel(std::stream &stream)
{
    int   min;      // Location of minimum
    float buffer1;  // Buffer for store values and replace
    float buffer2;  // Buffer for store values
  
    // Set pointer to begin
    stream.seekg(0, std::ios::end);
    
    // Get size of array writed to file
    long size = stream.tellg() / sizeof(float);
    
    for (int i = 0; i < size - 1; i++)
    {
        // Save at buffer current element
	    stream.seekg(i * sizeof(float));
	    stream.read((char*) &buffer1, sizeof(float));
	
	    // Set minimum as current item
	    min = i;
	    
	    // Search minimum
	    for (int j = i + 1; j < size; j++)
	    {
	        // Move pointer
	        stream.seekg(j * sizeof(float));
	        stream.read((char*) &buffer2, sizeof(float));
	        
	        if (buffer2 < buffer1)
	        {
	            min = j;
	            buffer1 = buffer2;
	        }
	    }

        // Replace elements	
	    if (min != i)
	    {	
	        /**
	         * Value of minmimum we already have at buffer one
	         */
	    
	        // Get first element value
	        stream.seekg(i * sizeof(float));
	        stream.read((char*) &buffer2, sizeof(float)); 
	        
	        // Write to position of minimum value of first item
	        stream.seekp(min * sizeof(float));
	        stream.write((char*)&buffer2 ,sizeof(float));
	        
	        // Write to first position value of minimum
	        stream.seekp(i * sizeof(float));
	        stream.write((char*) &buffer1, sizeof(float));
	    }
    }
}

int main()
{
    // Write to file any array
    std::ofstream outS;
    outS.open(FILE_NAME, std::ios::out | std::ios::binary);
    writeToStream(outS);
    outS.close();

    // Print data from file
    std::ifstream inS;
    inS.open(FILE_NAME, std::ios::in | std::ios::binary);
    print_fl(inS);
    inS.close();

    // Sort file
    std::fstream stream;
    stream.open(FILE_NAME, std::ios::in | std::ios::out | std::ios::binary);
    sortSel(stream);
    stream.close();

    // Print data from file
    std::ifstream inS;
    inS.open(FILE_NAME, std::ios::in | std::ios::binary);
    print_fl(inS);
    inS.close();
 
    return 0;
}

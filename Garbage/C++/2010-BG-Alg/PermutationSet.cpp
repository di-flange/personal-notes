#include <iostream>

template <class T>
class PermutationSet
{
public:
    /**
     * Constructor.
     */
    PermutationSet(T* elements, int size)
    {
        // Create elements set.
        this->elements = new T*[size];
        
        // Fill array
        for (int i = 0; i < size; i++)
        {
            this->elements[i] = &elements[i];
        }
        
        // Set start values of variables.
        this->size = size;
        
        this->pointer = 0;
        this->last = 0;
    }

    /**
     * Destructor.
     */
    ~PermutationSet(void)
    {
        delete[] this->elements;
    }
        
    /**
     * Get permutation
     */
    T* getPermutation(void)
    {
        // If this is not first call to this permutation
        if (this->last)
        {
            // Return previos returned value.
            return this->last; 
        }
        
        // If pointer in zero we can return array.
        if (!this->pointer)
        {
            this->elements;
        }
        
        // Generate permutation.
        this->last = this->generate(this->pointer);
        
        return this->last; 
    }
    
    /**
     * Move pointer to next permutation.
     */
    bool next(bool clear = true)
    {
        // If we tray get more permutation than we have in this array
        if (this->pointer == this->getCount())
        {
            this->pointer = 0;
            
            return false;
        }
        
        // Move pointer.
        this->pointer++;
        
        // Delete last permutation
        if (clear && this->pointer)
        {
            delete[] this->last;
        }
        
        this->last = 0;
    }
    
    /**
     * Return count of permutation in this set
     */
    int getCount(void)
    {
        return this->permutation(this->size);
    }
private:
    /**
     * Current selected permutation.
     */
    int pointer;
    
    /**
     * Size of elements array.
     */
    int size;
    
    /**
     * Array of permutation.
     */
    T** elements;
    
    /**
     * Last geted permutation.
     */
    T** last;
    
    /**
     * n!
     */
    int permutation(int n)
    {
        if (n < 2)
        {
            return 1;
        }        
        
        return n * this->permutation(n - 1);
    }
    
    /**
     * Generate permutation by number.
     */
    int* generate(int pointer)
    {
        if (!pointer)
        {
            this->elements;
        }
        
        pointer++;
        bool used[size];
        
        for (int i = 0; i < size; i++)
        {
            used[i] = false;
        }
/*
    pointer 1
    count 6

    step = count / size;
    
    pointer + 1 = position;
    
    
    for (int i = step; i < position )
*/

        
        int size = this->getSize();
    }
};

int main(void)
{
    int* set = new int[10];
    
    for (int i = 0; i < 10; i++)
    {
        set[i] =  i;
    }
   
    PermutationSet<int> ps(set, 10);
    
    std::cout << ps.getCount();
    
    return 0;
}
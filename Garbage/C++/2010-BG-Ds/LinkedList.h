/**
 * Author:      Anton [Hasado|Sigurn] Ishkov
 * Date:        8.01.2011
 * Version:     0.2
 *
 * Title:       Linked list 
 * Description: This is implementation of the Linked List data
 *              structure. It have main fucnctions of work with
 *              this type of data, and integrated iterator. Which
 *              can be used for fast access to nodes.
 *              
 *              This implementation use like nodes objects of
 *              Linked list Node class (LinkedNode.h). This
 *              class incapsulated data of node and pointer to
 *              the next node.
 */
#pragma once

#include "LinkedNode.h"

namespace sim
{
    namespace ds
    {
        template <class T>
        class LinkedList
        {
        public:
            /**
             * Empty constructor.
             * 
             * Note: Setup main variable of iterator and
             *       structure.
             */
            LinkedList(void);

            /**
             * Destructor.
             */
            ~LinkedList(void);

            /**
             * Insert value to the list.
             *
             * Input: value - data of new node.
             *        key - position to inserting of node.
             * 
             * Note: Reset iterator if this shift is affecting
             *       on it.
             */
            void insert(const T value, int key = -1);

            /**
             * Remove value from list.
             *
             * Input: key - position of node which will be removed.
             *
             * Note: Move iterator if this shift is affecting
             *       on it. But if you delete element which 
             *       selected by iterator it stay in old position.
             */
            void remove(const int key);

            /**
             * Clear list.
             *
             * Note: Blank iterator, and delete (not unlink) all
             *       nodes.
             */
            void clear(void);

            /**
             * Get size of list.
             *
             * Output: count of nodes in list.
             */
            int size(void);

            /**
             * Get element with selected key.
             *
             * Input: position of node which data will be got.
             *
             * Output: data which got from node.
             *
             * Note: Using this operation in circles is extremly
             *       slow.
             */
            T operator[](int key);

            /**
             * Get element with selected key.
             *
             * Input: position of node which data will be got.
             *
             * Output: data which got from node.
             *
             * Note: Using this operation in circles is extremly
             *       slow.
             */
            T get(int key);

            /**
             * Find item with selected value.
             *
             * Input: value which we search in list.
             *
             * Output: position of first founded element.
             *
             * Note: This method use iterator and start search
             *       from it position.
             *       
             *       If you will use this current position of
             *       iterator will be lost.
             */
            int find(T value);

            /**
             * Sort list.
             *
             * Note: Not implemented function.
             */
            void sort(void);

            /**
             * Swap two nodes.
             *
             * Input: first - key of first node.
             *        second - key of second node.
             */
            void swap(int first, int second);

            /**
             * Move iterator and get next.
             *
             * Output: data from next node.
             */
            T getNext(void);

            /**
             * Get current.
             *
             * Output: data from current node.
             */
            T getCurrent(void);

            /**
             * Next node, move iterator.
             *
             * Output: status of moving.
             */
            bool next(void);

            /**
             * Get position of iterator.
             *
             * Output: key selected by iterator.
             */
            int tellg(void);

            /**
             * Set position of iterator.
             *
             * Input: key - new position of iterator.
             *
             * Output: status of moving.
             */
            bool seekg(int key);
        private:
            /**
             * First element of list.
             */
            LinkedNode<T>* _start;

            /**
             * Position of iterator.
             */
            int _iteratorPosition;

            /**
             * Pointer of iterator.
             */
            LinkedNode<T>* _iteratorPointer;

            /**
             * Size of linked list.
             */
            int _size;

            /**
             * Get node with selected key.
             */
            LinkedNode<T>* _get(int key);

            /**
             * Delete this node and return next node;
             */
            LinkedNode<T>* _delete(LinkedNode<T>* node);
        };
    }
}

template <class T>
sim::ds::LinkedList<T>::LinkedList(void)
{
    // Set main data.
    this->_start = 0;
    this->_size  = 0;

    // Set iterator.
    this->_iteratorPosition = 0;
    this->_iteratorPointer  = 0;
}

template <class T>
sim::ds::LinkedList<T>::~LinkedList(void)
{
    if (this->_start)
    {
        this->clear();
    }
}

template <class T>
void sim::ds::LinkedList<T>::insert(const T value, int key)
{
    // If key not valid or default save value as last node.
    if (key < 0 || key > this->_size)
    {
        key = this->_size;
    }

    // Create new node 
    LinkedNode<T>* buf = new LinkedNode<T>;
    buf->setData(value);

    // If key not last, move current node with this element
    // to rest of new node.
    if (this->_size > key)
    {
        buf->setNext(this->_get(key));
    }

    // Save in list
    if (!key)
    {
        // IF key is first node save it like start.
        this->_start = buf;
    }
    else
    {
        // If not first save in 'next' pointer of previous node.
        this->_get(key - 1)->setNext(buf);
    }

    // Save new count of nodes.
    this->_size++;

    // Correct iterator.
    if (this->_iteratorPosition == key)
    {
        this->_iteratorPointer = buf;
    }
    else if (this->_iteratorPosition > key)
    {
        this->_iteratorPointer = this->_get(key);
    }
    
    if (!this->_iteratorPointer)
    {
        this->_iteratorPosition = 0;
        this->_iteratorPointer  = this->_start;
    }
}

template <class T>
void sim::ds::LinkedList<T>::remove(const int key)
{
    // Check key valid or not.
    if (key < 0 || key >= this->_size)
    {
        return;
    }

    // Correct iterator.
    if (key <= this->_iteratorPosition)
    {
        this->_iteratorPointer = this->_iteratorPointer->getNext();

        // If we haven't link to node, blank iterator.
        if (this->_iteratorPointer)
        {
            this->_iteratorPosition = 0;
        }
        else
        {
            this->_iteratorPosition--;
        }
    }

    // Recreate link between nodes.
    if (!key)
    {
        // If we remove first element, save next like start
        this->_start = this->_delete(this->_get(key));
    }
    else
    {
        // If not first element get next and save it in previous element.
        this->_get(key - 1)->setNext(this->_delete(this->_get(key)));
    }

    // Save new count of nodes.
    this->_size--;
}

template <class T>
void sim::ds::LinkedList<T>::clear(void)
{
    // Check empty list or not.
    if (!this->_size)
    {
        return;
    }

    // Delete all.
    LinkedNode<T>* buf = this->_start;
    while (buf = this->_delete(buf));
    
    // Save new count of nodes, new start and blank iterator.
    this->_start = 0;
    this->_size  = 0;

    this->_iteratorPosition = 0;
    this->_iteratorPointer  = 0;
}

template <class T>
int sim::ds::LinkedList<T>::size(void)
{
    return this->_size;
}

template <class T>
T sim::ds::LinkedList<T>::operator[](int key)
{
    // This is only alias.
    return this->get(key);
}

template <class T>
T sim::ds::LinkedList<T>::get(int key)
{
    // If key not valid we can return first node data.
    if (key >= this->_size || key < 1)
    {
        return this->_start->getData();
    }

    // Return node data.
    return this->_get(key)->getData();
}

template <class T>
int sim::ds::LinkedList<T>::find(T value)
{
    // Get node
    LinkedNode<T>* buf = this->_iteratorPointer;
    int key = 0;

    while (true)
    {
        // If we found it.
        if (buf->getData() == value)
        {
            return key;
        }

        // If list ended.
        if ((buf = buf->getNext()) == 0)
        {
            return -1;
        }

        key++;
    }

    // Return position.
    return key;
}

template <class T>
void sim::ds::LinkedList<T>::sort(void)
{
    // Not implemented.
}

template <class T>
void sim::ds::LinkedList<T>::swap(int first, int second)
{
    // Filter bad values of keys.
    if (first == second || first >= this->_size || 
        second >= this->_size || first < 0 || second < 0)
    {
        return;
    }

    // Set first smaller 
    if (first > second)
    {
        int buffer;

        buffer = first;
        first  = second;
        second = buffer;
    }

    // Swap
    if (first == 0)
    {
        LinkedNode<T>* buf1 = this->_start;
        LinkedNode<T>* buf2 = this->_get(second - 1);

        this->_start = buf2->getNext();
        buf2->setNext(buf1);

        buf2 = buf1->getNext();
        buf1->setNext(this->_start->getNext());
        this->_start->setNext(buf2);

        // Correct iterator.
        if (this->_iteratorPosition == second)
        {
            this->_iteratorPointer = buf1;
        }
        else if (!this->_iteratorPosition)
        {
            this->_iteratorPointer = this->_start;
        }
    }
    else
    {
        LinkedNode<T>* buf1 = this->_get(first - 1);
        LinkedNode<T>* buf2 = this->_get(second - 1);
        LinkedNode<T>* buf3 = buf1->getNext();

        buf1->setNext(buf2->getNext());
        buf2->setNext(buf3);

        buf3 = buf1->getNext()->getNext();
        buf1->getNext()->setNext(buf2->getNext()->getNext());
        buf2->getNext()->setNext(buf3);

        // Correct iterator.
        if (this->_iteratorPosition == second)
        {
            this->_iteratorPointer = buf2->getNext();
        }
        else if (this->_iteratorPosition == first)
        {
            this->_iteratorPointer = buf1->getNext();
        }
    }

    // Blank iterator if it demaged.
    if (!this->_iteratorPointer)
    {
        this->_iteratorPosition = 0;
        this->_iteratorPointer  = this->_start;
    }
}

template <class T>
T sim::ds::LinkedList<T>::getNext(void)
{
    this->next();
    return this->getCurrent();
}

template <class T>
T sim::ds::LinkedList<T>::getCurrent(void)
{
    // If we haven't selected node.
    if (!this->_iteratorPointer)
    {
        return this->_start->getData();
    }

    // Return node data.
    return this->_iteratorPointer->getData();
}

template <class T>
bool sim::ds::LinkedList<T>::next(void)
{
    // We can't move it.
    if (this->_size - 1 <= this->_iteratorPosition)
    {
        return false;
    }

    // Move
    this->_iteratorPosition++;
    this->_iteratorPointer = this->_iteratorPointer->getNext();

    return true;
}

template <class T>
int sim::ds::LinkedList<T>::tellg(void)
{
    return this->_iteratorPosition;
}

template <class T>
bool sim::ds::LinkedList<T>::seekg(int key)
{
    // We can't move it.
    if (this->_size <= key || 0 > key)
    {
        return false;
    }

    // Move
    this->_iteratorPointer  = this->_get(key);
    this->_iteratorPosition = key;

    return true;
}

template <class T>
sim::ds::LinkedNode<T>* sim::ds::LinkedList<T>::_get(int key)
{
    // If we can't get it send first element.
    if (key >= this->_size || key < 1)
    {
        return this->_start;
    }

    // Get node.
    LinkedNode<T>* buf;

    if (key < this->_iteratorPosition)
    {
        buf = this->_start;
    }
    else
    {
        buf = this->_iteratorPointer;
    }

    while (key--)
    {
        buf = buf->getNext();
    }

    // Return node.
    return buf;
}

template <class T>
sim::ds::LinkedNode<T>* sim::ds::LinkedList<T>::_delete(sim::ds::LinkedNode<T>* node)
{
    // If pointer not valid return 0.
    if (!node)
    {
        return 0;
    }

    // Save next for returning it.
    LinkedNode<T>* nextNode = node->getNext();

    // Delete node and correct pointer.
    delete node;

    // Return next
    return nextNode;
}
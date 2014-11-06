/**
 * Author:      Anton [Hasado|Sigurn] Ishkov
 * Date:        8.01.2011
 * Version:     0.1
 *
 * Title:       Queue 
 * Description: This is implementation of the Queue data
 *              structure. It have main fucnctions of work with
 *              this type of data.
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
        class Queue
        {
        public:
            /**
             * Empty constructor.
             * 
             * Note: Setup main variable of structure.
             */
            Queue(void);

            /**
             * Destructor.
             */
            ~Queue(void);

            /**
             * Insert value to the queue.
             *
             * Input: value - data of new node.
             */
            void enqueue(const T value);

            /**
             * Remove first value from queue.
             */
            void dequeue(void);

            /**
             * Clear list.
             *
             * Note: Blank iterator, and delete (not unlink) all
             *       nodes.
             */
            void clear(void);

            /**
             * Get size of queue.
             *
             * Output: count of nodes in queue.
             */
            int size(void);

            /**
             * Get element from start.
             *
             * Output: data which got first node.
             */
            T get(void);
        private:
            /**
             * First element.
             */
            LinkedNode<T>* _first;

            /**
             * Last element.
             */
            LinkedNode<T>* _last;

            /**
             * Size of queue.
             */
            int _size;

            /**
             * Delete this node and return next node.
             */
            LinkedNode<T>* _delete(LinkedNode<T>* node);
        };
    }
}

template <class T>
sim::ds::Queue<T>::Queue(void)
{
    // Set main data.
    this->_first = 0;
    this->_last  = 0;
    this->_size  = 0;
}

template <class T>
sim::ds::Queue<T>::~Queue(void)
{
    if (this->_first)
    {
        this->clear();
    }
}

template <class T>
void sim::ds::Queue<T>::enqueue(const T value)
{
    // Create new node 
    LinkedNode<T>* buf = new LinkedNode<T>(value, 0);

    // Save in list
    if (!this->_first)
    {
        this->_last  = buf;
        this->_first = buf;
    }
    else
    {
        this->_last->setNext(buf);
        this->_last  = buf;
    }

    // Save new count of nodes.
    this->_size++;
}

template <class T>
void sim::ds::Queue<T>::dequeue(void)
{
    // Delete
    this->_first = this->_delete(this->_first);

    // Save new count of nodes.
    this->_size--;
}

template <class T>
void sim::ds::Queue<T>::clear(void)
{
    // Delete all.
    LinkedNode<T>* buf = this->_first;
    while (buf = this->_delete(buf));
    
    // Save new count of nodes, new top.
    this->_first = 0;
    this->_last  = 0;
    this->_size  = 0;
}

template <class T>
int sim::ds::Queue<T>::size(void)
{
    return this->_size;
}

template <class T>
T sim::ds::Queue<T>::get(void)
{
    // Return node data.
    return this->_first->getData();
}

template <class T>
sim::ds::LinkedNode<T>* sim::ds::Queue<T>::_delete(sim::ds::LinkedNode<T>* node)
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
/**
 * Author:      Anton [Hasado|Sigurn] Ishkov
 * Date:        8.01.2011
 * Version:     0.1
 *
 * Title:       Stack 
 * Description: This is implementation of the Stack data
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
        class Stack
        {
        public:
            /**
             * Empty constructor.
             * 
             * Note: Setup main variable of structure.
             */
            Stack(void);

            /**
             * Destructor.
             */
            ~Stack(void);

            /**
             * Insert value to the stack.
             *
             * Input: value - data of new node.
             */
            void push(const T value);

            /**
             * Remove last value from stack.
             */
            void pop(void);

            /**
             * Clear stack.
             *
             * Note: Blank iterator, and delete (not unlink) all
             *       nodes.
             */
            void clear(void);

            /**
             * Get size of stack.
             *
             * Output: count of nodes in stack.
             */
            int size(void);

            /**
             * Get element from top.
             *
             * Output: data which got top node.
             */
            T top(void);
        private:
            /**
             * Top element.
             */
            LinkedNode<T>* _top;

            /**
             * Size of stack.
             */
            int _size;

            /**
             * Delete this node and return next node;
             */
            LinkedNode<T>* _delete(LinkedNode<T>* node);
        };
    }
}

template <class T>
sim::ds::Stack<T>::Stack(void)
{
    // Set main data.
    this->_top  = 0;
    this->_size = 0;
}

template <class T>
sim::ds::Stack<T>::~Stack(void)
{
    if (this->_top)
    {
        this->clear();
    }
}

template <class T>
void sim::ds::Stack<T>::push(const T value)
{
    // Create new node 
    LinkedNode<T>* buf = new LinkedNode<T>(value, this->_top);

    // Save in list
    this->_top = buf;

    // Save new count of nodes.
    this->_size++;
}

template <class T>
void sim::ds::Stack<T>::pop(void)
{
    // Delete
    this->_top = this->_delete(this->_top);

    // Save new count of nodes.
    this->_size--;
}

template <class T>
void sim::ds::Stack<T>::clear(void)
{
    // Delete all.
    LinkedNode<T>* buf = this->_top;
    while (buf = this->_delete(buf));
    
    // Save new count of nodes, new top.
    this->_top  = 0;
    this->_size = 0;
}

template <class T>
int sim::ds::Stack<T>::size(void)
{
    return this->_size;
}

template <class T>
T sim::ds::Stack<T>::top(void)
{
    // Return node data.
    return this->_top->getData();
}

template <class T>
sim::ds::LinkedNode<T>* sim::ds::Stack<T>::_delete(sim::ds::LinkedNode<T>* node)
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
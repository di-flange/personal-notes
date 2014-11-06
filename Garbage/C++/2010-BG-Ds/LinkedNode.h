/**
 * Author:      Anton [Hasado|Sigurn] Ishkov
 * Date:        8.01.2011
 * Version:     0.2
 *
 * Title:       Linked node 
 * Description: This is implementation of the List Node data
 *              structure.
 */
#pragma once

namespace sim
{
    namespace ds
    {
        template <class T>
        class LinkedNode
        {
        public:
            /**
             * Empty contructor.
             */
	        LinkedNode(void);

            /**
             * Full constructor.
             */
            LinkedNode(const T data, LinkedNode<T>* next);

            /**
             * Destructor.
             */
	        ~LinkedNode(void);

            /**
             * Get data.
             */
            T getData(void);

            /**
             * Set data.
             */
            void setData(T data);

            /**
             * Get next node.
             */
            LinkedNode<T>* getNext(void);

            /**
             * Set next node.
             */
            void setNext(LinkedNode<T>* next);
        private:
            /**
             * Data of node.
             */
	        T _data;

            /**
             * Next node of list.
             */
	        LinkedNode<T>* _next;
        };
    }
}

#include "LinkedNode.h"

template <class T>
sim::ds::LinkedNode<T>::LinkedNode<T>(void)
{
    this->_next = 0;
}

template <class T>
sim::ds::LinkedNode<T>::LinkedNode(const T data, sim::ds::LinkedNode<T>* next)
{
    this->_data = data;
    this->_next = next;
}

template <class T>
sim::ds::LinkedNode<T>::~LinkedNode(void)
{
    
}

template <class T>
T sim::ds::LinkedNode<T>::getData(void)
{
    return this->_data;
}

template <class T>
void sim::ds::LinkedNode<T>::setData(T data)
{
    this->_data = data;
}

template <class T>
sim::ds::LinkedNode<T>* sim::ds::LinkedNode<T>::getNext(void)
{
    return this->_next;
}

template <class T>
void sim::ds::LinkedNode<T>::setNext(sim::ds::LinkedNode<T>* next)
{
    this->_next = next;
}
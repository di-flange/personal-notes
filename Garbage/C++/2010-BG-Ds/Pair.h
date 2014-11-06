/**
 * Author:      Anton [Hasado|Sigurn] Ishkov
 * Date:        9.01.2011
 * Version:     0.1
 *
 * Title:       Pair 
 * Description: This is data structure which look up
 *              together two values.
 */
#pragma once

namespace sim
{
    namespace ds
    {
        template <class F, class S>
        class Pair
        {
        public:
            /**
             * Empty constructor.
             */
            Pair(void);

            /**
             * Fill constructor.
             *
             * Input: first  - first value.
             *        second - second value.
             */
            Pair(F first, S second);

            /**
             * Destructor.
             */
            ~Pair(void);

            /**
             * Get first value.
             *
             * Output: first value.
             */
            F getFirst(void);

            /**
             * Set first value.
             *
             * Input: new value for first element.
             */
            void setFirst(F first);

            /**
             * Get second value.
             *
             * Output: second value.
             */
            S getSecond(void);

            /**
             * Set second value.
             *
             * Input: new value for second element.
             */
            void setSecond(S second);

            /**
             * Compare to objects of pair.
             *
             * Output: operand - compared values.
             *
             * Input: is same, or not.
             */
            bool operator==(const Pair<F, S> operand);
        private:
            /**
             * Value of first element.
             */
            F _first;

            /**
             * Value of second element.
             */
            S _second;
        };
    }
}

template <class F, class S>
sim::ds::Pair<F, S>::Pair(void)
{

}

template <class F, class S>
sim::ds::Pair<F, S>::Pair(F first, S second)
{
    this->_first = first;
    this->_second = second;
}

template <class F, class S>
sim::ds::Pair<F, S>::~Pair(void)
{
    
}

template <class F, class S>
F sim::ds::Pair<F, S>::getFirst(void)
{
    return this->_first;
}

template <class F, class S>
void sim::ds::Pair<F, S>::setFirst(F first)
{
    this->_first = first;
}

template <class F, class S>
S sim::ds::Pair<F, S>::getSecond(void)
{
    return this->_second;
}

template <class F, class S>
void sim::ds::Pair<F, S>::setSecond(S second)
{
    this->_second = second;
}

template <class F, class S>
bool sim::ds::Pair<F, S>::operator==(const Pair<F, S> operand)
{
    if (operand.getFirst() == this->getFirst()
        || operand.getSecond() == this->getSecond())
    {
        return true;
    }

    return false;
}
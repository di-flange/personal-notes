/**
 * Author:      Anton [Hasado|Sigurn] Ishkov
 * Date:        9.01.2011
 * Version:     0.4
 *
 * Title:       Graph 
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

#include "LinkedList.h"
#include "Queue.h"
#include "Stack.h"
#include "Pair.h"

#include <vector>
#include <iostream>
namespace sim
{
    namespace ds
    {
        template <class T>
        class Graph
        {
        public:
            /**
             * Empty constructor.
             * 
             * Input: oriented - oriented graph or not.
             *
             * Note: Setup type of graph.
             */
            Graph(bool oriented = true);
            
            /**
             * Destructor.
             */
            ~Graph(void);

            /**
             * Add new node.
             *
             * Input: value - value of node.
             */
            void addNode(T value);

            /**
             * Delete node with selected key.
             *
             * Input: key - key of node which will be
             *              deleted
             *
             * Node: Past it keys can be shifted.
             */
            void deleteNode(int key);

            /**
             * Rewrite node value whith selected key.
             *
             * Input: value - new value of node.
             *        key   - key of node.
             */
            void editNode(const T value, const int key);

            /**
             * Get node whith key.
             *
             * Input: key - key of node which will be
             *              got.
             *
             * Output: value of selected node.
             */
            T getNodeByKey(int key);

            /**
             * Get key by node value.
             *
             * Input: value - value which will be searched
             *                in nodes.
             *
             * Output: key of node in which it founded. 
             */
            int getNodeByValue(T value);

            /**
             * Get all nodes by value.
             *
             * Input: value - value which will be searched
             *                in nodes.
             *        count - count of founded nodes.
             *
             * Output: dynamic array of nodes key.
             */
            int* getAllNodesByValue(const T value, int &count);

            /**
             * Get count of nodes.
             *
             * Output: count of nodes.
             */
            int nodesCount(void);

            /**
             * Add edge.
             *
             * Input: first       - first node key.
             *        second      - second node key.
             *        coefficient - weight of edge.
             */
            void addEdge(int first, int second, int coefficient);

            /**
             * Delete edge.
             *
             * Input: first       - first node key.
             *        second      - second node key.
             *        coefficient - weight of edge.
             */
            void deleteEdge(int first, int second);

            /**
             * Check neighbors this nodes or not.
             *
             * Input: first  - first node.
             *        second - second node.
             *
             * Output: neigbors they or not.
             */
            bool adjacent(const int first, const int second);

            /**
             * Get all naibors.
             */
            int* neighbors(const int key, int &count);

            /**
             *
             */
            int* bestWay(const int start);

            /**
             * TODO: Implement other methods.
             */
        private:
            std::vector<LinkedList<Pair<int, int>*>*> _nodes;
            std::vector<T> _values;
            bool _oriented;

            /**
             * Node whith this key is exist.
             *
             * Input: key - key of node.
             *
             * Output: exist node or not.
             */ 
            bool _exist(const int key);

            /**
             * Shift elements if one of node was deleted.
             *
             * Input: key - key of node which was deleted
             *
             * Note: unsafe function.
             */
            void _shift(const int key);

            /**
             * Delete node.
             *
             * Input: key - key of node which will be deleted.
             *
             * Note: unsafe function.
             */
            void _delete(int key);
        };
    }
}

template <class T>
sim::ds::Graph<T>::Graph(bool oriented)
{
    this->_oriented = oriented;
}

template <class T>
sim::ds::Graph<T>::~Graph(void)
{
    // Delete naber lists.
    for (int i = 0; i < this->_nodes.size(); i++)
    {
        this->_delete(i);
    }
}

template <class T>
void sim::ds::Graph<T>::addNode(T value)
{
    this->_values.push_back(value);
    this->_nodes.push_back(new LinkedList<Pair<int, int>*>);
}

template <class T>
void sim::ds::Graph<T>::deleteNode(int key)
{
    if (!this->_exist(key))
    {
        return;
    }

    this->_delete(key);
    this->_shift(key);
}

template <class T>
void sim::ds::Graph<T>::editNode(const T value, const int key)
{
    if (!this->_exist(key))
    {
        return;
    }

    this->_values[key] = value;
}

template <class T>
T sim::ds::Graph<T>::getNodeByKey(int key)
{
    return this->_values[key];
}

template <class T>
int sim::ds::Graph<T>::getNodeByValue(T value)
{
    // Search
    for (int i = this->_values.size() - 1; i > -1; i--)
    {
        if (this->_values[i] == value)
        {
            return i;
        }
    }

    return -1;
}

template <class T>
int* sim::ds::Graph<T>::getAllNodesByValue(const T value, int &count)
{
    // Search
    Queue<int> founded;

    for (int i = 0; i < this->_values.size(); i++)
    {
        if (this->_values[i] == value)
        {
            founded.enqueue(i);
        }
    }

    // Save in array.
    if (!(count = founded.size()))
    {
        return 0;
    }
    
    int* result = new int[count];
    
    for (int i = 0; founded.size(); i++)
    {
        result[i] = founded.get();
        founded.dequeue();
    }

    return result;
}

template <class T>
int sim::ds::Graph<T>::nodesCount(void)
{
    return this->_nodes.size();
}

template <class T>
void sim::ds::Graph<T>::addEdge(int first, int second, int coefficient)
{
    if (!this->_exist(first) || !this->_exist(second))
    {
        return;
    }

    if (this->adjacent(first, second))
    {
        return;
    }

    this->_nodes[first]->insert(new Pair<int, int>(second, coefficient));

    if (!this->_oriented)
    {
        this->_nodes[second]->insert(new Pair<int, int>(first, coefficient));
    }
}

template <class T>
void sim::ds::Graph<T>::deleteEdge(int first, int second)
{
    if (!this->_exist(first) || !this->_exist(second))
    {
        return;
    }

    if (!this->_nodes[first]->size())
    {
        return;
    }

    this->_nodes[first]->seekg(0);

    while (true)
    {
        if (this->_nodes[first]->getCurrent()->getFirst() == first)
        {
            delete this->_nodes[first]->getCurrent();
            this->_nodes[first]->remove(this->_nodes[first]->tellg());
        }
        else if (!this->_nodes[first]->next())
        {
            break;
        }
    }
}

template <class T>
bool sim::ds::Graph<T>::adjacent(const int first, const int second)
{
    if (!this->_exist(first) || !this->_exist(second))
    {
        return false;
    }

    if (!this->_nodes[first]->size())
    {
        return false;
    }

    this->_nodes[first]->seekg(0);

    while (true)
    {
        if (this->_nodes[first]->getCurrent()->getFirst() == second)
        {
            return true;
        }

        if (!this->_nodes[first]->next())
        {
            return false;
        }
    }
}

template<class T>
int* sim::ds::Graph<T>::neighbors(const int key, int &count)
{
    if (!this->_exist(key))
    {
        return 0;
    }

    // Save in array.
    if (!(count = this->_nodes[key]->size()))
    {
        return 0;
    }
    
    int* result = new int[count];

    while (true)
    {
        result[this->_nodes[key]->tellg()] = this->_nodes[key]->getCurrent()->getFirst();

        if (!this->_nodes[key]->next())
        {
           break;
        }
    }   

    return result;
}

template<class T>
int* sim::ds::Graph<T>::bestWay(const int start)
{
    if (!this->_exist(start))
    {
        return 0;
    }

    Queue<int> que;
    int count = 0;
    int* nbr;
    bool* visited = new bool[this->nodesCount()];

    que.enqueue(key);

    while (int )
    {
        

    }
    for (int i = 0; i < count; i++)
    {
        this->neighbors(i, count);
    }

    //----------------------------------------------
    // Prepair.
    LinkedList<int>* ways = new LinkedList<int>[this->nodesCount()];
    int* distance = new int[this->nodeCount()];
    bool* visited = new bool[this->nodeCount()];
    Queue<int> que;
    int count = 0;
    int* nbr;
    int currDist;

    for (int i = 0; i < this->nodesCount(); i++)
    {
        if (i == start)
        {
            // From start to start distance zero.
            distance[i] = 0;
            
            // From start to start way looking like "1" save it.
            ways[i].insert(i);
        }
        else
        {
            // Pre executing data.
            distance[i] = -1;
            
            // From start to start way looking like "1 - 1" save it.
            ways[i].insert(-1);
        }
    }

    que.enqueue(start);
    visited[start] = true;

    // Swarch ways
    while (que.size())
    {
        // We look over all naighbor edges.
        nbr = this->neighbors(que.get(), count);
        
        for (int i = 0; i < count; i++)
        {
            // Get current distance.
            this->_nodes[que.get()]->seekg(0);            
            
            // Search current edge.
            while (true)
            {
                // Save it in current distance and break cicle
                if (this->_nodes[que.get()]->getCurrent()->getFirst() == nbr[i])
                {
                    currDist = this->_nodes[que.get()]->getCurrent()->getSecond();
                    break;
                }

                // Exit if get all edges
                if (this->_nodes[que.get()]->next())
                {
                    /**
                     * TODO: Here somthing wrong, make exception here
                     */
                    break;
                }
            }

            // Save distance.
            currDist += distance[que.get()];

            // Save new way if it better 
            if (distance[nbr[i]] == -1 || distance[nbr[i]] > currDist)
            {
                distance[nbr[i]] = currDist;

                // Copy way from start to prvious node.
                ways[que.get()].seekg(0);
                while (true)
                {
                    ways[nbr[i]].insert(ways[que.get()]->getCurrent());

                    if (que.get() != ways[que.get()].getCurrent())
                    {
                        break;
                    }
                }

                // Add last hope to way.
                ways[nbr[i]].insert(nbr[i]);
            }

            //


        }
    }
    //-----------------------------------------------------
    LinkedList<int>* ways = new LinkedList<int>[this->nodesCount()];    
    int* distance = new int[this->nodeCount()];                         // dist
    bool* visited = new bool[this->nodeCount()];// used
    Queue<int> que;
    int count = 0;
    int* nbr;
    int currDist;                           //unsigned t_min_dist;//Текуща дължина на най-късия път
    
    int* pred = new int[];

    for (int i=0;i<BrV;i++)
    {
        used[i]=0;
        dist[i]=9999;
        pred[i]=-1;
    }
    
    
    int j;
                            
    v = start;
 
    rebro *p=a[v].first;
 
    while (p)
    {
        i=p->nom;
        distance[i]=p->teg;
        pred[i]=v;
        p=p->next;
    }

    used[v]=1;
    
    while (true)
    {
        j=-1;
        t_min_dist=9999;
  
        //Избираме като връх j този, за който used[j]=0 и dist[j] e минимално
        for (i=0;i<BrV;i++)
            if (used[i]==0 && dist[i]<t_min_dist)
            {
                t_min_dist=dist[i]; j=i;
            }

        if (j==-1) break;//Няма непосетен, до който няма път.

          used[j]=1; // j-ия връх е непосетен и има път до него.
          //За всеки непосетен връх i изпълняваме d[i]=min(d[i], d[j]+A[j][i])
          p=a[j].first;
          while (p){
           i=p->nom;
           if (!used[i])
            if (dist[i] > dist[j] + p->teg) {
               dist[i]=dist[j] + p->teg;
               pred[i]=j;//Регистрираме j-ия връх като предшественик на i-ия.
            }
            p=p->next;
          }
     }
    //----------------------------------------------
}

template<class T>
bool sim::ds::Graph<T>::_exist(const int key)
{
    if (key < 0 || key >= this->_nodes.size())
    {
        return false;
    }

    return true;
}

template<class T>
void sim::ds::Graph<T>::_shift(const int key)
{
    for (int i = 0; i < this->_nodes.size(); i++)
    {
        this->_nodes[i]->seekg(0);

        while (true)
        {
            // Remove edge if it was deleted.
            if (this->_nodes[i]->getCurrent()->getFirst() == key)
            {
                delete this->_nodes[i]->getCurrent();
                this->_nodes[i]->remove(this->_nodes[i]->tellg());
            }

            // Decrease key in age if it was shifted.
            if (this->_nodes[i]->getCurrent()->getFirst() > key)
            {
                this->_nodes[i]->getCurrent()->setFirst(this->_nodes[i]->getCurrent()->getFirst() - 1);
            }

            // Exit
            if (this->_nodes[i]->next())
            {
                break;
            }
        }
    }
}

template<class T>
void sim::ds::Graph<T>::_delete(int key)
{
    // TODO: Debug it.
    /**
    this->_nodes[key]->seekg(0);

    while (true)
    {
        if (this->_nodes[key]->getCurrent())
        {
            delete this->_nodes[key]->getCurrent();
        }

        if (this->_nodes[key]->next())
        {
            break;
        }
    }

    delete this->_nodes[key];
    */
    /**
     * TODO: Somthing wrong with _values. Check.
     */
    //std::vector<T>::iterator it;
    //it += key;
    //this->_values.erase(it);
    //this->_nodes.erase(this->_nodes.begin() + key);
}
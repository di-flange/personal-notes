#include "LinkedList.h"
#include "Stack.h"
#include "Queue.h"
#include "Graph.h"

#include <string>
#include <iostream>
#include <conio.h>

void testLinkedList(void);
void testStack(void);
void testQueue(void);
void testGraph(void);

int main()
{
    int sel;

    while (true)
    {
	    // Output menu
	    std::cout	<< "Menu:\n"
    			    << "\t [1] Linked List test\n"
	    		    << "\t [2] Stack test\n"
		    	    << "\t [3] Queue test\n"
			        << "\t [4] Graph test\n"
			        << "\n"
			        << "\t [0] Exit"
			        << std::endl;

	    // Input menu data;
	    std::cout	<< "Select: ";
		
	    while (true)
	    {
		    std::cin.clear();
		    std::cin.sync();

		    std::cin >> sel;

		    if (std::cin.good() && sel >= 0  && sel <= 4)
		    {
			    system("cls");
			    break;
		    }

		    std::cout	<< "Invalid choice, try again:";
	    }

	    // Menu cases;
	    switch (sel)
	    {
		    case 1:
			    testLinkedList();
			    break;
		    case 2:
                testStack();
			    break;
		    case 3:
                testQueue();
			    break;
		    case 4:
                testGraph();
			    break;
		    case 0:
			    // End work;
			    std::cout << std::endl
					      << "\t\t\t    Press any key to exit."
					      << std::endl;

			    _getch();
			    system("cls");

			    return 0;
	    }

	    // Hold result;
	    std::cout << std::endl
			      << "\t\t    Press any key for return to the menu."
			      << std::endl;

	    _getch();
	    system("cls");
    }

    _getch();
	return 1;
}

void testLinkedList(void)
{
    sim::ds::LinkedList<int> lst;

    for (int i = 0; i < 10; i++)
    {
        lst.insert(i);
    }

    for (int i = 0; i < 10; i++)
    {
        lst.insert(i*i, i * 2);
    }

    for (int i = 0; i < lst.size(); i++)
    {
        std::cout << lst[i] << " ";
    }

    lst.remove(0);
    lst.remove(9);
    lst.remove(lst.size() - 1);

    std::cout << std::endl;
    for (int i = 0; i < lst.size(); i++)
    {
        std::cout << lst[i] << " ";
    }

    lst.clear();

    for (int i = 0; i < 10; i++)
    {
        lst.insert(i*i, i * 2);
    }

    std::cout << std::endl;
    for (int i = 0; i < lst.size(); i++)
    {
        std::cout << lst[i] << " ";
    }

    std::cout << std::endl;
    std::cout << lst.get(7) << " ";
    std::cout << lst[8] << " ";
    std::cout << lst.find(49) << " ";
    std::cout << lst.tellg() << " ";

    std::cout << std::endl;
    while (true)
    {
        std::cout << lst.getCurrent() << " ";

        if (!lst.next())
        {
            break;
        }
    }

    std::cout << std::endl;
    lst.seekg(5);
    while (true)
    {
        std::cout << lst.getCurrent() << " ";

        if (!lst.next())
        {
            break;
        }
    }

    std::cout << std::endl;
    lst.seekg(0);
    lst.swap(0,9);
    lst.swap(4,6);
    while (true)
    {
        std::cout << lst.getCurrent() << " ";

        if (!lst.next())
        {
            break;
        }
    }
}

void testStack(void)
{
    sim::ds::Stack<int> st;

    for (int i = 0; i < 10; i++)
    {
        st.push(i);
    }

    while (st.size())
    {
        std::cout << st.top() << " ";
        st.pop();
    }

    for (int i = 0; i < 10; i++)
    {
        st.push(i);
    }

    std::cout << std::endl;
    st.clear();
    for (int i = 0; i < 10; i++)
    {
        st.push(i);
    }

    while (st.size())
    {
        std::cout << st.top() << " ";
        st.pop();
    }
}

void testQueue(void)
{
    sim::ds::Queue<int> qu;

    for (int i = 0; i < 10; i++)
    {
        qu.enqueue(i);
    }
    
    while (qu.size())
    {
        std::cout << qu.get();
        qu.dequeue();
    }
}

void testGraph(void)
{
    sim::ds::Graph<float> gr(false);

    for (int i = 0; i < 10; i++)
    {
        gr.addNode(i);
    }

    std::cout << gr.nodesCount() << "\n";

    for (int i = 0; i < gr.nodesCount(); i++)
    {
        std::cout << gr.getNodeByKey(i) << " - "
                  << gr.getNodeByValue(gr.getNodeByKey(i)) << "\n";
    }

    std::cout << std::endl;
    gr.editNode(0, 9);
    
    for (int i = 0; i < gr.nodesCount(); i++)
    {
        std::cout << gr.getNodeByKey(i) << " - "
                  << gr.getNodeByValue(gr.getNodeByKey(i)) << "\n";
    }

    int count = 0;
    int* fndd = gr.getAllNodesByValue(0, count);

    for (int i = 0; i < count; i++)
    {
        std::cout << fndd[i] << " - ";
    }
    std::cout << count << std::endl;
    delete[] fndd;


    for (int i = 1; i < 9; i++)
    {
        gr.addEdge(i, 0, 1);
        //gr.addEdge(i, i + 1, 1);
    }

    //gr.deleteEdge(5, 0);
    //gr.deleteEdge(3, 0);

    std::cout << std::endl;
    for (int i = 0; i < 10; i++)
    {
        int* nbr = gr.neighbors(i, count);

        for (int j = 0; j < count; j++)
        {
            std::cout << nbr[j] << " ";
        }
        std::cout << std::endl;
        delete[] nbr;
    }
}
#include <bits/stdc++.h>

using namespace std;

// time complexity of this heapify method is o(n);

void heapify(int a[], int n, int i)
{
    int largest = i;                 // this is starting node position for heap
    int l = 2 * i;                   // left child node of main node
    int r = 2 * i + 1;               // right child node of main node
    if (l <= n && a[l] > a[largest]) // checking heap condition
    {
        largest = l; // if child node greater than parent changing position of main node
    }
    if (r <= n && a[r] > a[largest]) // same as above but for right child node
    {
        largest = r; // assigning value if condition fulfills
    }

    if (largest != i) // if changed node value not equal to previous parent node value
    {
        //    swap(a, i, largest);  //swaping elements of parent and child node
        heapify(a, n, largest); // calling heapify function again for next child nodes
    }
}

void buildheap(int a[], int n)
{
    for (int i = n / 2; i > 0; i--) // checking in reverse for heap condition for every node using above heapify method
    {
        heapify(a, n, i);
    }
}

void heapSort(int a[], int n)
{
    for (int i = n; i > 0; i--)
    {
        //    swap(a, 1, i);  //this will swap first parent element of heap and last element of heap
        heapify(a, i - 1, 1); // this will call heapify method to convert remaining array(size i-1) to a heap
    }
}

// important algorithm for placements
int main()
{
}
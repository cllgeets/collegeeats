#include <bits/stdc++.h>

using namespace std;

void insert(int a[], int n, int value)
{
    n = n + 1;
    a[n] = value;
    int i = n;
    while (n > 1)
    {
        int parent = i / 2;
        if (a[i] > a[parent])
        {
            int store = a[parent];
            a[parent] = a[i];
            a[i] = store;
            i = parent;
        }
        else
        {
            return;
        }
    }
}

void del(int a[], int n, int value)
{
    a[0] = a[n - 1];
    n = n - 1;
    int i = 1;
    while (i < n)
    {
        int left = a[2 * i - 1];
        int right = a[2 * i];
        int large = left > right ? 2 * i -1 : 2 * i;

        if (a[i-1] < a[large])
        {
            int store = a[large];
            a[large] = a[i-1];
            a[i - 1] = store;

            i = large;
        }
        else
        {
            return;
        }
    }
}

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

int main()
{
    int n;
    cout << "N = ";
    cin >> n;
    int id[n];
    int arr[n];
    int pro[n];
    int prio[n];

    for (int i = 0; i < n; i++)
    {
        cout << "id = ";
        cin >> id[i];
        cout << "arrival time = ";
        cin >> arr[i];
        cout << "processing time = ";
        cin >> pro[i];
        cout << "priority = ";
        cin >> prio[i];
    }

    heapSort(prio, n);

    for (int i = 0; i < n; i++)
    {
        cout << prio[i] << " ";
    }
}
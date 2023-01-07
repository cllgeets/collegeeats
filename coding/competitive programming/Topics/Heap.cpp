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

int main()
{
    int a[5] = {50, 30, 10, 20, 15};
    int value;
    cin >> value;

    // insert(a, 5, value);
    del(a, 5, value);

    for (int i = 0; i < 4; i++)
    {
        cout << a[i] << "  ";
    }
}
#include <bits/stdc++.h>

using namespace std;

void swapmax(int arru[], int n, int x)
{
    int newx = x;
    for (int i = newx; i > 0; i--)
    {
        int array = arru[i];
        arru[i] = arru[i - 1];
        arru[i - 1] = array;
    }
    
}

int main()
{
    int n;
    cin >> n;

    int arr[n];
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
    int maxm = INT16_MIN;
    int minm = INT16_MAX;
    int a = 0;
    int b = 0;

    for (int i = 0; i < n; i++)
    {
        if (arr[i] > maxm)
        {
            a = i;
        }
        maxm = max(maxm, arr[i]);
       
    }

    swapmax(arr, n, a);

    for (int i = 0; i < n; i++)
    {
         if (arr[i] <= minm)
        {
            b = i;
        }
        minm = min(minm, arr[i]);
    }

    cout<<a+n-b-1;
    
}
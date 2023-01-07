#include <bits/stdc++.h>

using namespace std;

void sorting(int arr[], int count[], int n)
{
    for (int counter = 0; counter < n - 1; counter++)
    {
        for (int i = 0; i < n - counter - 1; i++) // This loop is working on unsorted array. The length of loop decrease every time one element sorting  complete.
        {
            if (arr[i] > arr[i + 1])
            {
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;

                int temp2 = count[i + 1];
                count[i + 1] = count[i];
                count[i] = temp2;
            }
        }
    }
}

int sum(int arr[], int n, int k)
{
    int suming = 0;
    for (int i = 0; i < n - k; i++)
    {
        suming += arr[i];
    }
    return suming;
}

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        int arr[n];
        int count[n];
        for (int i = 0; i < n; i++)
        {
            cin >> arr[i];
            count[i] = i + 1;
        }

        sorting(arr, count, n);
        for (int i = n - 1; i >= 0; i--)
        {
            int storesum = sum(arr, n, n - i);
            if (storesum < arr[i])
            {
                cout << count[i]<<endl;
                break;
            }
        }
    }
}
#include <bits/stdc++.h>

using namespace std;
int n;
int index = n;
int sum = 0;

int minm(int arr[], int size)
{
    int minm = 0;
    for (int i = 0; i < size; i++)
    {
        if (arr[i] < minm)
        {
            minm = arr[i];
            index = i;
        }
    }
    sum += minm;
    return minm;
}

int check(int arr[], int size)
{

    for (int j = 0; j < size; j++)
    {
        arr[j] = arr[j] - minm(arr, size);
    }

    if (index > 0 && index < n)
    {

        int arr1[index];
        int arr2[n - index - 1];
        for (int i = 0; i < index; i++)
        {
            arr1[i] = arr[i];
        }
        for (int i = 0; i < n - index - 1; i++)
        {
            arr1[i] = arr[i + index + 1];
        }
        check(arr1, index);
        check(arr2, n - index - 1);
    }
    cout << sum;
}

int main()
{
    cin >> n;

    int arr[n];
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    check(arr, n);
}
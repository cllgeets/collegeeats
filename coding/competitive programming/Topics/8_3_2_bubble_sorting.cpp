#include <iostream>

using namespace std;

// In this type of sorting we swap elements if second item is > first item. and then we apply this on every unsorted array.
int main()
{
    int n;
    cin >> n;

    int arr[n];

    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    for (int counter = 0; counter < n - 1; counter++)
    {
        for (int i = 0; i < n - counter - 1; i++) // This loop is working on unsorted array. The length of loop decrease every time one element sorting  complete.
        {
            if (arr[i] > arr[i + 1])
            {
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
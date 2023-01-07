#include <bits/stdc++.h>

using namespace std;

void sortArray(int arr[], int n)
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
            }
        }
    }
}

int main()
{
    int n;
    cin >> n;
    int a[n];

    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    sortArray(a, n);

    int min_coin = INT16_MAX;
    for (int i = 0; i < n; i++)
    {
        int a_sum1 = 0;
        int a_sum2 = 0;
        int new_coin = n - i;
        for (int j = 0; j < i; j++)
        {
            a_sum1 += a[j];
        }
        for (int k = n - 1; k >= i; k--)
        {
            a_sum2 += a[k];
        }

        if ((a_sum2 > a_sum1))
        {
            min_coin = min(min_coin, new_coin);
        }
    }
    cout << min_coin;
}
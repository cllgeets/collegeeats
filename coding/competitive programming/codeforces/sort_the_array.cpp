#include <bits/stdc++.h>

using namespace std;

bool check(int arr[], int arr1[], int n)
{
    int ch = 1;
    for (int i = 0; i < n; i++)
    {
        if (arr[i] != arr1[i])
        {
            ch = 0;
            break;
        }
        else
        {
            ch = 1;
        }
    }
    return ch;
}

void reverse(int arr[], int n, int a, int b)
{
    for (int t = 0; t < (b - a + 1) / 2; t++)
    {
        int first = arr[a + t];
        arr[a + t] = arr[b - t];
        arr[b - t] = first;
    }
}

int main()
{
    int n;
    cin >> n;
    int arr[n];
    int arr1[n];
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
        arr1[i] = arr[i];
    }

    sort(arr1, arr1 + n);

    int arr3[n];
    int out = 0;
    for (int i = 0; i < n; i++)
    {
        int trick = 0;
        for (int j = i ; j < n; j++)
        {
            for (int k = 0; k < n; k++)
            {
                arr3[k] = arr[k];
            }

            reverse(arr3, n, i, j);

            int checking = check(arr3, arr1, n);
            if (checking == 1)
            {
                out = 1;
                cout <<"yes"<<endl<< i+1 << " " << j+1;
                trick = 1;
                break;
            }
        }
        if (trick == 1)
        {
            break;
        }
    }
    if (out == 0)
    {
        cout<<"no";
    }
    
}
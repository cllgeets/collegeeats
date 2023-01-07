#include <bits/stdc++.h>

using namespace std;

void printArr(int arr[], int n)
{
    for (int j = 0; j < n; j++)
    {
        cout << arr[j] << "  ";
    }
    cout << endl;
}

void per(int arr[], int n, int i)
{
    
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

    per(arr, n, 0);
}
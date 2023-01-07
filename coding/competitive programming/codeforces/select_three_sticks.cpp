#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;

    while (t--)
    {
        int n;
        cin >> n;

        int arr[n];
        for (int i = 0; i < n; i++)
        {
            cin >> arr[i];
        }

        sort(arr, arr + n);

        int a;
        a = arr[2] - arr[0];
        for (int i = 0; i < n - 2; i++)
        {
            int b;
            b = arr[i + 2] - arr[i];

            a = min(a, b);
        }

        cout << a<<endl;
    }
}
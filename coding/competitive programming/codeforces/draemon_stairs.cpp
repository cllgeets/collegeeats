#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n, m;
    cin >> n >> m;

    int minm = INT16_MAX;

    int arr[n / 2 + 1];
    for (int i = 0; i < n / 2 + 1; i++)
        arr[i] = n - i;

    for (int i = 0; i < n / 2 + 1; i++)
    {
        for (int j = 0; j < n / m; j++)
        {
            if (arr[i] == (j + 1) * m)
            {
                minm = min(minm, arr[i]);
                break;
            }
        }
    }

    if (minm == INT16_MAX)
    {
        cout << "-1";
    }
    else
    {
        cout << minm;
    }
}

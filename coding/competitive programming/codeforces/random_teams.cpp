#include <bits/stdc++.h>

using namespace std;

int main()
{

    int n, m;
    cin >> n >> m;

    int no = n + 1 - m;
    int max = (no * (no - 1)) / 2;

    int arr[m];

    if (n % m == 0)
    {
        for (int i = 0; i < m; i++)
        {
            arr[i] = n / m;
        }
    }
    else
    {
        int r = n % m;
        int num = (n - r) / (m-1);

        arr[0] = r;
        for (int i = 1; i < m; i++)
        {
            arr[i] = num;
        }
    }

    int min = 0;
    for (int i = 0; i < m; i++)
    {
        int no = (arr[i] * (arr[i] - 1)) / 2;
        min += no;
    }
    cout << min << " " << max;
}
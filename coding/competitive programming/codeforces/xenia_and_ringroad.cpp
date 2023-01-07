#include <bits/stdc++.h>

using namespace std;

int main()
{
    long long int n, m;
    cin >> n >> m;

    long long int a[m];
    for (int i = 0; i < m; i++)
        cin >> a[i];

    long long time = 0;
    time += a[0] - 1;
    for (int i = 1; i < m; i++)
    {
        if (a[i] >= a[i - 1])
        {
            time += a[i] - a[i - 1];
        }
        else
        {
            time += n - abs(a[i] - a[i - 1]);
        }
    }

    cout << time;
}
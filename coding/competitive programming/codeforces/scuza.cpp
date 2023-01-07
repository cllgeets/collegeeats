#include <bits/stdc++.h>

using namespace std;

int main()
{
    long long t;
    cin >> t;
    while (t--)
    {
        long long n, q;
        cin >> n >> q;

        long long a[n];
        long long k[q];

        for (int i = 0; i < n; i++)
        {
            cin >> a[i];
        }
        for (int i = 0; i < q; i++)
        {
            cin >> k[i];
        }

        for (int i = 0; i < q; i++)
        {
            long long height = 0;
            for (int j = 0; j < n; j++)
            {
                if (k[i] >= a[j])
                {
                    height += a[j];
                }
                else
                {
                    break;
                }
            }
            cout << height << " ";
        }
        cout << endl;
    }
}
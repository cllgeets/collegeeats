#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n, k;
        cin >> n >> k;

        int a[n];

        for (int i = 0; i < n; i++)
        {
            cin >> a[i];
        }

        int ans = 0;
        for (int i = 0; i < k; i++)
        {

            int maxm = INT16_MIN;
            int p = 0;
            for (int j = 0; j < k; j++)
            {
                maxm = max(maxm, a[j]);
                if (maxm <= a[j])
                {
                    p = j;
                }
            }

            int q = 0;
            int minm = INT16_MAX;
            for (int l = k; l < n; l++)
            {
                minm = min(minm, a[l]);
                if (minm >= a[l])
                {
                    q = l;
                }
            }
            if (maxm >= minm)
            {
                int ind = a[q];
                a[q] = a[p];
                a[p] = ind;
                ans += 1;
            }
        }
        cout << ans <<endl;
    }
}
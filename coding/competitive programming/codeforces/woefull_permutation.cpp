#include <bits/stdc++.h>

using namespace std;

int gcd(int a, int b)
{
    if (b == 0)
        return a;
    return gcd(b, a % b);
}

int lcm(int a, int b)
{
    return (a / gcd(a, b)) * b;
}

int main()
{

    int t;
    cin >> t;

    while (t--)
    {
        int n;
        cin >> n;

        int a[n];

        for (int i = 0; i < n; i++)
        {
            a[i] = i + 1;
        }

        for (int i = 0; i < n; i++)
        {
            int maxm = INT16_MIN;
            int l = 0;
            for (int j = 0; j < n; j++)
            {
                maxm = max(maxm, lcm(a[j], i + 1));
                if (maxm <= lcm(a[j], i + 1))
                {
                    l = j;
                }
            }
            int inde = a[l];
            a[l] = a[i];
            a[i] = inde;
        }

        for (int i = 0; i < n; i++)
        {
            cout << a[i] << " ";
        }
    }
}
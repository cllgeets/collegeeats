#include <bits/stdc++.h>

using namespace std;

int gcd(int a, int b)
{
    int result = min(a, b);
    while (result > 0)
    {
        if (a % result == 0 && b % result == 0)
        {
            break;
        }
        result--;
    }
    return result;
}
int main()
{
    int n, l, r;
    cin >> n >> l >> r;

    int a[n];

    for (int i = 0; i < n; i++)
    {
        a[i] = INT16_MIN;
    }

    int start = l;
    a[0] = gcd(0,l);
    for (int i = 1  < n+1; i++;)
    {
        for (int j = start+1; j < r; j++)
        {
                for (int k = 0; k < i; k++)
                {
                    if (gcd(i, start) != a[k])
                    {
                        a[i] = gcd(i, start);
                        start++;
                    }
                }
            
        }
    }

    for (int i = 0; i < n; i++)
    {
        cout << a[i] << "   ";
    }
}
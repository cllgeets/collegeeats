#include <bits/stdc++.h>

using namespace std;

int main()
{
    int p;
    cin >> p;
    while (p--)
    {
        int n;
        cin >> n;
        int t = n % 3;

        int check = INT16_MAX;
        for (int i = 0; i <= n / 2; i++)
        {
            int x = 0;
            for (int j = 0; j <= n / 2; j++)
            {
                if ((2 * i + 3 * j) == n)
                {
                    check = i + j;
                    x = 1;
                    break;
                }
            }
            if (x == 1)
            {
                break;
            }
        }

        if (t == 0)
        {
            check = min(n / 3, check);
            cout << check << endl;
        }
        else if (t == 1)
        {
            check = min(check, (((n - 1) / 3) + 2));
            cout << check << endl;
        }
        else if (t == 2)
        {
            check = min(check, (((n - 2) / 2) + 1));
            cout << check << endl;
        }
    }
}
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
        char s[n];
        int check[n];

        for (int i = 0; i < n; i++)
        {
            cin >> s[i];
            check[i] = s[i];
        }

        int cost = 0;
        for (int i = 0; i < n; i++)
        {
            if (check[i] == '0')
            {
                for (int k = i + 1; k <= n; k + i)
                {
                    if (s[k - 1] == '0')
                    {
                        s[k - 1] = '1';
                        cost += i + 1;
                    }
                    else if (check[k - 1] == '0')
                        continue;
                    else
                        break;
                }
            }
        }
        cout << cost << endl;
    }
}
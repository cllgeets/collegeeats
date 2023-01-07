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

        int a[n];
        for (int i = 0; i < n; i++)
        {
            cin >> a[i];
        }

        int num = 0;

        for (int k = 0; k < n; k++)
        {
            if (a[(n - 1 - k)] != 0)
            {
                int last = a[n - 1 - k];

                int store = 0;

                for (int i = 0; i < n - k - 1; i++)
                {
                    if (a[i] == last)
                    {
                        if (a[i] == store)
                        {
                            a[i] = 0;
                        }
                        else
                        {
                            store = a[n - 1 - k];
                            a[i] = 0;
                            a[n - 1 - k] = 0;
                            num += 1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n - 1; i++)
        {
            if (a[n - 1] < a[i])
            {
                a[i] = 0;
                num += 1;
            }
        }
        for (int i = 0; i < n; i++)
        {
            if (a[i] > a[i + 1])
            {
                a[i] = 0;
                num += 1;
            }
        }

        cout << num << endl;
    }
}
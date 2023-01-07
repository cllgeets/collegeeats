#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n, m;
    cin >> n >> m;

    int z = 0;
    for (int i = 0; i < n; i++)
    {
        if (i % 2 == 0)
        {
            for (int j = 0; j < m; j++)
            {
                cout << "#";
            }
            cout << endl;
        }
        else
        {
            if (z == 0)
            {
                for (int j = 0; j < m - 1; j++)
                {
                    cout << ".";
                }
                cout << "#";
                z = 1;
                cout << endl;
            }
            else
            {
                cout << "#";
                for (int j = 0; j < m - 1; j++)
                {
                    cout << ".";
                }
                z = 0;
                cout<<endl;
            }
        }
    }
}
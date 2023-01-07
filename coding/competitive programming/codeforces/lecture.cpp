#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n, m;
    cin >> n >> m;

    string a1[m], a2[m];

    for (int i = 0; i < m; i++)
    {
        cin >> a1[i] >> a2[i];
    }

    string lecture[n];
    for (int i = 0; i < n; i++)
    {
        cin >> lecture[i];
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (lecture[i] == a1[j])
            {
                int leng1 = a1[j].length();
                int leng2 = a2[j].length();
                if (leng1 > leng2)
                {
                    cout << a2[j] << " ";
                }
                else if (leng1 <= leng2)
                {
                    cout << a1[j] << " ";
                }
            }
        }
    }
}
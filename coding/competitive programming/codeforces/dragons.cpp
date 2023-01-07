#include <bits/stdc++.h>

using namespace std;

int main()
{
    int s, n;
    cin >> s >> n;
    pair<int, int> p[5000];

    for (int i = 0; i < n; i++)
    {
        cin >> p[i].first >> p[i].second;
    }

    sort(p, p + n);

    for (int i = 0; i < n; i++)
    {
        if (s > p[i].first)
        {
            s += p[i].second;
        }
        else
        {
            cout << "NO";
            return 0;
        }
    }
    cout << "YES";
}
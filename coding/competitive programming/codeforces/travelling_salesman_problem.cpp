#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;
    int maxx, minx, maxy, miny;
    while (t--)
    {
        int n;
        cin >> n;
        int x;
        int y;
        maxx = minx = maxy = miny = 0;
        for (int i = 0; i < n; i++)
        {
            cin >> x;
            cin >> y;
            maxx = max(maxx, x);
            maxy = max(maxy, y);
            minx = min(minx, x);
            miny = min(miny, y);
        }

        cout << (maxx - minx) * 2 + (maxy - miny) * 2 << endl;
    }
}
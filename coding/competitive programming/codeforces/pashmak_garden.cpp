#include <bits/stdc++.h>

using namespace std;

int main()
{
    int x1, y1, x2, y2, x3, y3, x4, y4;
    cin >> x1 >> y1 >> x2 >> y2;

    if (x1 == x2)
    {
        int length = abs(y2 - y1);
        int maxy = y2 > y1 ? y2 : y1;

        x4 = x3 = x1 + length;
        y3 = maxy - length;
        y4 = maxy;

        cout << x3 << " " << y3 << " " << x4 << " " << y4;
    }
    else if (y1 == y2)
    {
        int length = abs(x2 - x1);
        int maxx = x2 > x1 ? x2 : x1;

        y4 = y3 = y1 + length;
        x4 = maxx;
        x3 = maxx - length;

        cout << x3 << " " << y3 << " " << x4 << " " << y4;
    }
    else if (abs(y2 - y1) == abs(x2 - x1))
    {
        int length = abs(y2 - y1);
        int maxx = x2 > x1 ? x2 : x1;
        int maxy = maxx == x2 ? y2 : y1;
        int minx = x2 > x1 ? x1 : x2;
        int miny = minx == x2 ? y2 : y1;
        
        if (maxy > miny)
        {
            x3 = maxx - length;
            x4 = maxx;
            y3 = maxy;
            y4 = maxy - length;

            cout << x3 << " " << y3 << " " << x4 << " " << y4;
        }
        else
        {
            x3 = maxx;
            x4 = maxx - length;
            y3 = maxy + length;
            y4 = maxy;

            cout << x3 << " " << y3 << " " << x4 << " " << y4;
        }
    }
    else
    {
        cout << "-1";
    }
}
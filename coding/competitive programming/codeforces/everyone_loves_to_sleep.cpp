#include <bits/stdc++.h>

using namespace std;

int convertToMinute(int hour, int min)
{
    int minute = hour * 60 + min;
    return minute;
}

int main()
{
    int t;
    cin>>t;
    while (t--)
    {
        int n, H, M;
        cin >> n >> H >> M;

        int minmin = INT16_MAX;
        for (int i = 0; i < n; i++)
        {
            int h;
            int m;
            cin >> h >> m;

            if (convertToMinute(h, m) >= convertToMinute(H, M))
            {
                minmin = min(minmin, abs(convertToMinute(h, m) - convertToMinute(H, M)));
            }
            else if (convertToMinute(h, m) < convertToMinute(H, M))
            {
                minmin = min(minmin, abs(convertToMinute(h, m) + 24 * 60 - convertToMinute(H, M)));
            }
        }
        int realmin = (minmin) % 60;
        int realhour = abs(minmin - realmin) / 60;

        cout << realhour;
        cout << " ";
        cout << realmin<<endl;
    }
}
#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int a, b, c;
        cin >> a >> b >> c;

        int s1 = abs(a - 1);
        int s2 = abs(c - b) + abs(c - 1);

        if (s1 < s2)
            cout << '1';
        else if (s2 < s1)
            cout << "2";
        else
            cout << "3";

        cout << endl;
    }
}
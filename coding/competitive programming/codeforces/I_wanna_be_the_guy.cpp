#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> v;

    int x;
    cin >> x;

    for (int i = 0; i < x; i++)
    {
        int a;
        cin >> a;
        v.push_back(a);
    }

    int y;
    cin >> y;
    for (int i = 0; i < y; i++)
    {
        int b = 0;
        cin >> b;
        v.push_back(b);
    }

    if (v.size() == n)
    {
        cout << "I become the guy.";
    }
    else
    {
        cout << "Oh, my keyboard!";
    }
}
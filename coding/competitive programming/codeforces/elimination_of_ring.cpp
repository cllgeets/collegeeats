#include <bits/stdc++.h>

using namespace std;
set<int> s;
int main()
{
    int t;
    cin >> t;
    int n, a;
    while (t--)
    {
        s.clear();
        cin >> n;
        for (int i = 0; i < n; i++)
            cin >> a, s.insert(a);
        cout << (s.size() == 2 ? n / 2 + 1 : n) << endl;
    } 
}
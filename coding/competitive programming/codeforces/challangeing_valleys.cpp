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
        vector<int> v;
        for (int i = 0; i < n; i++)
        {
            int a;
            cin >> a;
            if (i == 0 || a != v.back())
            {
                v.push_back(a);
            }
        }

        int flag = 0;
        for (int i = 0; i < v.size(); i++)
        {
            if ((i == 0 || v[i - 1] > v[i]) && (i == v.size() - 1 || v[i] < v[i + 1]))
            {
                flag++;
            }
        }

        if (flag == 1)
        {
            cout << "YES" << endl;
        }
        else
        {
            cout << "NO" << endl;
        }
    }
}
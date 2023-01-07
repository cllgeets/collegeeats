#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        long long n, k;
        cin >> n >> k;
        long long arr[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = i + 1;
        }

        vector<pair<long long, long long>> vec;
        int check = 0;
        for (int i = 0; i < n - 1;)
        {
            if (((arr[i] + k) * arr[i + 1]) % 4 == 0)
            {
                vec.push_back(make_pair(arr[i], arr[i + 1]));
                check = 1;
            }
            else if (((arr[i + 1] + k) * arr[i]) % 4 == 0)
            {
                vec.push_back(make_pair(arr[i + 1], arr[i]));
                check = 1;
            }
            else
                vec.push_back(make_pair(arr[i], arr[i + 1]));

            i += 2;
        }
        if (check == 1)
        {
            cout << "YES" << endl;
            for (int i = 0; i < n / 2; i++)
            {
                cout << vec[i].first << " ";
                cout << vec[i].second << endl;
            }
        }
        else
            cout << "NO" << endl;
    }
}
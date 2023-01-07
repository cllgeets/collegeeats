#include <bits/stdc++.h>

using namespace std;

bool gcd(int a, int b)
{
    int result = min(a, b);

    int count = 0;
    while (result > 1)
    {
        if ((a % result == 0) && (b % result == 0) && (result > 1))
        {
            return false;
            break;
        }
        result--;
    }
    return true;
}
int main()
{

    int t;
    cin >> t;
    while (t--)
    {
        int n;
        cin >> n;
        int arr[n];
        for (int i = 0; i < n; i++)
            cin >> arr[i];

        int ct = 0;
        vector<int> v;
        for (int i = n - 1; i >= 0; i--)
        {
            if (arr[i] != 1)

            {
                int count = 0;
                for (int j = i - 1; j >= 0; j--)
                {
                    if (gcd(arr[i], arr[j]) == true)
                    {
                        count = 1;
                        // cout << i + 1 << " " << j + 1 << endl;
                        // cout << i + j + 2 << endl;
                        v.push_back(i + j + 2);
                    }
                }
            }
            else
            {
                // cout << 2 * (i + 1) << endl;
                v.push_back(2 *( i + 1));
            }
        }

        int maxm = INT16_MIN;
        for (int i = 0; i < v.size(); i++)
        {
            maxm = max(maxm, v.at(i));
        }
        if (v.size() > 0)
        {
            cout << maxm<<endl;
        }
        else
        {
            cout << "-1"<<endl;
        }
    }
}
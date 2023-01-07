#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;

    long long arr[n];

    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    for (int j = 0; j < n; j++)
    {
        int check = 1;

        for (int i = 1; i <= arr[j] / 2; i++)
        {
            if (arr[j] % i == 0)
            {
                check++;
            }+
        }
        if (check == 3)
        {
            cout << "YES";
        }
        else
        {
            cout << "NO";
        }
        cout << endl;
    }
}
#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int arr[26];

        for (int i = 0; i < 26; i++)
            arr[i] = 0;

        for (int i = 0; i < 4; i++)
        {
            char a;
            cin >> a;

            int a1 = a;
            arr[a1 - 97] = 1;
        }

        int count = 0;
        for (int i = 0; i < 26; i++)
        {
            if (arr[i] == 1)
            {
                count++;
            }
        }
        cout << count - 1 << endl;
    }
}
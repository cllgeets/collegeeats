#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;
    int p = 0;
    string arr[t];
    for (int k = 0; k < t; k++)
    {

        int a;
        int b;

        cin >> a >> b;

        string arr1;
        string arr2;
        cin>>arr1;
        cin>>arr2;

        int j = 0;

        for (int i = 0; i < a; i++)
        {
            if (arr2[j] == arr1[i])
            {
                j++;
            }
        }
        if (j == b)
        {
            arr[p] = "YES";
            p++;
        }
        if (j != b)
        {
            arr[p] = "NO";
            p++;
        }
    }
    for (int i = 0; i < t; i++)
    {
        cout << arr[i] << endl;
    }
}
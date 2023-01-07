#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;

    int a[n];
    int b[n];

    for (int i = 0; i < n; i++)
    {
        b[i] = i;
        a[i] = i;
    }

    for (int i = 1; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            int num = j + b[i];
            int res = (sqrt(num));
            if ((res * (sqrt(num))) == (num))
            {
                a[j] = b[i];
                b[i] = j;
                break;
            }
        }
    }
    cout << endl;

    for (int i = 0; i < n; i++)
    {
        cout << a[i] << "   ";
    }
}

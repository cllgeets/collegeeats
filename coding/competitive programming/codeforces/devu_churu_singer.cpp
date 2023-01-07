#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n, d;
    cin >> n >> d;

    int a[n];
    int sum = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
        sum += a[i]; 
    }

    int first = ((n - 1) * 10 + sum);
    if (first > d)
    {
        cout << "-1";
    }
    else
    {
        cout << ((n - 1) * 10 + ((d - first)/ 5) * 5)/5;
    }
}
#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    int m;
    cin >> n >> m;

    int a1[n];
    int a2[m];

    for (int i = 0; i < m; i++)
    {
        cin >> a2[i];
    }

    sort(a2, a2 + m);

    int minm = INT16_MAX;

    for (int i = 0; i < m-n+1; i++)
    {
        int k =0;
        for (int j = i; j < n+i; j++)
        {
            a1[k] = a2[j];
            k++;
        }
        
        minm = min(minm, (a1[n-1]-a1[0]));
    }

    cout<<minm;
}
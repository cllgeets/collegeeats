#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin>>t;

    while (t--)
    {
    
    int n;
    cin>>n;
    int x[n];
    int t[n];
    int maxm = INT16_MIN;
    for (int i = 0; i < n; i++)
    {
        cin >> x[i];
        maxm = max(maxm, x[i]);
    }
    int sumt = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> t[i];
        sumt += t[i];
    }

    int arr[maxm+1];
    int minm =INT16_MAX;
    for (int i = 0; i <= maxm; i++)
    {
        int maxmm = INT16_MIN;
        for (int k = 0; k < n; k++)
        {
            int num = abs(i - x[k]) + t[k];
            maxmm = max(maxmm, num);
        }               
        arr[i] = maxmm;
        minm = min(minm, arr[i]);
    }
    for (int i = 0; i <= maxm; i++)
    {
        if (minm == arr[i])
        {
            cout<<i<<endl;
            break;
        }
    }
    }
}
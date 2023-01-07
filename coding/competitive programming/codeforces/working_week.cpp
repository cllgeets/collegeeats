#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin>>t;
    while(t--){
 int n;
    cin >> n;

    int maxm = INT16_MIN;
    for (int i = 2; i <= n - 4; i++)
    {
            int l1 =1;
            int l2 = abs(n - i - 3);
            int l3 = abs(i - 1);

            int minm = 0;
            minm = min(abs(l1 - l2), abs(l2 - l3));
            minm = min(minm, abs(l1 - l3));

            maxm = max(maxm, minm);
    }
    cout<<maxm<<endl;
    }
   
}
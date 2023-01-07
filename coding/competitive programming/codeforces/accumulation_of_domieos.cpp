#include <bits/stdc++.h>

using namespace std;

int main()
{
    long long n, m;
    cin >> n >> m;

    if (m > 1)
    {
        cout << (m - 1) * n<<endl;
    }else{
        cout<<n-1<<endl;
    }
}
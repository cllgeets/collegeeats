#include <bits/stdc++.h>

using namespace std;

int main()
{
    int a, b, c;
    cin >> a >> b >> c;

    int maxm = INT_MIN;

    maxm = max(maxm, a + b + c);
    maxm = max(maxm, a * (b + c));
    maxm = max(maxm, (a + b) * c);
    maxm = max(maxm, a * b * c);
    maxm = max(maxm, a * b + c);
    maxm = max(maxm, a + b * c);

    cout << maxm;
}
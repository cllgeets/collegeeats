#include <bits/stdc++.h>

using namespace std;

const int N = 1e5 + 10;

int dp[N];

//Top-Down approach
int fib(int n)
{
    if (n == 0)
        return 0;
    if (n == 1)
        return 1;
    if (dp[n] != -1)
        return dp[n];   //memoization

    return dp[n] = fib(n - 1) + fib(n - 2);
} 

int main()
{
    memset(dp, -1, sizeof(dp)); // used to initialise array with only -1.
    int n;
    cin >> n;
}
#include <bits/stdc++.h>

using namespace std;

void store(int n)
{
}

int main()
{
    int x;

    vector<int> v;

    int maxm = INT16_MIN;
    int minm = INT16_MAX;
    int i;
    int j = 0;
    int k = 0;

    while (x != 0)
    {
        int r;
        r = x % 10;
        v.push_back(r);
        i++;
        if (r % 2 == 0)
        {
            if (r > maxm)
            {
                j = i;
                maxm = r;
            }
            if (r < minm)
            {
                k = i;
                minm = r;
            }
        }
        x = x / 10;
    }
}
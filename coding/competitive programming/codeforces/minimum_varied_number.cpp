#include <bits/stdc++.h>

using namespace std;

int convert(int arr[], int s)
{
    int finalNumber = 0;
    for (int i = 0; i < s; i++)
    {
        int num = arr[i];
        if (num != 0)
        {
            while (num > 0)
            {
                finalNumber *= 10;
                num /= 10;
            }
            finalNumber += arr[i];
        }
        else
        {
            finalNumber *= 10;
        }
    }
    return finalNumber;
}
int main()
{
    int s;
    cin >> s;

    int t = s;
    int a[s];
    for (int i = 0; i < s; i++)
    {
        a[i] = 1;
    }

    int minm = convert(a, s + 3);
    for (int i = 0; i < t; i++)
    {
        if (minm >= convert(a, t))
        {
            minm = min(minm, convert(a, t));
            if ((a[t - 1] <= 9) && (a[t - 2] <= 9))
            {
                a[t - 2] += a[t - 1];
                t--;
            }
        }
    }

    cout << minm;
}
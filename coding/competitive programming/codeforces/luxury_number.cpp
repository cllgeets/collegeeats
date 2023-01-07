#include <bits/stdc++.h>

using namespace std;

int main()
{
    long long l, r;
    cin >> l >> r;

    long long min;
    for (int i = l; i <= r; i++)
    {
        long long sqr = sqrt(i);
        if (pow(sqr, 2) == i)
        {
            min = sqr;
            break;
        }
    }
    cout<<min<<endl;

    long long max;
    for (int i = r; i >= l; i--)
    {
        long long sqr2 = sqrt(i);
        if (pow(sqr2, 2) == i)
        {
            max = sqr2;
            break;
        }
    }

    cout<<max<<endl;

    long long sum = 0;
    for (int i = min; i < max; i++)
    {
        long long first = pow(i, 2);
        long long second = pow(i + 1, 2) - 1;

        long long number = second / i - ((first / i) - 1);
        sum += number;
    }

    long long start = pow(max, 2);
    for (int i = start; i <= r; i++)
    {
        long long tumber = sqrt(i);
        if (i % tumber == 0)
        {
            sum++;
        }
    }

    long long end = pow(min, 2);
    for (int i = l; i < end; i++)
    {
        long long tumber2 = sqrt(i);
        if (i % tumber2 == 0)
        {
            sum++;
        }
    }

    cout << sum;
}
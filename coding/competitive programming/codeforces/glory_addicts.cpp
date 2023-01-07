#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;
    int a[n];
    int b[n];

    int ca = 0;
    int cb = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
        if (a[i] == 0)
            ca++;
        else
            cb++;
    }
    for (int i = 0; i < n; i++)
    {
        cin >> b[i];
    }

    int sum = 0;
    if (ca == cb)
    {
        int &minm = *min_element(b, b+ n);
        for (int i = 0; i < n; i++)
        {
            if (b[i] != minm)
            {
                sum += 2 * b[i];
            }
            else
            {
                sum += b[i];
            }
        }
    }else if(n<m)
    {
        
    }
    
    cout << sum;
}
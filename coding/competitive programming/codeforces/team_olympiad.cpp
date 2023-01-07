#include <bits/stdc++.h>

using namespace std;

// int main()
// {
//     int n;
//     cin >> n;
//     int t[n];
//     int t1 = 0, t2 = 0, t3 = 0;

//     int minm = INT16_MAX;

//     for (int i = 0; i < n; i++)
//     {
//         cin >> t[i];
//         if (t[i] == 1)
//             t1 += 1;
//         else if (t[i] == 2)
//             t2 += 1;
//         else
//             t3 += 1;
//     }

//     minm = min(min(t3, t2), t1);

//     cout << minm << endl;
// }

int main()
{
    int n;
    cin >> n;
    int t[n];
    for (int i = 0; i < n; i++)
    {
        cin >> t[i];
    }

    int x = 0, y = 0, z = 0;

    int t1[n];
    int t2[n];
    int t3[n];

    int index[n];

    for (int i = 0; i < n; i++)
    {
        if (t[i] == 1)
        {
            t1[x] = i + 1;
            x++;
        }
        else if (t[i] == 2)
        {
            t2[y] = i + 1;
            y++;
        }
        else
        {
            t3[z] = i + 1;
            z++;
        }
    }

    int minm = min(min(x, y), z);
    cout << minm << endl;

    for (int i = 0; i < minm; i++)
    {
        cout << t1[i] << " " << t2[i] << " " << t3[i]<<endl;
    }
}
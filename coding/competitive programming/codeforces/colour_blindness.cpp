#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;
    while (t--)
    {

        int n;
        cin >> n;

        string a, b;
        cin >> a >> b;

        int check = 0;
        for (int i = 0; i < n; i++)
        {
            if ((a[i] == 'G' && b[i] == 'B') || (a[i] == 'B' && b[i] == 'G') || (a[i] == b[i]))
            {
                check = 1;
            }
            else
            {
                check = 0;
                break;
            }
        }
        if (check == 1)
        {
            cout<<"YES"<<endl;
        }else{
            cout<<"NO"<<endl;
        }
        

        /* code */
    }
}
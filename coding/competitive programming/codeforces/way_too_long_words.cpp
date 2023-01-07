#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;
 
    for (int i = 1; i <= n; i++)
    {
        string str;
        cin >> str;
        if ((str.length()) > 10)
        {
            int x = str.length() - 2;
            cout << (str[0]) << to_string(x) << str[str.length() - 1] << endl;
        }
        else
        {
            cout << str << endl;
        }
    }
 
    return 0;
}
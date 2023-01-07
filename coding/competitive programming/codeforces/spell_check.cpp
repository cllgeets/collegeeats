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
        string s;

        cin >> s;
        if (n == 5)
        {
            if (s.find('T') < s.length() && s.find('i') < s.length() && s.find('m') < s.length() && s.find('u') < s.length() && s.find('r') < s.length())
            {
                cout << "YES"<<endl;
            }
            else
            {
                cout << "NO"<<endl;
            }
        }
        else
        {
            cout << "NO"<<endl;
        }
    }
}
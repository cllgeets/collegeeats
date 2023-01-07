#include <bits/stdc++.h>

using namespace std;



int main()
{
    string s, s1 = "hello";
    cin >> s;

    int j = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (s1[j] == s[i])
        {
            j++;
        }
    }
    if (j == 5)
    {
        cout << "YES";
    }
    else
    {
        cout << "NO";
    }
}
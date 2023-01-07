#include <bits/stdc++.h>

using namespace std;

int main()
{
    string s;
    getline(cin, s);

    char array[100];
    int check = 0;
    for (int i = 1; i < s.length()-1; i + 3)
    {

        for (int j = 0; j <= check; j++)
        {
            if (j == 0)
             
            {
                array[0] = s[i];
            }             
            
            if (s[i] == array[j])
            {
                break;
            }
            else
            {
                array[check] = s[i];
                check += 1;
            }
        }
    }
    for (int i = 0; i < 5; i++)
    {
        cout << array[i]<<"  ";
    }
}
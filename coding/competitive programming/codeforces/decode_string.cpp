#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin>>t;
    while (t--)
    {
    
    int n;
    string s;
    cin >> n;
    cin >> s;

    for (int i = 0; i < n; i++)
    {
        if (s[i + 2] == '0' && s[i+3] != '0' && i !=( n-1)) 
        {
            int p1 = s[i] - '0';    
            int p2 = s[i + 1] - '0';
            int p3 = p1 * 10 + p2;  
            char c = char(p3+96);   
            cout << c ;           
            i += 2;                 
        } 
        else
        {                   
            int p1 = s[i] - '0'; 
            char c = char(p1+96);
            cout << c;          
        }
    }
    cout<<endl;
    }
}
#include <bits/stdc++.h>

using namespace std;

// void recursion(string s)
// {
//     if (s.length() == 0)
//     {
//         return;
//     }

//     string ros = s.substr(1);
//     recursion(ros);
//     cout << s[0];
// }

// int main()
// {
//     string s;
//     cin >> s;

//     recursion(s);
// }

// replacing pi with 3.14 in a string
//  void recursion(string s)
//  {
//      if (s.length() == 0)
//      {
//          return;
//      } 

//     if (s[0] == 'p' && s[1] == 'i')
//     {
//         cout << 3.14;
//         string t = s.substr(2);
//         recursion(t);
//     }
//     else
//     {
//         cout << s[0];
//         string k = s.substr(1);
//         recursion(k);
//     }
// }

// int main()
// {
//     string s;
//     cin >> s;

//     recursion(s);
// }

// Tower of hanoi

// void tower(int n, char src, char dest, char helper)
// {
//     if (n == 1)
//     {
//         return;
//     }

//     tower(n - 1, src, helper, dest);
//     cout << "Move from " << src << " to " << dest << endl;
//     tower(n - 1, helper, dest, src);
// }
// int main()
// {
//     int n;
//     cin >> n;

//     tower(n, 'A', 'C', 'B');
// }

// removing duplicate character from string
//  string recursion(string s)
//  {
//      if (s.length() == 0)
//      {
//          return "";
//      }
//      char ch = s[0];
//      string ans = recursion(s.substr(1));
//      if (ch == ans[0])
//      {
//          return ans;
//      }
//      return (ch + ans);
//  }

// int main()
// {
//     string s;
//     cin >> s;

//     cout<<recursion(s);
// }

// placing x to last from string using recursion
//  string recursion(string s)
//  {
//      if (s.length() == 0)
//      {
//          return "";
//      }

//     char ch = s[0];
//     string ans = recursion(s.substr(1));

//     if (ch == 'x')
//     {
//         return (ans + "x");
//     }
//     else
//     {
//         return (ch + ans);
//     }
// }

// int main()
// {
//     string s;
//     cin >> s;

//     cout << recursion(s);
// }


//finding all substrings of a string using recursion
// void substr(string s)
// {
//     if (s.length() == 0)
//     {
//         return;
//     }

//     cout << s << "   ";
//     substr(s.substr(0, s.length() - 1));
// }

// int main()
// {
//     string s;
//     cin >> s;

//     substr(s);
// }
#include <bits/stdc++.h>

using namespace std;

// checking if array is sorted using recursion
//  bool check(int a[], int n)
//  {
//      if (n == 1)
//      {
//          return true;
//      }

//     bool rest = check(a + 1, n - 1);

//     return (a[0] < a[1] && rest);
// }

// int main()
// {
//     int n;
//     cin >> n;
//     int a[n];
//     for (int i = 0; i < n; i++)
//     {
//         cin >> a[i];
//     }

//     cout<<check(a, n);
// }

// printing numbers in decreasing or increasing number using recursion
//  void dec(int n)
//  {
//      if (n >= 1)
//      {
//          cout << n << "   ";
//          dec(n - 1);
//      }
//  } 

// void inc(int n)
// {

//     if (n == 0)
//     {
//         return;
//     }

//     inc(n - 1);
//     cout << n << "   ";
// }

// int main()
// {
//     int n;
//     cin >> n;

//     dec(n);
//     cout << endl;
//     inc(n);
// }


#include <iostream>

using namespace std;

// inverted numbered pattern
//  int main()
//  {
//      int n;
//      cin >> n;

//     for (int i = 1; i <= n; i++)
//     {
//         for (int k = 1; k <= n - i + 1; k++)
//         {
//             cout << k;
//         }
//         cout << endl;
//     }
// }

// 0-1 PATTERN
//  int main()
//  {
//      int n;
//      cin >> n;
//      for (int i = 1; i <= n; i++)
//      {
//          for (int k = 1; k <= i; k++)
//          {
//              if (i % 2 == 0)
//              {
//                  if (k % 2 == 0)
//                  {
//                      cout << "1";
//                  }
//                  else
//                  {
//                      cout << "0";
//                  }
//              }
//              else
//              {
//                  if (k % 2 == 0)
//                  {
//                      cout << "0";
//                  }
//                  else
//                  {
//                      cout << "1";
//                  }
//              }
//          }
//          cout << endl;
//      }
//  }
//////////other approach to solution
// int main()
// {
//     int n;
//     cin>>n;
//     for (int i = 1; i <= n; i++)
//     {
//         for (int j = 1; j<=i; j++)
//         {
//             if ((i+j)%2 == 0)
//             {
//                 cout<<" 1";
//             }else{
//                 cout<<" 0";
//             }

//         }
//         cout<<endl;
//     }
// }

// RHOMBUS PATTERN
//  int main()
//  {
//      int n;
//      cin >> n;

//     for (int i = 1; i <= n; i++)
//     {
//         for (int j = n - i; j >= 1; j--)
//         {
//             cout << " ";
//         }
//         for (int k = 1; k <= n; k++)
//         {
//             cout << " *";
//         }
//         cout << endl;
//     }
// }

// NUMBER PATTERN PYRAMID
//  int main(){
//      int n;
//      cin>>n;

//     for (int i = 1; i <=n; i++)
//     {
//         for (int j = n-i; j >= 1; j--)
//         {
//             cout<<" ";
//         }
//         for (int k = 1; k<=i; k++)
//         {
//             cout<<" ";
//             cout<<k;
//         }

//         cout<<endl;

//     }
// }

// palindromic pattern
//  int main()
//  {
//      int n;
//      cin >> n;

//     for (int i = 1; i <= n; i++)
//     {
//         for (int k = n - i; k >= 1; k--)
//         {
//             cout << " ";
//         }
//         for (int j = i; j >= 1; j--)
//         {

//             cout << j;
//         }
//         for (int a = 2; a < i + 1; a++)
//         {

//             cout << a;
//         }

//         cout << endl;
//     }
// }

//ZIG-ZAG PATTERN
// int main()
// {
//     int n;
//     cin >> n;

//     for (int i = 1; i <= 3; i++)
//     {
//         for (int j = 1; j <= n; j++)
//         {
//             if (((i + j) % 4 == 0) || (i == 2 && j % 4 == 0))
//             {
//                 cout << "*";
//             }else{
//                 cout<<" ";
//             }
//         }
//         cout<<endl;
//     }
// }

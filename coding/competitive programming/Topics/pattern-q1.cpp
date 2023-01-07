#include <iostream>

using namespace std;

// rectangle pattern
//  int main()
//  {
//      int rows, column;
//      cin >> rows;
//      cin >> column;

//     for (int i = 0; i < rows; i++)
//     {
//         for (int k = 0; k < column; k++)
//         {
//             cout << "*";
//         }
//         cout << endl;
//     }
// }


// hollow rectangle
//  int main()
//  {
//      int rows, column;
//      cin >> rows >> column;

//     for (int a = 0; a < column; a++)
//     {
//         cout << "*";
//     }
//     cout << endl;

//     for (int i = 0; i < rows-2; i++)
//     {
//         cout << "*";
//         for (int k = 0; k < column - 2; k++)
//         {
//             cout << " ";
//         }
//         cout << "*";
//         cout<<endl;
//     }
//     for (int a = 0; a < column; a++)
//     {
//         cout << "*";
//     }
// }


// inverted pyramid
//  int main(){
//      int n;
//      cin>>n;

//     for (int k = n; k > 0; k--)
//     {
//         for (int i = 0; i < k; i++)
//         {
//            cout<<"*";
//         }
//         cout<<endl;

//     }

// }


// pyramid at 180 degree
//  int main()
//  {
//      int n;
//      cin >> n;

//     for (int i = 0; i < n; i++)
//     {
//         for (int k = n - i +1; k > 0; k--)
//         {
//             cout << " ";
//         }
//         for (int a = 0; a < i +1; a++)
//         {
//             cout << "*";
//         }
//         cout << endl;
//     }
// }


// NUMBERED PYRAMID
//  int main()
//  {
//      int n;
//      int k = 1;
//      cin >> n;

//     for (
//         int i = 0; i < n; i++)
//     {
//        for (
//         int a = 0; a <= i; a++)
//        {
//          cout<<k;
//        }
//        cout<<endl;
//        k = k+1;
//         }
// }


// FLOYDS TRIANGLE
//  int main(){
//      int n;
//      cin>>n;

// int count =1 ;
//     for (int i = 1; i <= n; i++)
//     {
//         for (int k = 1; k <= i; k++)
//         {
//             cout<<count;
//             count++;
//         }
//         cout<<endl;
//     }
// }


// BUTTERFLY PATTERN
//  int main()
//  {
//      int n;
//      cin >> n;

//     for (
//         int i = 1; i <= n; i++)
//     {
//         for (int k = 1; k <= i; k++)
//         {
//             cout << "*";
//         }
//         for (int a = 2 * n - 2 * i; a >= 1; a--)
//         {
//             cout << " ";
//         }
//         for (int k = 1; k <= i; k++)
//         {
//             cout << "*";
//         }
//         cout << endl;
//     }
//     for (int b = 1; b <= n; b++)
//     {
//         for (int c = n-b; c >=0; c--)
//         {
//             cout<<"*";
//         }
//         for (int d = 2; d <2*b; d++)
//         {
//            cout<<" ";
//         }
//         for (int c = n-b; c >=0; c--)
//         {
//             cout<<"*";
//         }
//         cout<<endl;
//     }
// }

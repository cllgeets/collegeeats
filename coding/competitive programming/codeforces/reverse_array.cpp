#include <bits/stdc++.h>

using namespace std;


//using recursion 
// void check(int arr[], int n, int i)
// {
//     if (i>(n-1)/2)
//     {
//         return;
//     }

//     int initial = arr[i];
//     arr[i] = arr[n - i - 1];
//     arr[n - i - 1] = initial;

//     check(arr, n, i + 1);
    
// }

// int main()
// {
//     int n;
//     cin >> n;
//     int arr[n];
//     for (int i = 0; i < n; i++)
//     {
//         cin >> arr[i];
//     }

//     check(arr, n, 0);

//     for (int i = 0; i < n; i++)
//     {
//         cout << arr[i];
//     }
// }

// normal method
//  int main()
//  {
//      int n;
//      cin >> n;
//      int arr[n];
//      for (int i = 0; i < n; i++)
//      {
//          cin >> arr[i];
//      }

//     for (int i = 0; i < n / 2; i++)
//     {
//         int initial = arr[i];
//         arr[i] = arr[n - i - 1];
//         arr[n - i - 1] = initial;
//     }

//     for (int i = 0; i < n; i++)
//     {
//         cout << arr[i] << endl;
//     }
// }
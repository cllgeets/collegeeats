#include <iostream>

using namespace std;

// Finding min in an array
//  int main(){
//      int n;
//      cin>>n;

//     int arr[n];

//     for (int i = 0; i < n; i++)
//     {
//         cin>>arr[i];
//     }

//     int x = INT8_MIN;

//     for (int j = 0; j < n; j++)
//     {
//         x = max(x, arr[j]);
//     }
//     cout<<x;
// }

// Sum of all subarrays of a array
//  int main()
//  {
//      int n;
//      cin >> n;

//     int arr[n];
//     for (int i = 0; i < n; i++)
//     {
//         cin >> arr[i];
//     }

//     int x;
//     cin >> x;
//     int array[x];

//     if (x <= n)
//     {
//         int k = 0;
//         while (k < n-x+1)
//         {
//             int t = k;
//             for (int i = 0; i < x; i++)
//             {
//                 array[i] = arr[t + i];
//             }
//             t++;

//             cout << "here is your sum of array : ";
//             int sum = 0;
//             for (int l = 0; l < x; l++)
//             {
//                 sum += array[l];
//             }
//             cout<<sum;
//             cout << endl;
//             k++;
//         }
//     }
// }

// max length of arithmetica subarray in a array
//  int main()
//  {
//      int n;
//      cin >> n;

//     int a[n];
//     for (int i = 0; i < n; i++)
//     {
//         cin >> a[i];
//     }

//     int ans = 2;
//     int pd = a[1]-a[0];
//     int j = 2;
//     int curr = 2;

//     while (j<n)
//     {
//         if (pd == a[j]-a[j-1])
//         {
//             curr++;
//         } else{
//             pd = a[j]-a[j-1];
//             curr =2;
//         }
//         ans = max(ans, curr);
//         j++;
//     }
//     cout<<ans;
//     return 0;

// }

// Nahi hua samajh aajaye to bata dena
//  int main()
//  {
//      int N;
//      cin >> N;

//     int a[N];

//     for (int i = 0; i < N; i++)
//     {
//         cin >> a[i];
//     }

//     int r = 0;
//     for (int i = 0; i < N; i++)
//     {
//         int m1 = 0;
//         for (int j = i; j >= 0; j--)
//         {
//             m1 = max(m1, a[j]);

//             if (a[j] > a[j + 1] && a[j] < a[j - 1])
//             {
//                 r++;
//             }
//         }
//         cout << "value of m1: " << m1 << endl;

//         if (a[i] > m1)
//         {
//             r++;
//         }
//     }
//     cout << r;
// }

// minimum Index of repeating number in array
//  int main()
//  {
//      int n;
//      cin >> n;

//     int a[n];
//     for (int i = 0; i < n; i++)
//     {
//         cin >> a[i];
//     }

//     const int N = 10e6+2;
//     int arr[N];

//     for (int i = 0; i < N; i++)
//     {
//         arr[i] = -1;
//     }

//     int minInd = INT8_MAX;
//     for (int i = 0; i < n; i++)
//     {
//         int num = a[i];
//          if (arr[num] != -1)
//         {
//             minInd = min(minInd, arr[num]);
//         }else{
//             arr[num] = i;
//         }
//     }

//     if (minInd == INT8_MAX){
//         cout<<"nothing";
//     }
//     {
//         cout<<minInd;
//     }

// }

int main()
{
    int n;
    cin >> n;

    int a[n];
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    int sum;
    cin >> sum;

    for (int i = 0; i < n; i++)
    {
        for (int j = i; j < ; j++)
        {
            
        }
        
    }
    
}

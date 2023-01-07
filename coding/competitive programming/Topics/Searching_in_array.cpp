#include <iostream>
using namespace std;

// LINEAR SEARCHING
//  int search(int a, int n, int arr[]){
//  for (int i = 0; i < n; i++)
//      {
//          if (arr[i] == a)
//          {
//              return i;
//          }
//      }
//      return -1;
//  }

// int main()
// {
//     int n;
//     cin >> n;

//     int arr[n];
//     for (int i = 0; i < n; i++)
//     {
//         cin >> arr[i];
//     }

//     int a;
//     cin>>a;

//     cout<<"The index is : "<<search(a, n, arr);
// }

// BINARY SEARCH
int search(int arr[], int n, int a)
{
    int s = 0;
    int e = n;

    while (s <= e)
    {
        int mid = (s + e) / 2;
        if (arr[mid] == a)
        {
            return mid;
        }
        else if (arr[mid] < a)
        {
            s = mid + 1;
        }
        else if (arr[mid] > a)
        {
            e = mid - 1;
        }
    }
    return -1;
}

int main()
{
    int n;
    cin >> n;

    int arr[n];
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    int a;
    cin >> a;

    cout << "Index is : " << search(arr, n, a);
}  
#include <bits/stdc++.h>

using namespace std;

// power of a number using recursion
//  int power(int n, int p)
//  {
//      if (p == 0)
//      {
//          return 0;
//      }
//      int value = power(n, p - 1);

//     return n * value;
// }

// int main()
// {
//     int n, p;
//     cin >> n >> p;
//     cout << power(n, p);

//     return 0;
// }

// factorial of a number using recursion
//  int sum(int n){
//      if (n==0)
//      {
//          return 1;
//      }

//     int answer = sum(n-1);

//     return answer*n;
// }
// int main(){
//     int n;
//     cin>>n;

//     cout<<sum(n);

// }

int search(int n)
{

    if (n == 2)
    {
        return 0;
    }

    int tatti = search(n - 1) + search(n - 2);
    return tatti;
}

int main()
{
    int n;
    cin >> n;

    cout<<search(n);
}
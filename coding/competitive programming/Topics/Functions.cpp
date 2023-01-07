#include <iostream>
#include <cmath>
using namespace std;

// QUESTION 1 CHECKING PRIME NUMBER
//  bool isPrime(int num)
//  {
//      for (int i = 2; i <= sqrt(num); i++)
//      {
//          if (num % i == 0)
//          {
//              return false;
//          }
//      }
//      return true;
//  }

// int main()
// {
//     int a, b;
//     cin >> a >> b;

//     for (int i = a; i <= b; i++)
//     {
//         if (isPrime(i))
//         {
//             cout << i << endl;
//         }
//     }
// }

// FINDING FABUNAAKI SERIES
//  void fabunaaki(int n)
//  {
//      int a = 0;
//      int b = 1;
//      int digit;

//     for (int i = 1; i <= n; i++)
//     {
//         cout << a;
//         digit = a + b;
//         a = b;
//         b = digit;
//     }
//     return;
// }

// int main()
// {
//     int n;
//     cin >> n;

//     fabunaaki(n);
// }

// FACTORIAL OF A NUMBER
//  void factorial(int num){
//      int multi =1;

// for (int i = 2; i <= num; i++)
// {
//    multi *= i;
// }

// cout<<multi<<endl;

// }

// int main(){
//     int n;
//     cin>>n;

//     factorial(n);
// }

// FINDING NCR
// int ncr(int n)
// {
//     int multi1 =1;

//     for (int i = 2; i <= n; i++)
//     {
//         multi1 *= i;
//     }
//     return multi1;
// }

// int main()
// {
//     int n;
//     int r;
//     cin >> n >> r;

//     int ans = ncr(n) / (ncr(r) * ncr(n - r));

//     cout << ans;
// }

// PASCALS TRIANGLE
// int fact(int n)
// {
//     int multi1 =1;

//     for (int i = 2; i <= n; i++)
//     {
//         multi1 *= i;
//     }
//     return multi1;
// }

// int main()
// {
//     int i;
//     cin>>i;

//     for (int a = 0; a <=i-1; a++)
//     {
//         for (int b = 0; b <=a; b++)
//         {
//             int fa = fact(a)/(fact(a-b)*fact(b));
//             cout<<fa;
//         }
//         cout<<endl;
        
//     }
// }




#include <iostream>
#include <cmath>
#include <math.h>

using namespace std;

// FINDING PRIME AND NONPRIME NUMBER
//  int main()
//  {
//      int n;
//      cin >> n;

//     for (int i = 2; i < sqrt(n); i++)
//     {
//         if (n%i ==0)
//         {
//             cout<<"Non prime";
//             break;
//         }else{
//             cout<<"prime";
//             break;
//         }

//     }
// }

// REVERSING A NUMBER
//  int main()
//  {
//      int n;
//      cin >> n;

//     int reverse =0;

//     while (n > 0)
//     {
//         int lastdigit = n % 10;
//         reverse = reverse*10 + lastdigit;
//         n=n/10;
//     }
//     cout<<reverse;
//     return 0;
// }


//CHECKING IF ARMSTRONG NUMBER 
// int main()
// {
//     int n;
//     cin >> n;
//     int cube = 0;

//     int original = n;
//     while (n > 0)
//     {
//         int l = n % 10;
//         cube += pow(l, 3);
//         n = n / 10;
//     }
//     cout << cube << endl;
//     if (cube == original)
//     {
//         cout << "Number is Armstrong number";
//     }
//     else
//     {
//         cout << "not an Armstrong number";
//     }
// }
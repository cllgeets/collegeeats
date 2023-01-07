#include <iostream>
using namespace std;
// int n, b, mas[105];
// int main() {
//     cin >> n;
//     for (int i = 1; i <= n; i++) {
//         cin >> b;
//         mas[b] = i;
//     }
//     for (int i = 1; i <= n; i++) {
//         cout << mas[i] << " ";
//     }
// }

int main()
{
    int n;
    cin >> n;
    int a[n];
    int b =0;

    for (int i = 1; i <= n; i++)
    {
       cin>>b;
       a[b] = i ;
    }

    for (int i = 1; i <= n; i++)
    {
        cout<<a[i]<<"  ";
    }
    
}

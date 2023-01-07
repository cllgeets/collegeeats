// Online C++ compiler to run C++ program online
#include <iostream>
using namespace std;

// QUESTION1 - PERMUTATION
//  int main() {
//      // Write C++ code here
//      int n;cin>>n;
//      while(n--){
//          int x;cin>>x;
//          cout<<x<<" ";
//          for(int i=1;i<x;i++)
//          cout<<i<<" ";
//          cout<<endl;
//      }

//     return 0;
// }

// QUESTION2 - PARTY
int main()
{
    int t;
    cin >> t;

    for (int i = 0; i < t; i++)
    {

        int n;
        int m;
        cin >> n;
        cin >> m;

        int arr[n];
        for (int i = 0; i < n; i++)
        {
            cin >> arr[i];
        }

        if (m > 0)
        {
            int a[m];
            int b[m];

            for (int i = 0; i < m; i++)
            {
                cin >> a[i];
                cin >> b[i];
            }

            int minim = INT16_MAX;
            for (int i = 0; i < m; i++)
            {
                minim = min(minim, arr[a[i] - 1] + arr[b[i] - 1]);
            }
            cout << minim;
        }else{
            cout<<"0";
        }

       
    }
     return 0;
}
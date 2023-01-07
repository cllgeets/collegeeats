#include <iostream>

using namespace std;

// int main()
// {
//     for (int date = 1; date <= 30; date++)
//     {
//         if (date % 2 == 0) // here if date is odd then it will skip that step and continue to next step
//         {
//             continue;
//         }
//         cout << "go out today" << endl;
//     }
// }

//#example2
// int main()
// {
//     for (int i = 0; i < 100; i++)
//     {
//         if (i % 3 == 0)
//         {
//             continue;
//         }
//         cout << i << endl;
//     }
// }

//#example3

int main(){
    int n;
    cin>>n;

    for (int i = 0; i < n; i++)
    {
        if (n%i)
        {
           continue;
        }   
        
    }
    
}
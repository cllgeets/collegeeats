#include <iostream>

using namespace std;

// Input outout of character array
//  int main()
//  {
//      char arr[100] = "apple";
//      int i = 0;

//     cin >> arr;

//     cout << arr[2];

//     return 0;
// }


//Check if word is palindrome
int main()
{

    int n;
    cin >> n;
    char arr[n + 1];
    cin >> arr;

    bool t = true;
    for (int i = 0; i < n / 2; i++)
    {
        if (arr[i] != arr[n - i - 1])
        {
            t = false;
        }
    }
    if (t == 0)
    {
        cout << "word is not an palindrome";
    }
    else
    {
        cout << "word is palindrome";
    }
}

int main(){
    int n ;
    cin>>n;

    char arr[n+1];

     

    return 0;
}
#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin>>t;
    while (t--)
    {
    
    int n, c;
    cin >> n >> c;
    int arr[n];
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
    sort(arr, arr + n);

    int mainCount = 0;
    for (int i = 0; i < n; i++)
    {
        int count = 1;
        if (arr[i] != 0)
        {
            for (int j = i+1; j < n; j++)
            {
                if (arr[j] == arr[i])
                {
                    count++;
                    arr[j] = 0;
                }
                else
                {
                    break;
                }
            }
            if (count < c)
            {
                mainCount += count;
            }
            else
            {
                mainCount += c;
            }
        }else{
            continue;
        }
    }
    cout<<mainCount<<endl;
    }
}
#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;
    cin >> n;

    int arr[n];
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }

    int t = n;
    for (int i = 0; i < t; i++)
    {
        for (int j = i + 1; j < t; j++)
        {
            if (arr[i] == arr[j])
            {
                for (int l = 0; l < i; l++)
                {
                    for (int k = 0; k < n; k++)
                    {
                        int arru = arr[k];
                        arr[k] = arr[k + 1];
                        arr[k + 1] = arru;
                    }
                    t--;
                }
            }
            break;
            
        }
    }

    cout << n - t;
}
#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n, k;
    cin >> n >> k;
    int arr[n];

    int num = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
        arr[i] += k;
        if (arr[i] <= 5)
        {
            num++;
        }
    }
    int hihi = num / 3;
    cout<<hihi;
}
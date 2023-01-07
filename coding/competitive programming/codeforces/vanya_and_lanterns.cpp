#include <bits/stdc++.h>

using namespace std;

int main()
{
    int m, n;
    cin >> m >> n;
    double arr[m];
    for (int i = 0; i < m; i++)
        cin >> arr[i];

    sort(arr, arr + m);

    vector<double> v;
    double a1 = arr[0]/1.0f;
    double a2 = (n - arr[m - 1])/1.0f;

    v.push_back(a1);
    v.push_back(a2);
    for (int i = 1; i < m; i++)
    {
        int num = arr[i] - arr[i - 1];
        double no;
        no = num;
        v.push_back(no);
    }

    double max = -20000.0;
    for (int i = 0; i < v.size(); i++)
    {
        if (v.at(i) > max)
        {
            max = v.at(i);
        }
    }
    cout << max/1.00f;
}
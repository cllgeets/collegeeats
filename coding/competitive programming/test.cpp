#include<bits/stdc++.h>

using namespace std;

int main(){
    int n;
    cin>>n;
    int arr[n];
    for (int i = 0; i < n; i++)
    {
       cin>>arr[i];
    }

    vector<pair<int, int>> v;
    pair<int, int> pair;

    for (int i = 0; i < n; i++)
    {
        for (int j = i; j < n-1; j++)
        {
           v.push_back(make_pair(arr[i], arr[j]));
        }
    }

    for (int i = 0; i < v.size(); i++)
    {
        cout<<v[i].first<<"  ";
        cout<<v[i].second<<endl;

    }
    


}
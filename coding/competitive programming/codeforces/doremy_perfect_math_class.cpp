#include<bits/stdc++.h>

using namespace std;

int binary_search(int arr[], int s, int e, int k){
    int mid = (s+e)/2;
    if (arr[mid] == k)
    {
        return mid;
    }else if (arr[mid] < k)
    {
        s = mid;
       return binary_search(arr,s, e, k );
    }else if (arr[mid] > k)
    {
        e = mid;
        return binary_search(arr, s, e, k);
    }
}

int main(){
    int n;
    cin>>n;
    int arr[n];

    for(int i =0; i<n; i++){
    cin>>arr[i];
    }
    
}
#include<bits/stdc++.h>

using namespace std;

int main(){
    int t;
    cin>>t;
    while (t--)
    {
    
    int n, a, b;
    cin>>n>>a>>b;

    if (b<a)
    {
        cout<<1<<endl;
    }else{
        int rem = n%a;
        if (rem == 0)
        {
            cout<<n/a<<endl;
        }else{
            cout<<n/a +1<<endl;
        }
        
    }
    }
}
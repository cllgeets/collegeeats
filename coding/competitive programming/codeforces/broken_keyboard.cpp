#include<bits/stdc++.h>

using namespace std;

int main(){
    int t;
    cin>>t;
   while(t--){ int n;
    cin>>n;
    string s;
    cin>>s;

    int flag =0;
    for (int i = 1; i < n; i+=3)
    {
        if (s[i] != s[i+1])
        {
            flag =1; 
            cout<<"NO"<<endl;   
            break;   
        }
    }
    if(flag == 0)
    cout<<"YES"<<endl;}
    
}
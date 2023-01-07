#include <bits/stdc++.h>

using namespace std;

int main()
{

    int x = 0;
    int a =0;
    int b =0;
    for (int j = 1; j <= 5; j++)
    {
        for (int i = 1; i <= 5; i++)
        {
            cin>>x;
            if(x==1){
                a =i;
                b =j;
            }
        }
    }
    cout<<abs(a-3)+ abs(b-3);
}
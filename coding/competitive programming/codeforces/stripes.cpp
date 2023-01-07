#include <bits/stdc++.h>

using namespace std;

int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        char arr[8][8];

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                cin >> arr[i][j];
            }
        }

        int hihi = 0;
        for (int i = 0; i < 8; i++)
        {
            int j;
            for(j =0;j<8;j++){
                if(arr[i][j]!='R'){
                    break;
                }
            }
            if(j==8){
                hihi = 1;
            }
        }
        if (hihi ==0)
        {
            cout<<'B'<<endl;
        }
        if(hihi == 1){
            cout<<"R"<<endl;
        }
    }
}
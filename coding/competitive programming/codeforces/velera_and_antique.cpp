#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n; 
    cin >> n; //taking input of number of sellers
    int m;
    cin >> m; //taking input of total money

    int arr[n];  //array to store number of items for each seller
    int arr1[10000];  //array to store the price of items for each sellers
    int num = 0;  //used to calculate total number of eligible sellers
    int inv[n];  //storing eligible sellers number
    int x = 0;  //just to start index of above array
    for (int i = 0; i < n; i++)   //this loop runnning for every sellers
    {
        cin >> arr[i];  //taking input of number of items for a seller
        int check = 0;
        for (int j = 0; j < arr[i]; j++)  //this loop runnning for particular seller
        {
            cin >> arr1[j];  //taking input of prices for a seller
            if (arr1[j] < m && check == 0)  //checking eligibility conditions
            {
                num++;  
                inv[x] = i+1;
                x++;
                check = 1;
            }
        }
    }

    cout << num << endl;
    for (int i = 0; i < num; i++)
    {
        cout << inv[i]<<" ";
    }
}
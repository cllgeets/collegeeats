#include <bits/stdc++.h>

using namespace std;

void sortArray(char arr[], int n)
{
    for (int counter = 0; counter < n - 1; counter++)
    {
        for (int i = 0; i < n - counter - 1; i++) // This loop is working on unsorted array. The length of loop decrease every time one element sorting  complete.
        {
            if (arr[i] > arr[i + 1])
            {
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
    }
}

int main()
{
    char arr[1001];
    string str;
    cin >> str;


    int counter = 0;
    for (int i = 0; i < str.length(); i++)
    {
        if (str[i] != '+')
        {
            arr[counter] = str[i];
            counter++;
        }
    }

    sortArray(arr , counter);
    for (int i = 0; i < counter; i++)
    {
        if (i!=counter-1)
        {
            cout<<arr[i]<<'+';
        }else{
            cout<<arr[i];
        }
        
    }
}
